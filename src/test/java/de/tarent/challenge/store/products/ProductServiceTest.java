package de.tarent.challenge.store.products;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService service;


    @Test
    void save() {

        String sku = "saved sky";
        String name = "saved name";
        Set<String> eans = new HashSet<>();
        eans.add("111");
        eans.add("222");
        eans.add("333");

        int price = 1020;


        {   //test create
            Product object = new Product(sku, name, eans, price);
            assertNotNull(service.save(object));
        }

        {   //test update
            Product object = service.retrieveProductBySku(sku);

            //sku
            String skuUPD = sku + "UPD";
            assertNull(service.retrieveProductBySku(skuUPD));

            object.setSku(skuUPD);
            service.save(object);
            assertNotNull(service.retrieveProductBySku(skuUPD));

            //name
            String nameUPD = name + "UPD";
            assertTrue(service.retrieveProductByName(nameUPD).isEmpty());

            object.setName(nameUPD);
            service.save(object);
            assertNotNull(service.retrieveProductByName(nameUPD));

            //eans
            String eansUpd = "444";

            Product productExpected = service.retrieveProductByName(nameUPD).get(0);


            assertFalse(productExpected.getEans().contains(eansUpd));

            eans.add(eansUpd);
            productExpected.setEans(eans);
            service.save(productExpected);

            Product productReal = service.retrieveProductBySku(skuUPD);

            assertTrue(productReal.getEans().contains(eansUpd));

        }


        //------------------------------------------------
        // test for SKU field: required, not empty, unique
        {


            try {
                Product object = new Product(null, name, eans, price);
                service.save(object);
                fail("SKU darf nicht leer sein");
            } catch (ConstraintViolationException ex) {
                assertNotNull(ex.getMessage());
            }


            try {
                Product object = new Product("   ", name, eans, price);
                service.save(object);
                fail("SKU darf nicht leer sein");
            } catch (ConstraintViolationException ex) {
                assertNotNull(ex.getMessage());
            }

            try {
                final String NOT_UNIQUE_SKU_VALUE = "NOT_UNIQUE_SKU_VALUE";

                Product object = new Product(NOT_UNIQUE_SKU_VALUE, name, eans, price);
                service.save(object);

                object = new Product(NOT_UNIQUE_SKU_VALUE, name, eans, price);
                service.save(object);
                fail("Unique index  violation for column SKU");
            } catch (DataIntegrityViolationException ex) {
                assertNotNull(ex.getMessage());
            }
        }

        //-----------------------------------------------
        {
            try {
                Product object = new Product(sku, name, eans, -1020);
                service.save(object);
                fail("Price muss positiv sein");
            } catch (ConstraintViolationException ex) {
                assertNotNull(ex.getMessage());
            }
        }


        //------------------------------------------------
        // test for Name field: required, not empty

        {


            try {
                Product object = new Product(sku, null, eans, price);
                service.save(object);
                fail("Name darf nicht null sein");
            } catch (ConstraintViolationException ex) {
                assertNotNull(ex.getMessage());
            }


            try {
                Product object = new Product(sku, "    ", eans, price);
                service.save(object);
                fail("Name darf nicht leer sein");
            } catch (ConstraintViolationException ex) {
                assertNotNull(ex.getMessage());
            }
        }


        // test for EANs: At least one, non-empty item

        try {
            Product object = new Product(sku, name, null, price);
            service.save(object);
            fail("Eans darf nicht leer sein");
        } catch (ConstraintViolationException ex) {
            assertNotNull(ex.getMessage());
        }

        try {
            Set<String> empty = new HashSet<>();
            Product object = new Product(sku, name, empty, price);
            service.save(object);
            fail("Eans darf nicht leer sein");
        } catch (ConstraintViolationException ex) {
            assertNotNull(ex.getMessage());
        }

        /*
        TODO check ob eans have at least one not empty element occur ny validation

        */
    }


    @Test
    void retrieveAllProducts() {
        assertNotNull(service.retrieveAllProducts());
    }

    @Test
    void retrieveProductBySku() {
        assertEquals("Couscous", service.retrieveProductBySku("B001").getName());
    }

    @Test
    void retrieveById() {
        Optional<Product> product = service.retrieveProductById(5L);

        assertTrue(product.isPresent());

        assertEquals("Couscous", product.get().getName());
    }

    @Test
    void retrieveByName() {
        assertEquals("B001", service.retrieveProductByName("Couscous").get(0).getSku());
    }

    @Test
    void delete() {

        String sku = "saved sky";
        String name = "saved name";
        Set<String> eans = new HashSet<>();
        eans.add("111");
        eans.add("222");
        eans.add("333");
        Integer price = 1020;

        Product object = new Product(sku, name, eans, price);
        Product saved = service.save(object);
        assertNotNull(saved);

        assertEquals(sku, service.retrieveProductByName(name).get(0).getSku());

        service.delete(object);

        assertTrue(service.retrieveProductByName(name).isEmpty());


    }
}