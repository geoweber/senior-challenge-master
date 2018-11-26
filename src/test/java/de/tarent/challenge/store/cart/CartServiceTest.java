package de.tarent.challenge.store.cart;

import de.tarent.challenge.store.products.Product;
import de.tarent.challenge.store.products.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
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

        items.add(item1);
        items.add(item2);
        object.setProducts(items);
        object.setCheckedOut(true);
        LocalDate checkedDate = LocalDate.now();
        object.setCheckedDate(checkedDate);
        object = service.save(object);


        Cart savedCart = service.retrieveById(object.getId()).get();

        assertNotNull(savedCart);
        assertTrue(savedCart.isCheckedOut());
        assertEquals(checkedDate, savedCart.getCheckedDate());

        assertEquals(items.size(), savedCart.getProducts().size());

        assertTrue(savedCart.getProducts().contains(item1));
        assertTrue(savedCart.getProducts().contains(item2));

    }

    @Test
    void retrieveAll() {
        //TODO 26.11.2018
        fail();

    }

    @Test
    void retrieveById() {
        //TODO 26.11.2018
        fail();
    }

    @Test
    void delete() {
        //TODO 26.11.2018
        fail();
    }
}