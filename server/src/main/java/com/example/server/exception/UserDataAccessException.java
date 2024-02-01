package com.example.server.exception;

import org.springframework.dao.DataAccessException;

public class UserDataAccessException extends DataAccessException {
    public UserDataAccessException(String msg) {
        super(msg);
    }
}
