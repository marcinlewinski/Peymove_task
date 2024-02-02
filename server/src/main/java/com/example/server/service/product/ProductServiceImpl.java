package com.example.server.service.product;

import com.example.server.model.order.Product;
import com.example.server.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for managing products in the system.
 */
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Retrieves a list of all products in the system.
     *
     * @return List of Product objects representing all products.
     */
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


}
