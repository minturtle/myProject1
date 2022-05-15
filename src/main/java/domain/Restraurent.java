package domain;

import java.util.Collection;

public final class Restraurent {
    private Long id;
    private String name;
    private String address;
    private Collection<Menu> menuList;
    private Category category;

    public Restraurent(Long id, String name, String address, Collection<Menu> menuCollection, Category category) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.menuList = menuCollection;
        this.category = category;
    }

    public boolean addMenu(Menu menu){
        return menuList.add(menu);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Collection<Menu> getMenuList() {
        return menuList;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return String.format("id : %s, name : %s ", id, name) ;
    }
}
