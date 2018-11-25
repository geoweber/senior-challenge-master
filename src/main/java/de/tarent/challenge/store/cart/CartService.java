package de.tarent.challenge.store.cart;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository repository;

    public CartService(CartRepository repository) {
        this.repository = repository;
    }

    //create and  updare
    public Cart save(Cart object) {
        Cart saved = repository.save(object);
        return saved;
    }

    //read
    public List<Cart> retrieveAllProducts() {
        return repository.findAll();
    }


    public Optional<Cart> retrieveCartById(Long id) {
        return repository.findById(id);
    }


    //delete
    public void delete(Cart object) {
        repository.delete(object);
    }


}
