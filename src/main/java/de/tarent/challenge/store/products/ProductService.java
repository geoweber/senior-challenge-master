package de.tarent.challenge.store.products;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }


    //create and  updare
    public Product save(Product object) {
        Product saved = repository.saveAndFlush(object);
        return saved;
    }

    //read

    public List<Product> retrieveAllProducts() {
        return repository.findAll();
    }

    public Product retrieveProductBySku(String sku) {
        return repository.findBySku(sku);
    }

    public Optional<Product> retrieveProductById(Long id) {
        return repository.findById(id);
    }

    public Product retrieveProductByName(String name) {
        return repository.findByName(name);
    }


    //delete
    public void delete(Product object) {
        repository.delete(object);
    }




}
