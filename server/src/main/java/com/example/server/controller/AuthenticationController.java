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

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> authenticate(@RequestBody AuthenticationRequestDTO request) {
        LoginResponseDTO response = authenticationService.loginUser(request.getEmail(), request.getPassword());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody RegistrationDTO request) {
        try {
            authenticationService.registerUser(request.getEmail(), request.getPassword(), request.getUsername());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (RegistrationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
