package de.tarent.challenge.store.cart;

import de.tarent.challenge.store.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository repository;

    @Autowired
    public CartService(CartRepository repository) {
        Assert.notNull(repository, "CartRepository should not be null");
        this.repository = repository;
    }

    //create and  update
    public Cart save(Cart object) {
        Cart saved = repository.save(object);
        return saved;
    }

    //read
    public List<Cart> retrieveAll() {
        return repository.findAll();
    }


    public Optional<Cart> retrieveById(Long id) {

        return repository.findById(id);
        /*
        Optional<Cart> optionalCart = repository.findById(id);

        if (optionalCart.isPresent()) {
            Cart result = optionalCart.get();
            if (result.getProducts()!=null){
                for (CartItem item:result.getProducts()) {

                }
            }

            return Optional.of(result);
        }
        return optionalCart;
        */
    }


    //delete
    public void delete(Cart object) {

        Optional<Cart> cart = repository.findById(object.getId());

        cart.ifPresent(repository::delete);
    }


}
