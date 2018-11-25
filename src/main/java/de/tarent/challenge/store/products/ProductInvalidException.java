package de.tarent.challenge.store.products;

public class ProductInvalidException extends RuntimeException {
    ProductInvalidException(String message) {
        super(message);
    }
}
