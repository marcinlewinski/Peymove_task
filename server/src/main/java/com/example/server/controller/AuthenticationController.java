package com.example.server.controller;

import com.example.server.exception.RegistrationException;
import com.example.server.model.AuthenticationRequestDTO;
import com.example.server.model.LoginResponseDTO;
import com.example.server.model.RegistrationDTO;
import com.example.server.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    /**
     * Constructor to inject the AuthenticationService dependency.
     *
     * @param authenticationService AuthenticationService instance.
     */
    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * Endpoint for user authentication.
     *
     * @param request AuthenticationRequestDTO containing user credentials.
     * @return ResponseEntity containing LoginResponseDTO on successful authentication.
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> authenticate(@RequestBody AuthenticationRequestDTO request) {
        LoginResponseDTO response = authenticationService.loginUser(request.getEmail(), request.getPassword());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Endpoint for user registration.
     *
     * @param request RegistrationDTO containing user registration details.
     * @return ResponseEntity with a success message on successful registration,
     * or an error message with HttpStatus.BAD_REQUEST on registration failure.
     */
    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody RegistrationDTO request) {
        try {
            authenticationService.registerUser(request.getEmail(), request.getPassword(), request.getName());
            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        } catch (RegistrationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
