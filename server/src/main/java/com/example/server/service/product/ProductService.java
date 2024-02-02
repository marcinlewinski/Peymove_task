package com.example.server.service.product;

import com.example.server.model.order.Product;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing products in the system.
 */
@Service
public interface ProductService {
    /**
     * Retrieves a list of all products in the system.
     *
     * @return List of Product objects representing all products.
     */
    List<Product> getAllProducts();
}
