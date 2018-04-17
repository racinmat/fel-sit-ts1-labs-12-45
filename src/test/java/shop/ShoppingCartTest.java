package shop;

import helpers.ItemStub;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static shop.ShoppingCart.DISCOUNT;
import static shop.ShoppingCart.POINTS_FOR_DISCOUNT;

public class ShoppingCartTest {

    private ShoppingCart cart;

    @Before
    public void setUp() throws Exception {
       cart = new ShoppingCart();
    }

    @Test
    public void getItemsCount() {
//        arrange
        Item item = new ItemStub();
//        assert
        assertEquals(0, cart.getItemsCount());
//        act
        cart.addItem(item);
//        assert
        assertEquals(1, cart.getItemsCount());
    }

    @Test
    public void getTotalPrice() {
//        Item item = new ItemStub();
        Item mockedItem = mock(Item.class);
        when(mockedItem.getPrice()).thenReturn((float) 20);
        cart.addItem(mockedItem);

        assertEquals(20, cart.getTotalPrice(new Customer("anonymous", "4chan")));
//        verify(mockedItem, times(2)).getPrice();
        verify(mockedItem, times(1)).getPrice();
        verify(mockedItem, never()).setPrice(anyFloat());
    }

    @Test
    public void getDiscountNewCustomer() {
        Item mockedItem = mock(Item.class);
        when(mockedItem.getPrice()).thenReturn((float) 20);
        cart.addItem(mockedItem);

        Customer customer = mock(Customer.class);
        when(customer.getLoyaltyPoints()).thenReturn(0);
//        Customer customer = new Customer("a", "b");

        assertEquals(0, cart.getDiscount(customer));
    }

    @Test
    public void getDiscount() {
        Item mockedItem = mock(Item.class);
        when(mockedItem.getPrice()).thenReturn((float) 20);
        cart.addItem(mockedItem);

        Customer customer = mock(Customer.class);
        when(customer.getLoyaltyPoints()).thenReturn(POINTS_FOR_DISCOUNT);

        assertEquals(DISCOUNT, cart.getDiscount(customer));
    }

    @Test
    public void getOriginalPrice() {
        Item mockedItem = mock(Item.class);
        when(mockedItem.getPrice()).thenReturn((float) 20);
        cart.addItem(mockedItem);

        assertEquals(20, cart.getOriginalPrice());
    }

    @Test
    public void getPriceAfterDiscount() {
        Item mockedItem = mock(Item.class);
        when(mockedItem.getPrice()).thenReturn((float) 2000);
        cart.addItem(mockedItem);

        Customer customer = mock(Customer.class);
        when(customer.getLoyaltyPoints()).thenReturn(POINTS_FOR_DISCOUNT);

        assertEquals(2000 - DISCOUNT, cart.getTotalPrice(customer));
        verify(customer, times(1)).getLoyaltyPoints();
    }

    @Test
    public void getZeroPriceAfterDiscount() {
        Item mockedItem = mock(Item.class);
        when(mockedItem.getPrice()).thenReturn((float) 20);
        cart.addItem(mockedItem);

        Customer customer = mock(Customer.class);
        when(customer.getLoyaltyPoints()).thenReturn(POINTS_FOR_DISCOUNT);

        assertEquals(0, cart.getTotalPrice(customer));
        verify(customer, times(1)).getLoyaltyPoints();
    }

}