package com.example.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for handling exceptions in the REST controllers.
 */
@ControllerAdvice
public class RestExceptionHandler {
    /**
     * Handles AuthenticationFailedException and returns a ResponseEntity with UNAUTHORIZED status.
     *
     * @param e AuthenticationFailedException instance.
     * @return ResponseEntity with the exception message and UNAUTHORIZED status.
     */
    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<String> handleAuthenticationFailedException(AuthenticationFailedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles generic Exception and returns a ResponseEntity with INTERNAL_SERVER_ERROR status.
     *
     * @param e Exception instance.
     * @return ResponseEntity with the exception message and INTERNAL_SERVER_ERROR status.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles RegistrationException and returns a ResponseEntity with BAD_REQUEST status.
     *
     * @param e RegistrationException instance.
     * @return ResponseEntity with the exception message and BAD_REQUEST status.
     */
    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<String> handleRegistrationException(RegistrationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


    /**
     * Handles ProductNotFoundException and returns a ResponseEntity with NOT_FOUND status.
     *
     * @param e ProductNotFoundException instance.
     * @return ResponseEntity with the exception message and NOT_FOUND status.
     */
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles UserNotFoundException and returns a ResponseEntity with NOT_FOUND status.
     *
     * @param e UserNotFoundException instance.
     * @return ResponseEntity with the exception message and NOT_FOUND status.
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles OrderServiceException and returns a ResponseEntity with INTERNAL_SERVER_ERROR status.
     *
     * @param e OrderServiceException instance.
     * @return ResponseEntity with the exception message and INTERNAL_SERVER_ERROR status.
     */
    @ExceptionHandler(OrderServiceException.class)
    public ResponseEntity<String> handleOrderServiceException(OrderServiceException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles UserDataAccessException and returns a ResponseEntity with NOT_FOUND status.
     *
     * @param e UserDataAccessException instance.
     * @return ResponseEntity with the exception message and NOT_FOUND status.
     */
    @ExceptionHandler(UserDataAccessException.class)
    public ResponseEntity<String> handleUserDataAccessException(UserDataAccessException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
