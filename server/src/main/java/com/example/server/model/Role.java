package com.example.server.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

/**
 * Entity class representing a role in the system.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    /**
     * The unique identifier for the role.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "role_id")
    private UUID id;
    /**
     * The authority associated with the role.
     */
    private String authority;

    /**
     * Constructor for creating a role with a specific authority.
     *
     * @param authority The authority associated with the role.
     */
    public Role(String authority) {
        this.authority = authority;
    }

    /**
     * Get the authority of the role.
     *
     * @return The authority associated with the role.
     */
    @Override
    public String getAuthority() {
        return this.authority;
    }

}
