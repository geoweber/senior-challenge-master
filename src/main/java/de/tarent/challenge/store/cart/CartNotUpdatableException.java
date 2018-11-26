package de.tarent.challenge.store.cart;

public class CartNotUpdatableException  extends RuntimeException{
    public CartNotUpdatableException(String message) {
        super(message);
    }
}
