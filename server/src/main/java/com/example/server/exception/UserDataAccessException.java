package com.example.server.exception;

import org.springframework.dao.DataAccessException;

/**
 * Exception thrown when there is an issue accessing user data in the database.
 */
public class UserDataAccessException extends DataAccessException {
    /**
     * Constructor to create an instance of UserDataAccessException with a specified message.
     *
     * @param msg Error message indicating the reason for the user data access exception.
     */
    public UserDataAccessException(String msg) {
        super(msg);
    }
}
