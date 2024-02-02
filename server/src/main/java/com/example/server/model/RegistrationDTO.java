package com.example.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing a user registration request.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {
    /**
     * The email associated with the registration request.
     */
    private String email;
    /**
     * The name associated with the registration request.
     */
    private String name;
    /**
     * The password associated with the registration request.
     */
    private String password;
}
