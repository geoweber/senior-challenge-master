package de.tarent.challenge.store.products;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProductValidatorTest {

    @Test
    void validate() {


        String sku = "saved sky";
        String name = "saved name";
        Set<String> eans = new HashSet<>();
        eans.add("111");
        eans.add("222");
        eans.add("333");

        int price = 1020;


        Product object = new Product(sku, name, eans, price);
//TODO Check add

        ProductValidator.getInstance().validate(object);
    }
}