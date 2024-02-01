package com.example.server.service.order;

import com.example.server.model.order.Order;
import com.example.server.model.order.OrderItemRequest;
import com.example.server.model.order.dto.OrderResponseDto;

import java.util.List;

public interface OrderService {
    void createOrder(String userEmail, List<OrderItemRequest> orderItemRequests);

    List<OrderResponseDto>getAllOrders();
}
