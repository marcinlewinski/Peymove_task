package com.example.server.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

/**
 * Entity class representing a user in the system.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID id;

    /**
     * The email associated with the user.
     */
    @Column(name = "email", unique = true)
    private String email;

    /**
     * The username associated with the user.
     */
    private String username;

    /**
     * The password associated with the user.
     */
    private String password;


    /**
     * The set of roles (authorities) associated with the user.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> authorities;

    /**
     * Returns the name associated with the user.
     *
     * @return The name associated with the user.
     */
    public String getName() {
        return this.username;
    }

    /**
     * Returns the authorities (roles) associated with the user.
     *
     * @return The authorities (roles) associated with the user.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    /**
     * Returns the email associated with the user.
     *
     * @return The email associated with the user.
     */
    @Override
    public String getUsername() {
        return this.email;
    }

    /**
     * Returns true if the user's account is not expired.
     *
     * @return true if the user's account is not expired.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Returns true if the user's account is not locked.
     *
     * @return true if the user's account is not locked.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Returns true if the user's credentials are not expired.
     *
     * @return true if the user's credentials are not expired.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Returns true if the user is enabled.
     *
     * @return true if the user is enabled.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
