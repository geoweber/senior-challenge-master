package de.tarent.challenge.store.cart;

import de.tarent.challenge.store.products.Product;
import de.tarent.challenge.store.products.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
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

        Cart object=new Cart();

        Set<CartItem> products=new HashSet<>();

        Product product=productService.retrieveProductBySku("B001");

        CartItem item1=new CartItem(product, 5);


        int total;


       // CartItem item2=new CartItem();
        //CartItem item3=new CartItem();

        service.save(object);


    }

    @Test
    void retrieveAllProducts() {
    }

    @Test
    void retrieveCartById() {
    }

    @Test
    void delete() {
    }
}