package com.example.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Data Transfer Object (DTO) representing an authentication request.
 */
@Getter
@AllArgsConstructor
public class AuthenticationRequestDTO {
    /**
     * The email associated with the authentication request.
     */
    private String email;
    /**
     * The password associated with the authentication request.
     */
    private String password;
}
