package de.tarent.challenge.store.depot;


import de.tarent.challenge.store.products.Product;
import de.tarent.challenge.store.products.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepotServiceTest {

    @Autowired
    private DepotService service;

    @Autowired
    private ProductService productService;

    @Test
    void save() {

        Product product = productService.retrieveBySku("2035");
        Depot object = new Depot(product, 20);
        service.save(object);

        assertEquals(20, service.getAvailableQuantityByProduct(product));

        object.setQuantity(0);
        service.save(object);
    }


    @Test
    void getAvailableQuantityByProduct() {

        Product product = productService.retrieveBySku("B001");
        assertEquals(101, service.getAvailableQuantityByProduct(product));
    }


    @Test
    void getAllAvailableProducts() {

        List<Depot> list = service.getAllAvailableProducts();
        assertEquals(1, list.size());
        assertEquals(1L, list.get(0).getId().longValue());
        assertEquals(5, list.get(0).getProduct().getId().longValue());
        assertEquals(101, list.get(0).getQuantity());
    }


    @Test
    void delete() {

        Product product = productService.retrieveBySku("2035");
        Depot object = new Depot(product, 20);
        service.save(object);

        assertEquals(20, service.getAvailableQuantityByProduct(product));

        object.setQuantity(0);
        service.save(object);

        assertNotNull(productService.retrieveBySku("2035"));

        assertEquals(0, service.getAvailableQuantityByProduct(product));
    }
}