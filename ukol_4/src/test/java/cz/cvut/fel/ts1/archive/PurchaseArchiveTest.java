package cz.cvut.fel.ts1.archive;

import cz.cvut.fel.ts1.shop.Item;
import cz.cvut.fel.ts1.shop.Order;
import cz.cvut.fel.ts1.shop.ShoppingCart;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class PurchaseArchiveTest {

    int id = 10;
    String name = "TestItem";
    float price = 100;
    String category = "TestCategory";


    private final HashMap<Integer, ItemPurchaseArchiveEntry> itemPurchaseArchive = new HashMap<>();
    private final ArrayList<Order> orderArchive = new ArrayList<>();
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
        ArrayList<Item> items = order.getItems();
        for(Item i : items){
            if(itemPurchaseArchive.containsKey(i.getID())){
                ItemPurchaseArchiveEntry e = itemPurchaseArchive.get(i.getID());
                e.increaseCountHowManyTimesHasBeenSold(1);
            }else{
                itemPurchaseArchive.put(i.getID(), new ItemPurchaseArchiveEntry(i));
            }
        }

        archiveShort.putOrderToPurchasesArchive(order);
        assertEquals(expected,archiveShort.getOrderArchive());

    }

    @Test
    public void testPutOrderToPurchasesArchive_items_long(){
        Order order = new Order(new ShoppingCart(), "Pepa", "Praha");
        ArrayList <Order> expected = archiveLong.getOrderArchive();
        ArrayList<Item> items = order.getItems();
        for(Item i : items){
            if(itemPurchaseArchive.containsKey(i.getID())){
                ItemPurchaseArchiveEntry e = itemPurchaseArchive.get(i.getID());
                e.increaseCountHowManyTimesHasBeenSold(1);
            }else{
                itemPurchaseArchive.put(i.getID(), new ItemPurchaseArchiveEntry(i));
            }
        }

        archiveLong.putOrderToPurchasesArchive(order);
        assertEquals(expected,archiveLong.getOrderArchive());

    }

}
