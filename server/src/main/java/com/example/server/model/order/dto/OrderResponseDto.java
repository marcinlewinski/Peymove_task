package com.example.server.model.order.dto;

import com.example.server.model.order.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) representing a response for an order.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    /**
     * The unique identifier for the order.
     */
    private UUID orderId;
    /**
     * The email of the user associated with the order.
     */
    private String userEmail;
    /**
     * The list of order items included in the order.
     */
    private List<OrderItemResponseDto> orderItems;
}
