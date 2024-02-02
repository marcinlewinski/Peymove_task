package com.example.server.controller;

import com.example.server.exception.ProductNotFoundException;
import com.example.server.exception.UserNotFoundException;
import com.example.server.model.order.OrderItemRequest;
import com.example.server.model.order.dto.OrderResponseDto;
import com.example.server.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class handling order-related endpoints.
 */
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    /**
     * Constructor to inject the OrderService dependency.
     *
     * @param orderService OrderService instance.
     */
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Endpoint for creating a new order.
     *
     * @param userEmail         User's email associated with the order.
     * @param orderItemRequests List of order items to be included in the order.
     * @return ResponseEntity with a success message on successful order creation,
     * or an error message with HttpStatus.NOT_FOUND or HttpStatus.INTERNAL_SERVER_ERROR on failure.
     */
    @PostMapping("/create")
    public ResponseEntity<Object> createOrder(@RequestParam String userEmail, @RequestBody List<OrderItemRequest> orderItemRequests) {
        try {
            orderService.createOrder(userEmail, orderItemRequests);
            return ResponseEntity.ok("Order created successfully");
        } catch (UserNotFoundException | ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    /**
     * Endpoint to retrieve all orders.
     *
     * @return ResponseEntity containing a list of OrderResponseDto on successful retrieval.
     */
    @GetMapping("/all")
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        List<OrderResponseDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
}
