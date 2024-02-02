package com.example.server.repository;

import com.example.server.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for managing orders in the system.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

}
