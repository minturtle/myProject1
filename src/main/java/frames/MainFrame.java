package frames;

import config.MyConfig;
import domain.*;
import service.OrderService;
import service.RestService;
import service.UserService;

import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

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

	//cartView
	private JLabel totalPriceLabel;
	private JButton cartOrderBtn;
	private JScrollPane cartListScroll;

	//orderView
	private JLabel phoneNumLabel;
	private JLabel addressLabel;
	private JComboBox paymentList;
	private JLabel orderTotalPrice;
	private JButton orderBtn;

	//orderDetailView
	private JScrollPane menuOrderListScroll;
	private JLabel orderDetailTotalPrice;
	private JLabel paymentLabel;
	private JLabel orderDetailAddressLabel;

	//orderListView
	private JScrollPane orderListScroll;

	private JPanel loginView;
	private JPanel mainView;
	private JPanel restListView;
	private JPanel restDetailView;
	private JPanel cartView;
	private JPanel orderView;
	private JPanel orderDetailView;
	private JPanel orderListView;

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
		frame.setTitle("?????? ?????? ????????????");
		frame.setBounds(100, 100, 450, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().setLayout(null);

		loginView = initLoginView();
		mainView = initMainView();
		restListView = initRestListView();
		restDetailView = initRestDetailView();
		cartView = initCartView();
		orderView = initOrderView();
		orderDetailView = initOrderDetailView();
		orderListView = initOrderListView();

		//???????????? ??????????????? ?????????
		mainView.setVisible(false);
		restListView.setVisible(false);
		restDetailView.setVisible(false);
		orderView.setVisible(false);
		orderDetailView.setVisible(false);
		orderListView.setVisible(false);

		frame.getContentPane().add(loginView);
		frame.getContentPane().add(mainView);
		frame.getContentPane().add(restListView);

	}
	private JPanel initCartView(){
		JPanel cartView_1 = new JPanel();
		cartView_1.setBounds(0, 0, 436, 563);
		frame.getContentPane().add(cartView_1);
		cartView_1.setLayout(null);
		cartView_1.setVisible(false);


		JPanel topNav_1 = new JPanel();
		topNav_1.setLayout(null);
		topNav_1.setBounds(0, 0, 436, 30);
		cartView_1.add(topNav_1);

		JButton btnBack = new JButton("????????????");
		btnBack.setBounds(0, 0, 82, 33);
		topNav_1.add(btnBack);

		JLabel lblNewLabel = new JLabel("????????????");
		lblNewLabel.setFont(new Font("??????", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(168, 0, 90, 33);
		topNav_1.add(lblNewLabel);


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 30, 436, 366);
		cartListScroll = scrollPane;
		cartView_1.add(scrollPane);

		JLabel lblNewLabel_1 = new JLabel("??? ?????? :");
		lblNewLabel_1.setBounds(29, 406, 82, 42);
		cartView_1.add(lblNewLabel_1);

		JLabel totalPrice = new JLabel("??????");
		totalPriceLabel = totalPrice;
		totalPrice.setHorizontalAlignment(SwingConstants.CENTER);
		totalPrice.setBounds(318, 406, 82, 42);
		cartView_1.add(totalPrice);

		JButton orderBtn_1 = new JButton("0??? ?????? ????????????");
		this.cartOrderBtn = orderBtn_1;
		orderBtn_1.setBounds(0, 487, 436, 66);
		cartView_1.add(orderBtn_1);

		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveToMainView(cartView_1);
			}
		});
		orderBtn_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveToOrderView(cartView_1);
			}
		});
		return cartView_1;
	}

	private JPanel initOrderView(){
		JPanel orderView = new JPanel();
		orderView.setBounds(0, 0, 436, 563);
		frame.getContentPane().add(orderView);
		orderView.setLayout(null);

		JPanel topNav_1 = new JPanel();
		topNav_1.setLayout(null);
		topNav_1.setBounds(0, 0, 436, 30);
		orderView.add(topNav_1);

		JButton btnBack_1 = new JButton("????????????");
		btnBack_1.setBounds(0, 0, 82, 33);
		topNav_1.add(btnBack_1);

		JLabel lblOrder = new JLabel("????????????");
		lblOrder.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrder.setFont(new Font("??????", Font.PLAIN, 15));
		lblOrder.setBounds(168, 0, 90, 33);
		topNav_1.add(lblOrder);

		JPanel deliveryInfoPane = new JPanel();
		deliveryInfoPane.setBounds(0, 28, 436, 205);
		orderView.add(deliveryInfoPane);
		deliveryInfoPane.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("?????? ??????");
		lblNewLabel_2.setFont(new Font("??????", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(12, 10, 97, 41);
		deliveryInfoPane.add(lblNewLabel_2);

		JLabel addressLabel = new JLabel("??????");
		this.addressLabel = addressLabel;

		addressLabel.setBounds(12, 61, 412, 46);
		deliveryInfoPane.add(addressLabel);

		JLabel phoneNumLabel = new JLabel("?????? ??????");
		this.phoneNumLabel = phoneNumLabel;

		phoneNumLabel.setBounds(12, 117, 261, 60);
		deliveryInfoPane.add(phoneNumLabel);

		JPanel paymentChoosePane = new JPanel();
		paymentChoosePane.setBounds(0, 231, 436, 152);
		orderView.add(paymentChoosePane);
		paymentChoosePane.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("????????????");
		lblNewLabel_3.setFont(new Font("??????", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(12, 10, 108, 31);
		paymentChoosePane.add(lblNewLabel_3);

		JComboBox paymentList = new JComboBox();
		this.paymentList = paymentList;

		paymentList.setBounds(12, 69, 233, 23);
		paymentChoosePane.add(paymentList);

		JButton addPaymentBtn = new JButton("???????????? ??????");
		addPaymentBtn.setBounds(268, 69, 137, 23);
		paymentChoosePane.add(addPaymentBtn);

		JLabel lblNewLabel_4 = new JLabel("??? ????????????");
		lblNewLabel_4.setFont(new Font("??????", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(10, 393, 82, 39);
		orderView.add(lblNewLabel_4);

		JLabel totalPrice = new JLabel("1???");
		this.orderTotalPrice = totalPrice;

		totalPrice.setHorizontalAlignment(SwingConstants.CENTER);
		totalPrice.setBounds(308, 443, 116, 39);
		orderView.add(totalPrice);

		JButton orderBtn = new JButton("1??? ????????????");
		this.orderBtn = orderBtn;
		orderBtn.setFont(new Font("??????", Font.PLAIN, 15));
		orderBtn.setBounds(0, 492, 436, 66);
		orderView.add(orderBtn);

		//???????????? ?????????
		btnBack_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveToCartView(orderView);
			}
		});
		//???????????? ?????? ?????????
		addPaymentBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame payment = new PaymentsFormFrame(userService, user);
				payment.setVisible(true);
				moveToOrderView(orderView);
			}
		});
		orderBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//????????????, view??? ??????
				Order order = orderService.order(user, (Payment) (paymentList.getSelectedItem()));
				moveToOrderDetailView(orderView, order);
			}
		});
		return orderView;
	}
	private JPanel initRestListView(){
		JPanel RestListView = new JPanel();

		RestListView.setBounds(0, 0, 436, 563);

		RestListView.setLayout(null);


		categoryHeaderLabel = new JLabel("????????????");
		categoryHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		categoryHeaderLabel.setBounds(0, 0, 436, 48);
		JButton gotoBackBtn = new JButton("????????????");
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

		JButton searchBtn = new JButton("??????");
		underLineSpace.add(searchBtn);

		JButton jjimBtn = new JButton("???");
		underLineSpace.add(jjimBtn);

		JButton orderListBtn = new JButton("????????????");
		underLineSpace.add(orderListBtn);

		JButton profileBtn = new JButton("My??????");
		underLineSpace.add(profileBtn);

		orderListBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveToOrderListView(restListView);
			}
		});

		return RestListView;

	}
	private JPanel initMainView() {
		JPanel mainView = new JPanel();
		mainView.setBounds(0, 0, 436, 563);

		mainView.setLayout(null);

		userAddressLabel = new JLabel("?????????");
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

		JButton searchBtn = new JButton("??????");
		JButton jjimBtn = new JButton("???");
		JButton orderListBtn = new JButton("????????????");
		JButton profileBtn = new JButton("My??????");

		underLineSpace.add(searchBtn);
		underLineSpace.add(jjimBtn);
		underLineSpace.add(orderListBtn);
		underLineSpace.add(profileBtn);


		Category[] categoryList = Category.values();

		for (int i = 0; i < 17; i++) {
			Category category = categoryList[i];
			JButton categoryChooseBtn = new JButton(category.toString());
			categoryChooseBtn.addActionListener(new ActionListener() {
				//?????? ????????? ??????????????? ????????? ??? ???????????? ?????????
				@Override
				public void actionPerformed(ActionEvent e) {
					//?????? ??????
					moveToRestListView(mainView, category);
				}
			});
			categoryGridSpace.add(categoryChooseBtn);
		}

		orderListBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveToOrderListView(mainView);
			}
		});
		return mainView;
	}
	private JPanel initLoginView(){
		JPanel loginView = new JPanel();
		loginView.setBounds(0, 0, 436, 263);
		loginView.setLayout(null);

		JLabel headerLabel = new JLabel("\uBC30\uB2EC \uC8FC\uBB38 \uD504\uB85C\uADF8\uB7A8");
		headerLabel.setBounds(12, 10, 436, 24);
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerLabel.setFont(new Font("??????????????????", Font.PLAIN, 20));
		loginView.add(headerLabel);

		JLabel idLabel = new JLabel("\uC544\uC774\uB514");
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setFont(new Font("??????????????????", Font.PLAIN, 15));
		idLabel.setBounds(89, 91, 62, 21);
		loginView.add(idLabel);

		idField = new JTextField();
		idField.setBounds(186, 91, 190, 21);
		loginView.add(idField);
		idField.setColumns(10);

		JLabel pwLabel = new JLabel("\uBE44\uBC00\uBC88\uD638");
		pwLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pwLabel.setFont(new Font("??????????????????", Font.PLAIN, 15));
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
				if(user == null){
					JOptionPane.showMessageDialog(null,"????????? ??????!");
				}
				else {
					frame.setSize(450, 600);
					moveToMainView(loginView);
					userAddressLabel.setText(user.getAddress());
				}
			}
		});

		return loginView;
	}
	private JPanel initRestDetailView() {
		JPanel RestDetailView = new JPanel();
		RestDetailView.setBounds(0, 0, 436, 563);
		frame.getContentPane().add(RestDetailView);
		RestDetailView.setLayout(null);

		JButton cartBtn = new JButton("????????????");
		cartBtn.setBounds(354, 502, 82, 51);
		RestDetailView.add(cartBtn);

		JLabel restNameLabel = new JLabel("?????? ??????");
		this.restNameLabel = restNameLabel;
		restNameLabel.setFont(new Font("??????", Font.PLAIN, 20));
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

		JButton gotoBackBtn = new JButton("????????????");
		this.gotoBackBtn = gotoBackBtn;
		gotoBackBtn.setBounds(0, 0, 82, 33);
		topNav.add(gotoBackBtn);

		cartBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveToCartView(restDetailView);
			}
		});
		return RestDetailView;
	}
	private JPanel initOrderDetailView(){
		JPanel orderDetailView = new JPanel();
		orderDetailView.setBounds(0, 0, 436, 563);
		frame.getContentPane().add(orderDetailView);
		orderDetailView.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("?????? ????????????");
		lblNewLabel_5.setBounds(12, 25, 118, 43);
		lblNewLabel_5.setFont(new Font("??????", Font.PLAIN, 15));
		orderDetailView.add(lblNewLabel_5);

		JScrollPane menuOrderList = new JScrollPane();
		menuOrderListScroll = menuOrderList;

		menuOrderList.setBounds(0, 127, 436, 202);
		orderDetailView.add(menuOrderList);

		JLabel lblNewLabel_6 = new JLabel("??????");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(12, 98, 62, 19);
		orderDetailView.add(lblNewLabel_6);

		JLabel lblNewLabel_6_1 = new JLabel("??????");
		lblNewLabel_6_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6_1.setBounds(197, 98, 62, 19);
		orderDetailView.add(lblNewLabel_6_1);

		JLabel lblNewLabel_6_2 = new JLabel("??????");
		lblNewLabel_6_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6_2.setBounds(285, 98, 62, 19);
		orderDetailView.add(lblNewLabel_6_2);

		JLabel lblNewLabel_6_3 = new JLabel("??????");
		lblNewLabel_6_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6_3.setBounds(359, 98, 62, 19);
		orderDetailView.add(lblNewLabel_6_3);

		JButton btnNewButton_1 = new JButton("??????");
		btnNewButton_1.setFont(new Font("??????", Font.PLAIN, 15));
		btnNewButton_1.setBounds(0, 489, 436, 64);
		orderDetailView.add(btnNewButton_1);

		JLabel lblNewLabel_7 = new JLabel("??? ????????????");
		lblNewLabel_7.setFont(new Font("??????", Font.PLAIN, 15));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(10, 339, 96, 28);
		orderDetailView.add(lblNewLabel_7);

		JLabel totalPrice = new JLabel("New label");
		totalPrice.setHorizontalAlignment(SwingConstants.CENTER);
		totalPrice.setBounds(338, 339, 83, 28);
		orderDetailView.add(totalPrice);
		orderDetailTotalPrice = totalPrice;

		JLabel lblNewLabel_8 = new JLabel("?????? ??????");
		lblNewLabel_8.setBounds(33, 395, 72, 19);
		orderDetailView.add(lblNewLabel_8);

		JLabel paymentLabel = new JLabel("New label");
		paymentLabel.setBounds(156, 397, 83, 15);
		orderDetailView.add(paymentLabel);
		this.paymentLabel = paymentLabel;
		JLabel lblNewLabel_9 = new JLabel("??????");
		lblNewLabel_9.setBounds(33, 424, 72, 19);
		orderDetailView.add(lblNewLabel_9);

		JLabel orderAddressLabel = new JLabel("New label");
		this.orderDetailAddressLabel = orderAddressLabel;
		orderAddressLabel.setBounds(156, 426, 268, 43);
		orderDetailView.add(orderAddressLabel);

		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveToMainView(orderDetailView);
			}
		});

		return orderDetailView;
	}
	private JPanel initOrderListView(){
		JPanel orderListView = new JPanel();
		orderListView.setBounds(0, 0, 436, 563);
		frame.getContentPane().add(orderListView);
		orderListView.setLayout(null);

		JPanel topNav_1 = new JPanel();
		topNav_1.setLayout(null);
		topNav_1.setBounds(0, 0, 436, 30);
		orderListView.add(topNav_1);

		JButton btnBack_1 = new JButton("????????????");
		btnBack_1.setBounds(0, 0, 82, 33);
		topNav_1.add(btnBack_1);

		JLabel lblOrder = new JLabel("????????????");
		lblOrder.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrder.setFont(new Font("??????", Font.PLAIN, 15));
		lblOrder.setBounds(168, 0, 90, 33);
		topNav_1.add(lblOrder);

		JScrollPane scrollPane = new JScrollPane();
		orderListScroll = scrollPane;
		scrollPane.setBounds(440, 89, -434, 464);
		orderListView.add(scrollPane);

		btnBack_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveToMainView(orderListView);
			}
		});
		return orderListView;
	}


	private void moveToMainView(JPanel preView){
		preView.setVisible(false);
		mainView.setVisible(true);
	}
	private void moveToRestListView(JPanel preView, Category category){
		restListView.setVisible(true);
		preView.setVisible(false);
		categoryHeaderLabel.setText(category.toString());
		//??????????????? ???????????? ?????? ????????? list??? ????????????
		Collection<Restraurent> restraurents = restService.findRestByCategory(category);
		JPanel gridList = new JPanel(new GridLayout(restraurents.size(), 1));


		//??? restraunt????????? label??? ????????? gridList??? ?????????
		restraurents.stream().forEach(x->{
			JLabel label = new JLabel(x.getName());
			label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					moveToRestDetailView(restListView, x);
				}
			});
			gridList.add(label);
		});

		restListScroll.setViewportView(gridList);
	}
	private void moveToRestDetailView(JPanel preView, Restraurent restraurent){
		preView.setVisible(false);
		restDetailView.setVisible(true);

		restNameLabel.setText(restraurent.getName());

		JPanel gridList = new JPanel(new GridLayout(restraurent.getMenuList().size(), 1));

		restraurent.getMenuList().stream().forEach(x->{
			JLabel label = new JLabel(String.format("%s (%s)", x.getName(), x.getPrice()));
			label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					OrderFrame orderFrame = new OrderFrame(user, x, orderService);
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
	private void moveToCartView(JPanel preView){
		preView.setVisible(false);
		cartView.setVisible(true);

		List<MenuOrder> cartList = orderService.getcartListByUser(user);

		JPanel cartListGrid= new JPanel(new GridLayout(cartList.size(), 1));

		cartList.stream().forEach(cart->{
			JPanel cartElement = new JPanel();
			cartElement.setBounds(0, 396, 436, 54);
			cartElement.setLayout(null);

			cartListGrid.add(cartElement);

			JLabel menuName = new JLabel(cart.getMenu().getName());
			menuName.setHorizontalAlignment(SwingConstants.CENTER);
			menuName.setBounds(0, 0, 289, 34);
			cartElement.add(menuName);

			JButton deleteBtn = new JButton("x");
			deleteBtn.setBounds(385, 6, 50, 50);
			cartElement.add(deleteBtn);

			JLabel lblNumberPrice = new JLabel("??????:" + cart.getCount() + ", ??????" + cart.getTotalPrice());
			lblNumberPrice.setHorizontalAlignment(SwingConstants.CENTER);
			lblNumberPrice.setBounds(0, 31, 289, 23);
			cartElement.add(lblNumberPrice);

			deleteBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					orderService.removeCart(cart);
					moveToCartView(cartView);
				}
			});
		});
		cartListScroll.setViewportView(cartListGrid);
		int totalPrice = orderService.getTotalCartPrice(cartList);

		totalPriceLabel.setText(Integer.toString(totalPrice)+"???");
		cartOrderBtn.setText(totalPrice+"??? ????????????");
	}
	private void moveToOrderView(JPanel preView){
		preView.setVisible(false);
		orderView.setVisible(true);

		addressLabel.setText(user.getAddress());
		phoneNumLabel.setText(user.getPhoneNum());
		user.getPayments().stream().forEach(payment -> {
			paymentList.addItem(payment);
		});

		int totalPrice = orderService.getTotalCartPrice(user);
		orderTotalPrice.setText(totalPrice +"???");
		orderBtn.setText(totalPrice + "??? ????????????");
	}
	private void moveToOrderDetailView(JPanel preview, Order order){
		preview.setVisible(false);
		orderDetailView.setVisible(true);

		JPanel menuOrderGrid = new JPanel(new GridLayout(order.getMenuOrders().size(), 1));
		order.getMenuOrders().stream().forEach(menuOrder -> {
			JPanel orderDetailElement = createOrderDetailElement(menuOrder);
			menuOrderGrid.add(orderDetailElement);
		});
		orderDetailTotalPrice.setText(order.getTotalPrice() + "???");
		paymentLabel.setText(order.getPayment().toString());
		orderDetailAddressLabel.setText(order.getBuyer().getAddress());

		menuOrderListScroll.setViewportView(menuOrderGrid);
	}
	private void moveToOrderListView(JPanel preView){
		preView.setVisible(false);
		orderListView.setVisible(true);

		JPanel orderListGrid = new JPanel(new GridLayout(orderService.getOrderListByUser(user).size(), 1));
		orderService.getOrderListByUser(user).stream().forEach(order -> {
			orderListGrid.add(createOrderListViewElement(order));
		});
		orderListScroll.setViewportView(orderListGrid);
	}

	private JPanel createOrderDetailElement(MenuOrder menuOrder){
		JPanel menuOrderElement = new JPanel();
		menuOrderElement.setBounds(0, 355, 436, 48);
		orderDetailView.add(menuOrderElement);
		menuOrderElement.setLayout(null);

		JLabel name = new JLabel(menuOrder.getMenu().getName());
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setBounds(0, 10, 175, 28);
		menuOrderElement.add(name);

		JLabel price = new JLabel(Integer.toString(menuOrder.getMenu().getPrice()));
		price.setHorizontalAlignment(SwingConstants.CENTER);
		price.setBounds(174, 13, 87, 22);
		menuOrderElement.add(price);

		JLabel quantity = new JLabel(Integer.toString(menuOrder.getCount()));
		quantity.setHorizontalAlignment(SwingConstants.CENTER);
		quantity.setBounds(266, 13, 87, 22);
		menuOrderElement.add(quantity);

		JLabel totalPrice = new JLabel(menuOrder.getTotalPrice() + "???");
		totalPrice.setHorizontalAlignment(SwingConstants.CENTER);
		totalPrice.setBounds(349, 13, 87, 22);
		menuOrderElement.add(totalPrice);
		return menuOrderElement;
	}
	private JPanel createOrderListViewElement(Order order){
		JPanel orderElement = new JPanel();
		orderElement.setBounds(0, 42, 436, 48);
		orderListView.add(orderElement);
		orderElement.setLayout(null);

		JLabel orderDateLabel = new JLabel(getDate(order.getOrderedTime()));
		orderDateLabel.setBounds(12, 0, 93, 15);
		orderElement.add(orderDateLabel);

		JLabel orderRestNameLabel = new JLabel(order.getRestraurent().getName());
		orderRestNameLabel.setFont(new Font("??????", Font.PLAIN, 15));
		orderRestNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		orderRestNameLabel.setBounds(86, -7, 298, 28);
		orderElement.add(orderRestNameLabel);

		JLabel lblNewLabel_10 = new JLabel(getOrderListDetail(order));
		lblNewLabel_10.setBounds(86, 23, 298, 15);
		orderElement.add(lblNewLabel_10);

		JButton goToDetailBtn = new JButton("????????????");
		goToDetailBtn.setBounds(358, -2, 78, 40);
		orderElement.add(goToDetailBtn);
		orderElement.setVisible(true);
		return orderElement;
	}

	private String getDate(LocalDateTime localDateTime){
		String[] weeks = new String[]{"???", "???", "???", "???", "???", "???", "???"};
		String w= weeks[localDateTime.getDayOfWeek().getValue()-1];
		return localDateTime.format(DateTimeFormatter.ofPattern("yy/mm/dd"))+"(" + "w" + ")";
	}
	private String getOrderListDetail(Order order){
		MenuOrder representOrderMenu = order.getMenuOrders().get(0);
		String name = representOrderMenu.getMenu().getName();
		if(order.getMenuOrders().size() == 1){
			name += (representOrderMenu.getCount() + "???" + representOrderMenu.getTotalPrice() + "???");
			return name;
		}
		name += ("???" + order.getMenuOrders().size() +"???" + order.getTotalPrice());
		return name;
	}
}
