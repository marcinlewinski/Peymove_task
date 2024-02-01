package com.example.server.controller;

import com.example.server.exception.ProductNotFoundException;
import com.example.server.exception.UserNotFoundException;
import com.example.server.model.order.Order;
import com.example.server.model.order.OrderItemRequest;
import com.example.server.model.order.dto.OrderResponseDto;
import com.example.server.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

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
    @GetMapping("/all")
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        List<OrderResponseDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
}
