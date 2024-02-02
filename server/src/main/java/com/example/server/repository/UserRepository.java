package com.example.server.repository;

import com.example.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing users in the system.
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    /**
     * Finds a user by their email.
     *
     * @param email The email of the user to find.
     * @return An Optional containing the user if found, or empty otherwise.
     */
    Optional<User> findByEmail(String email);
}
