package com.example.server.exception;

/**
 * Exception thrown when an error occurs during user registration.
 */
public class RegistrationException extends RuntimeException {

    /**
     * Constructor to create an instance of RegistrationException with a specified message.
     *
     * @param message Error message indicating the reason for the registration exception.
     */
    public RegistrationException(String message) {
        super(message);
    }

}
