package domain;

public final class MenuOrder {
    private Long id;
    private Order order;
    private Menu menu;
    private int count;
    private User user;

    public MenuOrder(Long id, Menu menu, int count, User user) {
        this.id = id;
        this.order = null;
        this.menu = menu;
        this.count = count;
        this.user= user;
    }

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public Menu getMenu() {
        return menu;
    }

    public User getUser() {
        return user;
    }

    public int getCount() {
        return count;
    }

    public int getTotalPrice(){
        return (count * menu.getPrice());
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
