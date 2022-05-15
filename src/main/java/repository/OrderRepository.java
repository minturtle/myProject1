package repository;

import domain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRepository {
    private static final Map<Long, Order> orderRepository = new HashMap<>();
    private static long orderIndexNumber = 0L;

    public Order save(User user, Payment payment, List<MenuOrder> cartList, Restraurent restraurent){
        Order order = new Order(orderIndexNumber, user, payment, new ArrayList<>(cartList), restraurent);
        orderRepository.put(orderIndexNumber++, order);
        return order;
    }

    public List<Order> getList(){
        return orderRepository.values().stream().toList();
    }
    public List<Order> getList(User user){
        return orderRepository.values().stream().filter(order->{return order.getBuyer().equals(user);}).toList();
    }
}
