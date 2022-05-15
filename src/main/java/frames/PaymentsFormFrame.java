package frames;

import domain.CardPay;
import domain.KakaoPay;
import domain.User;
import service.UserService;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentsFormFrame extends  JFrame{

	private JFrame frame;
	private UserService userService;
	private User user;
	public PaymentsFormFrame(UserService userService, User user) {
		this.user = user;
		this.userService = userService;
		initialize();
	}

	private void initialize() {
		setSize(300, 200);
		frame = this;
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JRadioButton kakaoPayRadioBtn = new JRadioButton("카카오페이");
		kakaoPayRadioBtn.setBounds(31, 82, 142, 34);
		frame.getContentPane().add(kakaoPayRadioBtn);
		
		JRadioButton cardRadioBtn = new JRadioButton("신용/체크 카드");
		cardRadioBtn.setBounds(31, 130, 142, 34);
		frame.getContentPane().add(cardRadioBtn);

		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(kakaoPayRadioBtn);
		btnGroup.add(cardRadioBtn);

		JButton btnNewButton = new JButton("결제수단 추가");
		btnNewButton.setBounds(0, 195, 424, 46);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("\uACB0\uC81C \uC218\uB2E8 \uCD94\uAC00");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(99, 10, 243, 58);
		frame.getContentPane().add(lblNewLabel);

		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userService.addPayment(user, kakaoPayRadioBtn.isSelected() ? KakaoPay.class : CardPay.class);
				dispose();
			}
		});
	}

}
