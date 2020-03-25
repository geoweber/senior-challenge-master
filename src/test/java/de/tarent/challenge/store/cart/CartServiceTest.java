package de.tarent.challenge.store.cart;

import de.tarent.challenge.store.products.Product;
import de.tarent.challenge.store.products.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CartServiceTest {

    @Autowired
    private CartService service;

    @Autowired
    private ProductService productService;


    @Test
    void save() {

        Cart object = new Cart();
        Set<CartItem> items = new HashSet<>();
        Product product1 = productService.retrieveBySku("S-155");
        Product product2 = productService.retrieveBySku("B001");
        CartItem item1 = new CartItem(product1, 5);
        CartItem item2 = new CartItem(product2, 10);

        item1.setCart(object);
        item2.setCart(object);

        items.add(item1);
        items.add(item2);
        object.setCartItems(items);
        object.setCheckedOut(true);
        LocalDateTime checkedDate = LocalDateTime.now();
        object.setCheckedDate(checkedDate);
        object = service.save(object);


        Optional<Cart> optSavedCart = service.retrieveById(object.getId());
        Cart savedCart = optSavedCart.orElse(null);

        assertNotNull(savedCart);
        assertTrue(savedCart.isCheckedOut());
       // assertEquals(checkedDate, savedCart.getCheckedDate());

        assertEquals(items.size(), savedCart.getCartItems().size());

        assertTrue(savedCart.getCartItems().contains(item1));
        assertTrue(savedCart.getCartItems().contains(item2));

        service.delete(object);

        //Unavailable products cannot be added to a cart.
    }


    @Test
    void delete() {
        //assertEquals(1, service.retrieveAll().size());

        Cart object1 = new Cart();
        Set<CartItem> items1 = new HashSet<>();
        Product product1 = productService.retrieveBySku("S-155");
        CartItem item1 = new CartItem(product1, 5);
        items1.add(item1);
        object1.setCartItems(items1);
        object1.setCheckedOut(true);
        LocalDateTime checkedDate = LocalDateTime.now();
        object1.setCheckedDate(checkedDate);
        Cart cartToCheck = service.save(object1);

        Cart object2 = new Cart();
        Set<CartItem> items2 = new HashSet<>();
        Product product2 = productService.retrieveBySku("B001");
        CartItem item2 = new CartItem(product2, 10);
        items2.add(item2);
        object2.setCartItems(items2);
        object2.setCheckedOut(true);
        checkedDate = LocalDateTime.now();
        object2.setCheckedDate(checkedDate);
        Cart cartToRemove = service.save(object2);

        assertEquals(2, service.retrieveAll().size());

        service.delete(cartToRemove);

        assertEquals(1, service.retrieveAll().size());

        assertEquals(cartToCheck.getId(), service.retrieveAll().get(0).getId());

        service.delete(cartToCheck);
        assertTrue(service.retrieveAll().isEmpty());
    }


    @Test
    void retrieveAll() {

        assertEquals(3, service.retrieveAll().size());

        Cart object1 = new Cart();
        Set<CartItem> items1 = new HashSet<>();
        Product product1 = productService.retrieveBySku("S-155");
        CartItem item1 = new CartItem(product1, 5);
        items1.add(item1);
        object1.setCartItems(items1);
        object1.setCheckedOut(true);
        LocalDateTime checkedDate = LocalDateTime.now();
        object1.setCheckedDate(checkedDate);
        service.save(object1);

        Cart object2 = new Cart();
        Set<CartItem> items2 = new HashSet<>();
        Product product2 = productService.retrieveBySku("B001");
        CartItem item2 = new CartItem(product2, 10);
        items2.add(item2);
        object2.setCartItems(items2);
        object2.setCheckedOut(true);
        checkedDate = LocalDateTime.now();
        object2.setCheckedDate(checkedDate);
        service.save(object2);

        assertEquals(5, service.retrieveAll().size());
        for (Cart cart : service.retrieveAll()) {
            service.delete(cart);
        }
        assertTrue(service.retrieveAll().isEmpty());
    }


    @Test
    void retrieveById() {
        Cart object = new Cart();
        Set<CartItem> items = new HashSet<>();
        Product product1 = productService.retrieveBySku("S-155");
        CartItem item1 = new CartItem(product1, 5);
        item1.setCart(object);
        items.add(item1);

        object.setCartItems(items);
        object.setCheckedOut(true);
        LocalDateTime checkedDate = LocalDateTime.now();
        object.setCheckedDate(checkedDate);
        object = service.save(object);


        Optional<Cart> optSavedCart = service.retrieveById(object.getId());
        Cart savedCart = optSavedCart.orElse(null);

        assertNotNull(savedCart);
        assertTrue(savedCart.isCheckedOut());
        //assertEquals(checkedDate, savedCart.getCheckedDate());

        assertEquals(items.size(), savedCart.getCartItems().size());

        assertTrue(savedCart.getCartItems().contains(item1));
        service.delete(object);
    }


    @Test
    void retrieveByCheckedOut() {
        Cart object = new Cart();
        Set<CartItem> items = new HashSet<>();
        Product product1 = productService.retrieveBySku("S-155");
        CartItem item1 = new CartItem(product1, 5);
        item1.setCart(object);
        items.add(item1);

        object.setCartItems(items);
        object.setCheckedOut(true);
        LocalDateTime checkedDate = LocalDateTime.now();
        object.setCheckedDate(checkedDate);
        service.save(object);

        assertFalse(service.retrieveByCheckedOut(true).isEmpty());

        for (Cart cart : service.retrieveByCheckedOut(true)) {
            assertTrue(cart.isCheckedOut());
        }

        //--------------------------------------------------------


        object = new Cart();
        items = new HashSet<>();
        product1 = productService.retrieveBySku("S-155");
        item1 = new CartItem(product1, 5);
        item1.setCart(object);
        items.add(item1);

        object.setCartItems(items);
        object.setCheckedOut(false);
        checkedDate = LocalDateTime.now();
        object.setCheckedDate(checkedDate);
        service.save(object);

        assertFalse(service.retrieveByCheckedOut(false).isEmpty());

        for (Cart cart : service.retrieveByCheckedOut(false)) {
            assertFalse(cart.isCheckedOut());
        }
    }
}