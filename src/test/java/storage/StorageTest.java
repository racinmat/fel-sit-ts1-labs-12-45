package storage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shop.Item;
import shop.StandardItem;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.HashMap;

import static org.junit.Assert.*;

public class StorageTest {

    Storage storage;
    ByteArrayOutputStream mockOut = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {
        storage = new Storage();
        System.setOut(new PrintStream(mockOut));
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(System.out);
    }

    @Test
    public void printListOfStoredItems() {
//        System.out.println("já se vypisuju");
        storage.printListOfStoredItems();
        assertEquals("STORAGE IS CURRENTLY CONTAINING:\r\n", mockOut.toString());
    }

    @Test
    public void insertItems() throws NoSuchFieldException, IllegalAccessException {
        Item item = new StandardItem(1, "ahoj", 10, "pozdrav", 0);

        assertEquals(0, storage.getItemCount(1));
        storage.insertItems(item, 5);
        assertEquals(5, storage.getItemCount(1));

//        assertEquals(1, storage.stock.size());
//        Class<Storage> storageClass = (Class<Storage>) Class.forName(String.valueOf(Storage.class));
        Class<Storage> storageClass = Storage.class;
        Field privateField = storageClass.getDeclaredField("stock");
        //this call allows private fields to be accessed via reflection
        privateField.setAccessible(true);
        //getting value of private field using reflection
        HashMap<Integer, ItemStock> storageStock = (HashMap<Integer, ItemStock>) privateField.get(storage);

//        assertEquals(2, storageStock.size());
        assertEquals(1, storageStock.size());

    }

}