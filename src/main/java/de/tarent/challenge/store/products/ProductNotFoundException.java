package de.tarent.challenge.store.products;

class ProductNotFoundException extends RuntimeException {
    ProductNotFoundException(String message) {
        super(message);
    }
}
