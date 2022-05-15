package domain;

public final class Menu {
    private Long id;
    private String name;
    private int price;
    private Restraurent restraurent;
    public Menu(Long id, String name, int price, Restraurent restraurent) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.restraurent = restraurent;
    }

    public Restraurent getRestraurent() {
        return restraurent;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
