package de.tarent.challenge.store.cart;

public class CartInvalidException extends RuntimeException {

    CartInvalidException(String message) {
        super(message);
    }

}
