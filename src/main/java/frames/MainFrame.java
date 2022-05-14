package frames;

import config.MyConfig;
import domain.Category;
import domain.Order;
import domain.Restraurent;
import domain.User;
import service.OrderService;
import service.RestService;
import service.UserService;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

public class MainFrame {

	private UserService userService;
	private RestService restService;
	private OrderService orderService;
	private User user;
	private JFrame frame;

	//loginview
	private JTextField idField;
	private JPasswordField passwordField;

	//mainview
	private JLabel userAddressLabel;
	//restListview
	private JScrollPane restListScroll;
	private JLabel categoryHeaderLabel;

	//restDetailview
	private JLabel restNameLabel;
	private JScrollPane menuListScroll;
	private JButton gotoBackBtn;


	private JPanel loginView;
	private JPanel mainView;
	private JPanel restListView;
	private JPanel restDetailView;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void start(){
		MainFrame window = MyConfig.mainFrame();
		window.frame.setSize(450, 300);
		window.frame.setVisible(true);
	}

	public MainFrame() {
		initialize();
	}

	public MainFrame(UserService userService, RestService restService, OrderService orderService) {
		this.userService = userService;
		this.restService = restService;
		this.orderService = orderService;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("배달 주문 프로그램");
		frame.setBounds(100, 100, 450, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().setLayout(null);

		loginView = initLoginView();
		mainView = initMainView();
		restListView = initRestListView();
		restDetailView = initRestDetailView();

		//처음에는 로그인뷰만 뜨도록
		mainView.setVisible(false);
		restListView.setVisible(false);
		restDetailView.setVisible(false);


		frame.getContentPane().add(loginView);
		frame.getContentPane().add(mainView);
		frame.getContentPane().add(restListView);

	}

	private JPanel initRestListView(){
		JPanel RestListView = new JPanel();

		RestListView.setBounds(0, 0, 436, 563);

		RestListView.setLayout(null);


		categoryHeaderLabel = new JLabel("카테고리");
		categoryHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		categoryHeaderLabel.setBounds(0, 0, 436, 48);
		JButton gotoBackBtn = new JButton("뒤로가기");
		gotoBackBtn.setBounds(0, 10, 82, 23);
		gotoBackBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveToMainView(RestListView);
			}
		});
		RestListView.add(gotoBackBtn);
		RestListView.add(categoryHeaderLabel);

		JScrollPane restListScroll = new JScrollPane();
		this.restListScroll = restListScroll;
		restListScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		this.restListScroll = restListScroll;
		restListScroll.setBounds(0, 46, 436, 429);
		RestListView.add(restListScroll);

		JPanel underLineSpace = new JPanel();
		underLineSpace.setBounds(0, 485, 436, 68);
		RestListView.add(underLineSpace);
		underLineSpace.setLayout(new GridLayout(1, 4, 2, 2));

		JButton searchBtn = new JButton("검색");
		underLineSpace.add(searchBtn);

		JButton jjimBtn = new JButton("찜");
		underLineSpace.add(jjimBtn);

		JButton orderListBtn = new JButton("주문내역");
		underLineSpace.add(orderListBtn);

		JButton profileBtn = new JButton("My배민");
		underLineSpace.add(profileBtn);
		return RestListView;
	}
	private JPanel initMainView() {
		JPanel mainView = new JPanel();
		mainView.setBounds(0, 0, 436, 563);

		mainView.setLayout(null);

		userAddressLabel = new JLabel("집주소");
		userAddressLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userAddressLabel.setBounds(0, 10, 436, 78);
		mainView.add(userAddressLabel);

		JPanel pictureSpace = new JPanel();
		pictureSpace.setBounds(0, 98, 436, 112);
		mainView.add(pictureSpace);

		JPanel categoryGridSpace = new JPanel();
		categoryGridSpace.setBounds(0, 208, 436, 286);

		categoryGridSpace.setLayout(new GridLayout(4, 5, 10, 10));


		JPanel underLineSpace = new JPanel();
		underLineSpace.setBounds(0, 495, 436, 68);

		mainView.add(categoryGridSpace);
		mainView.add(underLineSpace);
		underLineSpace.setLayout(new GridLayout(1, 4, 2, 2));

		JButton searchBtn = new JButton("검색");
		JButton jjimBtn = new JButton("찜");
		JButton orderListBtn = new JButton("주문내역");
		JButton profileBtn = new JButton("My배민");

		underLineSpace.add(searchBtn);
		underLineSpace.add(jjimBtn);
		underLineSpace.add(orderListBtn);
		underLineSpace.add(profileBtn);


		Category[] categoryList = Category.values();

		for (int i = 0; i < 17; i++) {
			Category category = categoryList[i];
			JButton categoryChooseBtn = new JButton(category.toString());
			categoryChooseBtn.addActionListener(new ActionListener() {
				//메인 뷰에서 카테고리를 골랐을 때 실행되는 메서드
				@Override
				public void actionPerformed(ActionEvent e) {
					//화면 변경
					moveToRestListView(mainView, category);
				}
			});
			categoryGridSpace.add(categoryChooseBtn);
		}
		return mainView;
	}
	private JPanel initLoginView(){
		JPanel loginView = new JPanel();
		loginView.setBounds(0, 0, 436, 263);
		loginView.setLayout(null);

		JLabel headerLabel = new JLabel("\uBC30\uB2EC \uC8FC\uBB38 \uD504\uB85C\uADF8\uB7A8");
		headerLabel.setBounds(12, 10, 436, 24);
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerLabel.setFont(new Font("占쏙옙占쏙옙", Font.PLAIN, 20));
		loginView.add(headerLabel);

		JLabel idLabel = new JLabel("\uC544\uC774\uB514");
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setFont(new Font("占쏙옙占쏙옙", Font.PLAIN, 15));
		idLabel.setBounds(89, 91, 62, 21);
		loginView.add(idLabel);

		idField = new JTextField();
		idField.setBounds(186, 91, 190, 21);
		loginView.add(idField);
		idField.setColumns(10);

		JLabel pwLabel = new JLabel("\uBE44\uBC00\uBC88\uD638");
		pwLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pwLabel.setFont(new Font("占쏙옙占쏙옙", Font.PLAIN, 15));
		pwLabel.setBounds(89, 134, 62, 21);
		loginView.add(pwLabel);

		JButton loginBtn = new JButton("\uB85C\uADF8\uC778");
		loginBtn.setBounds(92, 193, 91, 23);
		loginView.add(loginBtn);

		passwordField = new JPasswordField();
		passwordField.setBounds(186, 135, 190, 21);
		loginView.add(passwordField);

		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				user = userService.login(idField.getText(), new String(passwordField.getPassword()));
				frame.setSize(450, 600);
				moveToMainView(loginView);
				userAddressLabel.setText(user.getAddress());
			}
		});

		return loginView;
	}
	private JPanel initRestDetailView(){
		JPanel RestDetailView = new JPanel();
		RestDetailView.setBounds(0, 0, 436, 563);
		frame.getContentPane().add(RestDetailView);
		RestDetailView.setLayout(null);

		JButton cartBtn = new JButton("장바구니");
		cartBtn.setBounds(354, 502, 82, 51);
		RestDetailView.add(cartBtn);

		JLabel restNameLabel = new JLabel("가게 이름");
		this.restNameLabel = restNameLabel;
		restNameLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		restNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		restNameLabel.setBounds(0, 33, 436, 134);
		RestDetailView.add(restNameLabel);

		JScrollPane menuListScroll = new JScrollPane();
		this.menuListScroll = menuListScroll;
		menuListScroll.setBounds(0, 177, 436, 376);
		RestDetailView.add(menuListScroll);

		JPanel topNav = new JPanel();
		topNav.setLayout(null);
		topNav.setBounds(0, 0, 436, 30);
		RestDetailView.add(topNav);

		JButton gotoBackBtn = new JButton("뒤로가기");
		this.gotoBackBtn = gotoBackBtn;
		gotoBackBtn.setBounds(0, 0, 82, 33);
		topNav.add(gotoBackBtn);
		return RestDetailView;
	}


	private void moveToMainView(JPanel preView){
		preView.setVisible(false);
		mainView.setVisible(true);
	}
	private void moveToRestListView(JPanel preView, Category category){
		restListView.setVisible(true);
		preView.setVisible(false);
		categoryHeaderLabel.setText(category.toString());
		//카테고리의 가게들을 모두 찾아서 list에 추가해줌
		Collection<Restraurent> restraurents = restService.findRestByCategory(category);

		JPanel gridList = new JPanel(new GridLayout(restraurents.size(), 1));


		//각 restraunt객체의 label을 만들고 gridList에 넣어줌
		restraurents.stream().forEach(x->{
			JLabel label = new JLabel(x.getName());
			label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					movteToRestDetailView(restListView, x);
				}
			});
			gridList.add(label);
		});

		restListScroll.setViewportView(gridList);
	}
	private void movteToRestDetailView(JPanel preView, Restraurent restraurent){
		preView.setVisible(false);
		restDetailView.setVisible(true);

		restNameLabel.setText(restraurent.getName());

		JPanel gridList = new JPanel(new GridLayout(restraurent.getMenuList().size(), 1));

		restraurent.getMenuList().stream().forEach(x->{
			JLabel label = new JLabel(String.format("%s (%s)", x.getName(), x.getPrice()));
			label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					OrderFrame orderFrame = new OrderFrame(x, orderService);
					orderFrame.setVisible(true);
					System.out.println(x);
				}
			});
			gridList.add(label);
		});

		gotoBackBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveToRestListView(restDetailView, restraurent.getCategory());
			}
		});
		menuListScroll.setViewportView(gridList);
	}
}
