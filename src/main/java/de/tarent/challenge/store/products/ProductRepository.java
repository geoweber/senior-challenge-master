package de.tarent.challenge.store.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findBySku(String sku);

    // pay attention
    // name is not unique
    @Query("select p from Product p where LOWER(p.name) like LOWER(?1)")
    List<Product> findByName(String name);

}
