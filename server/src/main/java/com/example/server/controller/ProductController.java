package com.example.server.controller;

import com.example.server.model.order.Product;
import com.example.server.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller class handling product-related endpoints.
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    /**
     * Constructor to inject the ProductService dependency.
     *
     * @param productService ProductService instance.
     */
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Endpoint to retrieve all products.
     *
     * @return ResponseEntity containing a list of Product on successful retrieval.
     */
    @GetMapping("/get")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
}
