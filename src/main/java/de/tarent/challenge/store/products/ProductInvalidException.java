package de.tarent.challenge.store.products;

class ProductInvalidException extends RuntimeException {
    ProductInvalidException(String message) {
        super(message);
    }
}
