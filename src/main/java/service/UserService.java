package service;

import domain.Menu;
import domain.MenuOrder;
import domain.User;

public class UserService {
    public User login(String id, String pw){
        System.out.println("MemberService.login, id=" + id +", pw="+pw);
        return new User(1L, "경상북도 구미시 거양길 18-6 205호", null);
    }

    public void addCart(User user, Menu menu, int count){
        System.out.println("UserService.addCart");
        MenuOrder menuOrder = new MenuOrder(25L, menu, count);
        user.addCart(menuOrder);
    }
}
