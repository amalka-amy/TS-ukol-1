package jUnit.shop;

import cz.cvut.fel.ts1.shop.Item;
import cz.cvut.fel.ts1.shop.Order;
import cz.cvut.fel.ts1.shop.ShoppingCart;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class OrderTest {

    private final ArrayList<Item> items = new ArrayList<>() {};
    String customerName = "TestName";
    String customerAddress = "TestAddress";
    int state = 1;
    ShoppingCart cart = new ShoppingCart(items);
    Order orderShort = new Order(cart, customerName, customerAddress);
    Order orderLong = new Order(cart, customerName, customerAddress, state);

    @Test
    public void testConstructorShort(){
        assertEquals(items, orderShort.getItems());
        assertEquals(customerName, orderShort.getCustomerName());
        assertEquals(customerAddress, orderShort.getCustomerAddress());
        assertEquals(0, orderShort.getState());
    }

    @Test
    public void testConstructorLong(){
        assertEquals(items, orderLong.getItems());
        assertEquals(customerName, orderLong.getCustomerName());
        assertEquals(customerAddress, orderLong.getCustomerAddress());
        assertEquals(state, orderLong.getState());
    }
}
