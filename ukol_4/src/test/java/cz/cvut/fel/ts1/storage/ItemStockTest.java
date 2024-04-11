package cz.cvut.fel.ts1.storage;

import cz.cvut.fel.ts1.shop.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ItemStockTest {

    int id = 10;
    String name = "TestItem";
    float price = 100;
    String category = "TestCategory";

    Item item;
    ItemStock itemStock;

    private final int x;
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {2},
                {1},
                {3}
        });
    }

    public ItemStockTest(int x) {
        this.item = new Item(id,name,price,category);
        this.itemStock = new ItemStock(item);
        this.x = x;
    }

    @Test
    public void testConstructor(){
        assertEquals(item, itemStock.getItem());
        assertEquals(0, itemStock.getCount());
    }

    @Test
    public void testIncreaseItemCount() {
        int before = itemStock.getCount();
        itemStock.IncreaseItemCount(this.x);
        assertEquals((before + this.x), itemStock.getCount());
    }

    @Test
    public void testDecreaseItemCount() {
        int before = itemStock.getCount();
        itemStock.decreaseItemCount(x);
        assertEquals((before - x), itemStock.getCount());
    }
}
