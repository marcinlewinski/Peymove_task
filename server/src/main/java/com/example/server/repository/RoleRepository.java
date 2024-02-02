package com.example.server.repository;

import com.example.server.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing roles in the system.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    /**
     * Finds a role by its authority.
     *
     * @param authority The authority of the role to find.
     * @return An Optional containing the role if found, or empty otherwise.
     */
    Optional<Role> findByAuthority(String authority);
}