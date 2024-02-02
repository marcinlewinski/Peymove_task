package com.example.server.exception;

/**
 * Exception thrown when authentication fails.
 */
public class AuthenticationFailedException extends RuntimeException {
    /**
     * Constructor to create an instance of AuthenticationFailedException with a specified message.
     *
     * @param message Error message indicating the reason for authentication failure.
     */
    public AuthenticationFailedException(String message) {
        super(message);
    }
}

