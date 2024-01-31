    package com.example.server.model.order;

    import jakarta.persistence.*;
    import lombok.*;

    import java.util.UUID;

    @Getter
    @Builder
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    @Table(name = "products")
    public class Product {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private UUID id;

        @Column(name = "product_name", nullable = false)
        private String productName;

        @Column(name = "price", nullable = false)
        private double price;

        @Column(name = "image")
        private String image;
    }
