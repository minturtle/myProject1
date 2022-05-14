package frames;

import domain.Menu;
import service.OrderService;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderFrame extends JFrame {

	private JPanel contentPane;
	private JPanel centerPane;
	private JLabel menuNameLabel;
	private JLabel priceLabel;
	private JSpinner spinner;
	private JLabel totalPriceLabel;
	private JButton cartAddBtn;
	private OrderService orderService;

	public OrderFrame(Menu menu, OrderService orderService) {
		this.orderService = orderService;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		centerPane = new JPanel();
		centerPane.setBounds(0, 24, 436, 112);
		contentPane.add(centerPane);
		centerPane.setLayout(null);
		
		menuNameLabel = new JLabel(menu.getName());
		menuNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		menuNameLabel.setFont(new Font("����", Font.PLAIN, 20));
		menuNameLabel.setBounds(154, 26, 129, 49);
		centerPane.add(menuNameLabel);
		
		priceLabel = new JLabel("���� : " + menu.getPrice() + "��");
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		priceLabel.setBounds(164, 75, 85, 27);
		centerPane.add(priceLabel);
		
		spinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		spinner.setBounds(128, 166, 30, 22);
		contentPane.add(spinner);
		
		JLabel lblNewLabel_1 = new JLabel("����");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(51, 161, 65, 30);
		contentPane.add(lblNewLabel_1);
		
		totalPriceLabel = new JLabel("���� :"+menu.getPrice()+"��");
		totalPriceLabel.setFont(new Font("����", Font.PLAIN, 15));
		totalPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		totalPriceLabel.setBounds(278, 165, 106, 22);
		contentPane.add(totalPriceLabel);
		
		cartAddBtn = new JButton("1�� ���");
		cartAddBtn.setBounds(0, 208, 436, 55);
		contentPane.add(cartAddBtn);

		spinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int totalPrice = Integer.parseInt(spinner.getValue().toString()) + menu.getPrice();
				totalPriceLabel.setText("���� :" +totalPrice + "��");
				cartAddBtn.setText(spinner.getValue().toString() + "�� ���");
			}
		});

		cartAddBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				orderService.addCart(menu, Integer.parseInt(spinner.getValue().toString()));
				dispose();
			}
		});

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
