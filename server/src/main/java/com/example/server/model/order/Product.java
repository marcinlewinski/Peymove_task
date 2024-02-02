package com.example.server.model.order;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

/**
 * Entity class representing a product in the system.
 */
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    /**
     * The unique identifier for the product.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    /**
     * The name of the product.
     */
    @Column(name = "product_name", nullable = false)
    private String productName;
    /**
     * The price of the product.
     */
    @Column(name = "price", nullable = false)
    private double price;
    /**
     * The image URL associated with the product.
     */
    @Column(name = "image")
    private String image;
}
