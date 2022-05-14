package domain;

public final class MenuOrder {
    private Long id;
    private Order order;
    private Menu menu;
    private int count;

    public MenuOrder(Long id, Menu menu, int count) {
        this.id = id;
        this.order = null;
        this.menu = menu;
        this.count = count;
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

    public int getCount() {
        return count;
    }

    public int getTotalPrice(){
        return (count * menu.getPrice());
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
