package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class Order {
    private Long id;
    private List<MenuOrder> menuOrders;
    private User buyer;
    private Payment payment;
    private LocalDateTime orderedTime;
    private Restraurent restraurent;

    public Order(Long id, User buyer, Payment payment, Collection<MenuOrder> menuOrderCollection, Restraurent restraurent) {
        this.id = id;
        this.buyer = buyer;
        this.payment = payment;
        this.menuOrders = new ArrayList<>(menuOrderCollection);
        orderedTime = LocalDateTime.now();
        this.restraurent = restraurent;
    }

    public Long getId() {
        return id;
    }

    public List<MenuOrder> getMenuOrders() {
        return menuOrders;
    }

    public User getBuyer() {
        return buyer;
    }

    public Payment getPayment() {
        return payment;
    }

    public LocalDateTime getOrderedTime() {
        return orderedTime;
    }

    public Restraurent getRestraurent() {
        return restraurent;
    }

    public int getTotalPrice(){
        return menuOrders.stream().mapToInt(MenuOrder::getTotalPrice).sum();
    }
}
