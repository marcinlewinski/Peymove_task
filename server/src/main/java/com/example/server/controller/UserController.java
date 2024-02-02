package com.example.server.controller;

import com.example.server.model.order.dto.UserResponseDto;
import com.example.server.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller class handling user-related endpoints.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private UserDetailsServiceImpl userService;

    /**
     * Constructor to inject the UserDetailsServiceImpl dependency.
     *
     * @param userService UserDetailsServiceImpl instance.
     */
    @Autowired
    public UserController(UserDetailsServiceImpl userService) {
        this.userService = userService;
    }

    /**
     * Endpoint to retrieve all users.
     *
     * @return ResponseEntity containing a list of UserResponseDto on successful retrieval.
     */
    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> allUsers = userService.getAllUsers();
        return ResponseEntity.ok().body(allUsers);

    }

}
