package cz.cvut.fel.ts1.shop;

import cz.cvut.fel.ts1.shop.StandardItem;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class StandardItemTest {

    int id = 10;
    String name = "TestItem";
    float price = 100;
    String category = "TestCategory";
    int loyaltyPoints = 40;

    StandardItem testItem = new StandardItem(id, name, price,category,loyaltyPoints);


    @Test
    public void testConstructor(){
        assertEquals(id, testItem.getID());
        assertEquals(name, testItem.getName());
        assertEquals(price, testItem.getPrice(),0.001);
        assertEquals(category, testItem.getCategory());
        assertEquals(loyaltyPoints, testItem.getLoyaltyPoints());
    }

    @Test
    public void testCopy(){
        assertEquals(testItem, testItem.copy());
    }

    @Test
    public void testEquals(){
        StandardItem testItem2 = new StandardItem(id, name, price,category,loyaltyPoints);
        assertEquals(true, testItem.equals(testItem2));
    }
}
