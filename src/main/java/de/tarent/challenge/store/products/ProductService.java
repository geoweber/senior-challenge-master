package de.tarent.challenge.store.products;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        Assert.notNull(repository, "ProductRepository should not be null");
        this.repository = repository;
    }


    //create and  update
    public Product save(Product object) {
        return repository.save(object);
    }

    //read

    public List<Product> retrieveAll() {
        return repository.findAll();
    }

    public Product retrieveBySku(String sku) {
        return repository.findBySku(sku);
    }

    public Optional<Product> retrieveById(Long id) {
        return repository.findById(id);
    }

    public List<Product> retrieveByName(String name) {
        return repository.findByName(name);
    }


    //delete
    public void delete(Product object) {

        Optional<Product> product = repository.findById(object.getId());

        product.ifPresent(repository::delete);

    }


}
