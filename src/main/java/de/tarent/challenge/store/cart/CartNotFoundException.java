package de.tarent.challenge.store.cart;

public class CartNotFoundException extends RuntimeException {

    CartNotFoundException(String message) {
        super(message);
    }
}