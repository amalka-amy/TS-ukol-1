package cz.cvut.fel.ts1.archive;

import cz.cvut.fel.ts1.shop.Item;
import cz.cvut.fel.ts1.shop.Order;
import cz.cvut.fel.ts1.shop.ShoppingCart;
import org.junit.Test;
import org.mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class PurchaseArchiveTest {

    int id = 10;
    String name = "TestItem";
    float price = 100;
    String category = "TestCategory";


    private HashMap<Integer, ItemPurchaseArchiveEntry> itemPurchaseArchive = new HashMap<>();
    private ArrayList<Order> orderArchive = new ArrayList<>();
    PurchasesArchive archiveShort = new PurchasesArchive();
    PurchasesArchive archiveLong = new PurchasesArchive(itemPurchaseArchive, orderArchive);

    @Test
    public void testPrintItemPurchaseStatisticsLong(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        StringBuilder expectedOutput = new StringBuilder("ITEM PURCHASE STATISTICS:\n");
        for(ItemPurchaseArchiveEntry e : itemPurchaseArchive.values()) {
            expectedOutput.append(e.toString()).append("\n");
        }
        archiveLong.printItemPurchaseStatistics();
        assertEquals(expectedOutput.toString(), outContent.toString());
        System.setOut(System.out);
    }
    @Test
    public void testPrintItemPurchaseStatisticsShort(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        StringBuilder expectedOutput = new StringBuilder("ITEM PURCHASE STATISTICS:\n");
        for(ItemPurchaseArchiveEntry e : itemPurchaseArchive.values()) {
            expectedOutput.append(e.toString()).append("\n");
        }
        archiveShort.printItemPurchaseStatistics();
        assertEquals(expectedOutput.toString(), outContent.toString());
        System.setOut(System.out);
    }
    @Test
    public void testGetHowManyTimesHasBeenItemSold(){
        Item itemTest = new Item(id, name,price, category);
        if(itemPurchaseArchive.containsKey(itemTest.getID())){
            int expected = itemPurchaseArchive.get(itemTest.getID()).getCountHowManyTimesHasBeenSold();
            assertEquals(expected, archiveShort.getHowManyTimesHasBeenItemSold(itemTest));
            assertEquals(expected, archiveLong.getHowManyTimesHasBeenItemSold(itemTest));
        }
        assertEquals(0,archiveShort.getHowManyTimesHasBeenItemSold(itemTest));
        assertEquals(0,archiveLong.getHowManyTimesHasBeenItemSold(itemTest));
    }
    @Test
    public void testPutOrderToPurchasesArchive_lengthOfArchive(){
        Order order = new Order(new ShoppingCart(), "Pepa", "Praha");
        ArrayList <Order> expected = archiveShort.getOrderArchive();
        expected.add(order);
        archiveShort.putOrderToPurchasesArchive(order);
        assertEquals(expected,archiveShort.getOrderArchive());
    }
    @Test
    public void testPutOrderToPurchasesArchive_lengthOfArchive_long(){
        Order order = new Order(new ShoppingCart(), "Pepa", "Praha");
        ArrayList <Order> expected = archiveLong.getOrderArchive();
        expected.add(order);
        archiveLong.putOrderToPurchasesArchive(order);
        assertEquals(expected,archiveLong.getOrderArchive());
    }
    @Test
    public void testPutOrderToPurchasesArchive_items(){
        Order order = new Order(new ShoppingCart(), "Pepa", "Praha");
        ArrayList <Order> expected = archiveShort.getOrderArchive();

        archiveShort.putOrderToPurchasesArchive(order);
        assertEquals(expected,archiveShort.getOrderArchive());
    }
    @Test
    public void testPutOrderToPurchasesArchive_items_long(){
        Order order = new Order(new ShoppingCart(), "Pepa", "Praha");
        ArrayList <Order> expected = archiveLong.getOrderArchive();

        archiveLong.putOrderToPurchasesArchive(order);
        assertEquals(expected,archiveLong.getOrderArchive());
    }
    @Test
    public void testOrderArchive_notNull(){
        ArrayList<Order> orderArchiveMock = mock(ArrayList.class);
        assertNotNull(orderArchiveMock);
    }
}
