package repository;

import domain.Menu;
import domain.MenuOrder;
import domain.Order;
import domain.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartRepository {
    private static final Map<Long, MenuOrder> cartRepository = new HashMap<>();
    private static long cartIndexNumber = 0L;

    public MenuOrder save(Menu menu, int count, User user){
        MenuOrder menuOrder = new MenuOrder(cartIndexNumber, menu, count, user);
        cartRepository.put(cartIndexNumber++, menuOrder);
        return menuOrder;
    }

    public MenuOrder updateCount(Long id, int modifiedCount){
        MenuOrder menuOrder = cartRepository.get(id);
        if(menuOrder == null){

        }
        menuOrder.setCount(modifiedCount);
        return menuOrder;
    }
    public List<MenuOrder> updateOrder(Order order, List<MenuOrder> menuOrders){
        menuOrders.stream().forEach(cart->{
            cart.setOrder(order);
        });
        return menuOrders;
    }

    public void remove(Long id){
        cartRepository.remove(id);
    }
    public void remove(MenuOrder menuOrder){
        cartRepository.remove(menuOrder.getId());
    }

    public List<MenuOrder> getList(User user){
        return cartRepository.values().stream().filter(cart->{return cart.getUser().equals(user);}).toList();
    }
}
