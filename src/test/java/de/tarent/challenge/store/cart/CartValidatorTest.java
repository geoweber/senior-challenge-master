package de.tarent.challenge.store.cart;

import de.tarent.challenge.store.products.Product;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CartValidatorTest {

    @Test
    void validate() {


        try {
            CartValidator.getInstance().validate(null);
            fail();
        } catch (CartInvalidException ex) {
            assertEquals("Cart is null", ex.getMessage());
        }

        //Contains a non-empty list of items
        try {
            Cart object = new Cart();
            object.setCartItems(new HashSet<>());
            CartValidator.getInstance().validate(object);
            fail();
        } catch (CartInvalidException ex) {
            assertEquals("Cart contains an empty list of items", ex.getMessage());
        }

        //Each item can have a specific quantity
        try {
            Cart object = new Cart();
            Set<CartItem> items = new HashSet<>();
            Product product1 = new Product("sku1", "name1", null, 1010);
            Product product2 = new Product("sku2", "name2", null, 1020);
            CartItem item1 = new CartItem(product1, 5);
            CartItem item2 = new CartItem(product2, 0);

            items.add(item1);
            items.add(item2);
            object.setCartItems(items);
            CartValidator.getInstance().validate(object);
            fail();
        } catch (CartInvalidException ex) {
            assertEquals("The quantity for product name/sku [name2/sku2] is illegal", ex.getMessage());
        }


        // if cart is checked out  then checkout date is not null
        try {
            Cart object = new Cart();
            Set<CartItem> items = new HashSet<>();
            Product product1 = new Product("sku1", "name1", null, 1010);
            Product product2 = new Product("sku2", "name2", null, 1020);
            CartItem item1 = new CartItem(product1, 5);
            CartItem item2 = new CartItem(product2, 10);

            items.add(item1);
            items.add(item2);
            object.setCartItems(items);
            object.setCheckedOut(true);
            object.setCheckedDate(null);
            CartValidator.getInstance().validate(object);
            fail();
        } catch (CartInvalidException ex) {
            assertEquals("Data inconsistency! The cart is checked bat CheckoutDate is null", ex.getMessage());
        }


        //all right
        try {
            Cart object = new Cart();
            Set<CartItem> items = new HashSet<>();
            Product product1 = new Product("sku1", "name1", null, 1010);
            Product product2 = new Product("sku2", "name2", null, 1020);
            CartItem item1 = new CartItem(product1, 5);
            CartItem item2 = new CartItem(product2, 10);

            items.add(item1);
            items.add(item2);
            object.setCartItems(items);
            object.setCheckedOut(true);
            object.setCheckedDate(LocalDateTime.now());
            CartValidator.getInstance().validate(object);

        } catch (CartInvalidException ex) {
            fail();
        }

    }
}