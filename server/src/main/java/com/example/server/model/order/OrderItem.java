package com.example.server.model.order;
/**
 * Entity class representing an item within an order in the system.
 */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Entity class representing an item within an order in the system.
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "order_items")
public class OrderItem {
    /**
     * The unique identifier for the order item.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID itemId;
    /**
     * The order to which the item belongs.
     */
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    /**
     * The product associated with the order item.
     */
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    /**
     * The quantity of the product in the order item.
     */
    @Column(name = "quantity", nullable = false)
    private int quantity;


}
