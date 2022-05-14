package service;

import domain.Menu;

public class OrderService {


    public void addCart(Menu menu,int count){
        System.out.println("OrderService.addCart, 메뉴 :" +menu.getName() +", 갯수 :"+ count);
    }
}
