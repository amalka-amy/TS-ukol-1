package cz.cvut.fel.ts1.shop;

import cz.cvut.fel.ts1.storage.NoItemInStorage;
import cz.cvut.fel.ts1.storage.Storage;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class EShopControllerTest {
    ArrayList<ShoppingCart> carts;
    Item ItemMock;
    Storage storage;


    @Test
    public void orderSimulationTest() throws NoItemInStorage {
        EShopController.startEShop();
        this.carts = new ArrayList<>();
        this.ItemMock = mock(Item.class);
        this.storage = new Storage();
        newCart();
        payEmptyCart();
        addToCart();
        removeFromCart();
    }

    @Test
    public void storageOrderTest() throws NoItemInStorage {
        EShopController.startEShop();
        Item Item = new Item(1,"baterka", (float) 9.9 ,"elektro");

        this.carts = new ArrayList<>();
        this.ItemMock = mock(Item.class);
        this.storage = new Storage();
        newCart();
        addToCart();
        itemCountInStorage();
        //přidávám idem do storage
        storage.insertItems(Item,2);
        EShopController.getStorage().insertItems(Item,2);
        //přidává item do košíků
        carts.get(0).addItem(Item);
        EShopController.carts.get(0).addItem(Item);
        // provádím objednávku
        EShopController.purchaseShoppingCart(carts.get(0), "Pepa", "Praha");
        EShopController.purchaseShoppingCart(EShopController.carts.get(0), "Pepa", "Praha");
        // bohužel nevím jak opravit... :/

    }

    private void itemCountInStorage(){
        assertEquals(this.storage.getItemCount(this.ItemMock), EShopController.getStorage().getItemCount(this.ItemMock));
    }

    private void newCart() {
        ShoppingCart added = EShopController.newCart();
        carts.add(added);
        assertEquals(carts, EShopController.carts);
        System.out.println("newCart - DONE");
    }

    private void addToCart() {
        carts.get(0).addItem(ItemMock);
        EShopController.carts.get(0).addItem(ItemMock);
        assertEquals(carts, EShopController.carts);
        System.out.println("addToCart - DONE");

    }

    private void removeFromCart() {
        carts.get(0).removeItem(ItemMock.getID());
        EShopController.carts.get(0).removeItem(ItemMock.getID());
        assertEquals(carts, EShopController.carts);
        System.out.println("removeFromCart - DONE");
    }

    private void payEmptyCart() throws NoItemInStorage {
        if (EShopController.carts.get(0).getItemsCount() == 0) {
            final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
            System.setErr(new PrintStream(errContent));
            String expectedOutput = "Error: shopping cart is empty\n"; // -> opraveno v kódu, vypisováno to bylo poute do out a ne do err
            EShopController.purchaseShoppingCart(EShopController.carts.get(0), "Pepa", "Praha");
            assertEquals(expectedOutput, errContent.toString());
            System.setErr(System.err);
        }else{
            System.out.println("Shopping cart is not empty");
        }
    }

}
