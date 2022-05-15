package config;

import frames.MainFrame;
import repository.CartRepository;
import repository.OrderRepository;
import service.OrderService;
import service.RestService;
import service.UserService;

public final class MyConfig {
    private final static UserService userService;
    private final static RestService restService;
    private final static OrderService orderService;
    private final static MainFrame mainFrame;

    private final static OrderRepository orderRepository;
    private final static CartRepository cartRepository;
    static{
        orderRepository = new OrderRepository();
        userService = new UserService();
        cartRepository = new CartRepository();
        restService = new RestService();
        orderService = new OrderService(orderRepository, cartRepository);
        mainFrame = new MainFrame(userService(), restService(), orderService());
    }

    private MyConfig() {
    }

    public static UserService userService(){return new UserService();}
    public static RestService restService(){return restService;}
    public static OrderService orderService(){ return orderService;}
    public static MainFrame mainFrame(){return mainFrame;}
}
