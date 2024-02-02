package com.example.server.exception;

/**
 * Exception thrown when a user is not found.
 */
public class UserNotFoundException extends RuntimeException {
    /**
     * Constructor to create an instance of UserNotFoundException with a specified message.
     *
     * @param message Error message indicating the reason for the user not being found.
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
