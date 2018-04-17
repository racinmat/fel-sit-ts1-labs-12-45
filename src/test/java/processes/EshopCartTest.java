package processes;

import archive.PurchasesArchive;
import org.junit.Test;
import shop.*;
import storage.NoItemInStorage;
import storage.Storage;
import static org.junit.Assert.*;

public class EshopCartTest {


    public static final int FIRST_ITEM_PRICE = 5000;
    public static final int ITEM_ONE_STORAGE_COUNT = 4;

    @Test
    public void testOrderProcessing() throws NoItemInStorage {
        Storage storage;
        PurchasesArchive archive;
//        arrange
        storage = new Storage();
        archive = new PurchasesArchive();

        int[] itemCounts = new int[]{ITEM_ONE_STORAGE_COUNT, 5, 2};
        Item[] storageItems = {
            new StandardItem(1, "Dancing Panda v.2", FIRST_ITEM_PRICE, "GADGETS", 5),
            new StandardItem(2, "Dancing Panda v.3 with USB port", 6000, "GADGETS", 10),
            new DiscountedItem(6, "Soft toy Angry bird (size 40cm)", 800, "GADGETS", 10, "1.8.2013", "1.12.2013")
        };

//        assert
        assertTrue(storage.getStockEntries().isEmpty());

//        act
        for (int i = 0; i < storageItems.length; i++) {
            storage.insertItems(storageItems[i], itemCounts[i]);
        }

//        assert
        assertEquals(3, storage.getStockEntries().size());
        assertEquals(ITEM_ONE_STORAGE_COUNT, storage.getItemCount(storageItems[0]));
        assertEquals(5, storage.getItemCount(storageItems[1]));
        assertEquals(2, storage.getItemCount(storageItems[2]));

//        act
        ShoppingCart cart = new ShoppingCart();
        Item cartItem1 = storageItems[0];
        cart.addItem(cartItem1);

//        assert
        assertEquals(1, cart.getItemsCount());
        assertEquals(FIRST_ITEM_PRICE, cart.getTotalPrice(new Customer("anonymous", "4chan")));
        assertEquals(cartItem1, cart.getCartItems().get(0));

//        act
        Order order = new Order(cart, "Test customer 1", "Příčná ulice");

//        assert
        assertEquals(cartItem1, order.getItems().get(0));

//        act
        storage.processOrder(order);

//        assert
        assertEquals(ITEM_ONE_STORAGE_COUNT - 1, storage.getItemCount(cartItem1));
    }

}
