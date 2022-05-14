package config;

import frames.MainFrame;
import service.OrderService;
import service.RestService;
import service.UserService;

public final class MyConfig {
    private MyConfig() {
    }

    public static UserService userService(){
        return new UserService();
    }
    public static RestService restService(){return new RestService();}
    public static OrderService orderService(){ return new OrderService();}
    public static MainFrame mainFrame(){
        return new MainFrame(userService(), restService(), orderService());
    }
}
