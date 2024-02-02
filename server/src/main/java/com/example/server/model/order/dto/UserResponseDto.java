package com.example.server.model.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing a response for a user.
 */
@Getter
@Setter
@AllArgsConstructor
public class UserResponseDto {
    /**
     * The email of the user.
     */
    private String email;
    /**
     * The name of the user.
     */
    private String name;
    /**
     * The roles assigned to the user, represented as a space-separated string.
     */
    private String roles;
}
