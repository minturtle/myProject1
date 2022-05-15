package service;

import domain.*;
import repository.CartRepository;
import repository.OrderRepository;

import java.util.*;

public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    public OrderService(OrderRepository orderRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
    }

    //유저의 장바구니 담기
    public void addCart(User user, Menu menu, int count){
        cartRepository.save(menu, count, user);
    }
    
    //주문을 완료하면 생기는 객체
    public Order order(User user, Payment payment){
        List<MenuOrder> cartList = getcartListByUser(user);
        Restraurent restraurent = cartList.get(0).getMenu().getRestraurent();

        //주문 객체를 생성후 저장
        Order order = orderRepository.save(user, payment, cartList, restraurent);
        cartRepository.updateOrder(order, cartList);

        //장바구니 모든 요소 비우기
        removeAllCart(cartList);
        //결제 진행
        payment.pay();
        return order;
    }

    public List<Order> getOrderListByUser(User user){
        return orderRepository.getList(user);
    }
    public List<MenuOrder> getcartListByUser(User user){
        return cartRepository.getList(user);
    }

    //유저의 장바구니의 총가격을 구하는 메서드
    public int getTotalCartPrice(Collection<MenuOrder> carts){
        int totalPrice = 0;
        Iterator<MenuOrder> iterator = carts.iterator();
        while (iterator.hasNext()){
            totalPrice += iterator.next().getTotalPrice();
        }
        return totalPrice;
    }
    public int getTotalCartPrice(User user){
        int totalPrice = 0;
        Iterator<MenuOrder> iterator = getcartListByUser(user).iterator();
        while (iterator.hasNext()){
            totalPrice += iterator.next().getTotalPrice();
        }
        return totalPrice;
    }

    public void updateCartCount(MenuOrder menuOrder, int count){
        cartRepository.updateCount(menuOrder.getId(), count);
    }
    
    //유저의 장바구니 객체 중 1개를 삭제하는 메서드
    public void removeCart(MenuOrder cart){
        cartRepository.remove(cart.getId());
    }
    public void removeCart(Long id){
        cartRepository.remove(id);
    }
    private void removeAllCart(List<MenuOrder> cartList){
        cartList.stream().forEach(cartRepository::remove);
    }
    private void removeAllCart(User user){
        cartRepository.getList(user).stream().forEach(cartRepository::remove);
    }

}
