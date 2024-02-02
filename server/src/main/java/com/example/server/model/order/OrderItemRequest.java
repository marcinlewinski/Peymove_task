package com.example.server.model.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Request object representing an item within an order.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {
    /**
     * The unique identifier for the product associated with the order item.
     */
    private UUID productId;
    /**
     * The quantity of the product in the order item.
     */
    private int quantity;

}
