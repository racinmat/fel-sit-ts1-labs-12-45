package shop;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.function.Supplier;


/**
 * Order is created, when an user purchases the content of the shopping cart.
 * 
 */
public class Order {

    private ArrayList<Item> items;
    String customerName;
    String customerAddress;
    int state;
    LocalDateTime created;

    public Order(ShoppingCart cart, String customerName, String customerAddress, int state, Supplier<LocalDateTime> supplier) {
        items = cart.getCartItems();
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.state = state;
        this.created = DateTimeFactory.getNow();
    }

    public Order(ShoppingCart cart, String customerName, String customerAddress, int state) {
        this(cart, customerName, customerAddress, state, LocalDateTime::now);
    }

    public Order(ShoppingCart cart, String customerName, String customerAddress) {
        this(cart, customerName, customerAddress, LocalDateTime::now);
    }

    public Order(ShoppingCart cart, String customerName, String customerAddress, Supplier<LocalDateTime> supplier) {
        items = cart.getCartItems();
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.state = 0;
        this.created = DateTimeFactory.getNow();
    }
    
    
    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> goods) {
        this.items = goods;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public LocalDateTime getCreated() {
        return created;
    }
}
