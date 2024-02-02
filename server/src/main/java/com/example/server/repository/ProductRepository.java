package com.example.server.repository;

import com.example.server.model.order.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for managing products in the system.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
}
