package com.example.server.exception;

/**
 * Exception thrown when a product is not found.
 */
public class ProductNotFoundException extends RuntimeException {
    /**
     * Constructor to create an instance of ProductNotFoundException with a specified message.
     *
     * @param message Error message indicating the reason for the product not being found.
     */
    public ProductNotFoundException(String message) {
        super(message);
    }
}
