package com.example.server.service.order;

import com.example.server.model.order.OrderItemRequest;

import java.util.List;

public interface OrderService {
    void createOrder(String userEmail, List<OrderItemRequest> orderItemRequests);
}
