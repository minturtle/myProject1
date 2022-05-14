package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class User {
    private Long id;
    private String address;
    private List<MenuOrder> cart;
    private List<Order> orders;
    private Set<Payment> payments;

    public User(Long id, String address, Set<Payment> payments) {
        this.id = id;
        this.address = address;
        this.cart = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.payments = payments;
    }

    public void addCart(MenuOrder menuOrder){
        cart.add(menuOrder);
    }
    public void addOrder(Order order){
        orders.add(order);
    }
    public boolean addPayment(Payment payment){
        return payments.add(payment);
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public List<MenuOrder> getCart() {
        return cart;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Set<Payment> getPayments() {
        return payments;
    }
}
