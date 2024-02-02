package com.example.server.model.order.dto;

import com.example.server.model.order.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing a response for an order item.
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderItemResponseDto {
    /**
     * The product associated with the order item.
     */
    private Product product;
    /**
     * The quantity of the product in the order item.
     */
    private int quantity;
}
