package com.example.server.exception;

/**
 * Exception thrown when an error occurs in the order service.
 */
public class OrderServiceException extends RuntimeException {
    /**
     * Constructor to create an instance of OrderServiceException with a specified message.
     *
     * @param message Error message indicating the reason for the order service exception.
     */
    public OrderServiceException(String message) {
        super(message);
    }
}
