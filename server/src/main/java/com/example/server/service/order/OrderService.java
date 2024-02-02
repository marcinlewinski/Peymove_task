package com.example.server.service.order;

import com.example.server.model.order.OrderItemRequest;
import com.example.server.model.order.dto.OrderResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing orders in the system.
 */
@Service
public interface OrderService {
    /**
     * Creates an order for a user based on the provided order item requests.
     *
     * @param userEmail         The email of the user for whom the order is created.
     * @param orderItemRequests The list of order item requests specifying products and quantities.
     */
    void createOrder(String userEmail, List<OrderItemRequest> orderItemRequests);

    /**
     * Retrieves all orders in the system.
     *
     * @return A list of order response DTOs representing the orders.
     */
    List<OrderResponseDto> getAllOrders();
}
