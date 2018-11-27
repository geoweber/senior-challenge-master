package de.tarent.challenge.store.cart;

/**
 * Carts that have been checked out cannot be changed anymore.
 */
public class CartNotUpdatableException  extends RuntimeException{
    public CartNotUpdatableException(String message) {
        super(message);
    }
}
