package de.tarent.challenge.store.products;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findBySku(String sku);

    Product findByName(String name);

}
