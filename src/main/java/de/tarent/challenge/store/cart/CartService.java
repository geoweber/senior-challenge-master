package de.tarent.challenge.store.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

/**
 * cart functionality
 */
@Service
public class CartService {

    private final CartRepository repository;

    @Autowired
    public CartService(CartRepository repository) {
        Assert.notNull(repository, "CartRepository should not be null");
        this.repository = repository;
    }


    public Cart save(Cart object) {

        //CHECK: Carts that have been checked out cannot be changed anymore.
        if (object != null && object.getId() != null) {

            // get existing cart from db
            Optional<Cart> optCart = repository.findById(object.getId());
            if (optCart.isPresent() && optCart.get().isCheckedOut())
                throw new CartNotUpdatableException("Cart (id=" + object.getId() + " have been checked out and cannot be changed anymore.");
        }
        return repository.save(object);
    }


    public List<Cart> retrieveAll() {
        return repository.findAll();
    }


    public Optional<Cart> retrieveById(Long id) {

        return repository.findById(id);
    }


    public void delete(Cart object) {




        for (CartItem item : object.getCartItems()) {
            item.setCart(null);
        }

        object.getCartItems().clear();
        repository.save(object);

        //repository.delete(object);

        Optional<Cart> cart = repository.findById(object.getId());
        cart.ifPresent(repository::delete);
    }
}
