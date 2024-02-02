package com.example.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing a login response.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginResponseDTO {
    /**
     * The JSON Web Token (JWT) associated with the login response.
     */
    private String jwt;
}
