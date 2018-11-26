package de.tarent.challenge.store.products;

import org.junit.jupiter.api.Test;

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

        //- SKU: required, not empty, unique
        // 	attention: check SKU is unique occur in ProductController.save
        try {
            Product object = new Product(null, name, eans, price);
            ProductValidator.getInstance().validate(object);
            fail();
        } catch (ProductInvalidException ex) {
            assertEquals("Product.sku is empty", ex.getMessage());
        }


        try {
            Product object = new Product("    ", name, eans, price);
            ProductValidator.getInstance().validate(object);
            fail();
        } catch (ProductInvalidException ex) {
            assertEquals("Product.sku is empty", ex.getMessage());
        }


        //- Name: required, not empty

        try {
            Product object = new Product(sku, null, eans, price);
            ProductValidator.getInstance().validate(object);
            fail();
        } catch (ProductInvalidException ex) {
            assertEquals("Product.name is empty", ex.getMessage());
        }


        try {
            Product object = new Product(sku, "    ", eans, price);
            ProductValidator.getInstance().validate(object);
            fail();
        } catch (ProductInvalidException ex) {
            assertEquals("Product.name is empty", ex.getMessage());
        }


        //- Price: required, greater than 0
        try {
            Product object = new Product(sku, name, eans, 0);
            ProductValidator.getInstance().validate(object);
            fail();
        } catch (ProductInvalidException ex) {
            assertEquals("Product.price is illegal", ex.getMessage());
        }


        //- EANs: At least one, non-empty item
        try {
            Product object = new Product(sku, name, null, 1);
            ProductValidator.getInstance().validate(object);
            fail();
        } catch (ProductInvalidException ex) {
            assertEquals("Product.EANs is empty", ex.getMessage());
        }

        try {
            Product object = new Product(sku, name, new HashSet<>(), 1);
            ProductValidator.getInstance().validate(object);
            fail();
        } catch (ProductInvalidException ex) {
            assertEquals("Product.EANs is empty", ex.getMessage());
        }

        try {
            eans = new HashSet<>();
            eans.add("   ");
            Product object = new Product(sku, name, eans, 1);
            ProductValidator.getInstance().validate(object);
            fail();
        } catch (ProductInvalidException ex) {
            assertEquals("Product.EANs is empty", ex.getMessage());
        }


        // all right
        try {
            eans = new HashSet<>();
            eans.add("111");
            eans.add("222");
            eans.add("333");
            Product object = new Product(sku, name, eans, 1);
            ProductValidator.getInstance().validate(object);

        } catch (ProductInvalidException ex) {
            fail();
        }
    }
}