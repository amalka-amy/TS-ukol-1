package cz.cvut.fel.ts1.archive;

import cz.cvut.fel.ts1.shop.Item;
import org.junit.Test;
import org.mockito.internal.matchers.Equals;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class ItemPurchaseArchiveEntryTest {
    private int soldCount;
    @Test
    public void testItemPurchaseArchiveEntry_soldCountIsOne(){
        Item refItemMock = mock(Item.class);
        ItemPurchaseArchiveEntry ItemPurchaseArchiveEntryTest = new ItemPurchaseArchiveEntry(refItemMock);
        assertEquals(1,ItemPurchaseArchiveEntryTest.getSoldCount());
    }
    @Test
    public void testItemPurchaseArchiveEntry_notNull(){
        HashMap<Integer, ItemPurchaseArchiveEntry> itemPurchaseArchiveEntryMock = mock(HashMap.class);
        assertNotNull(itemPurchaseArchiveEntryMock);
    }
}
