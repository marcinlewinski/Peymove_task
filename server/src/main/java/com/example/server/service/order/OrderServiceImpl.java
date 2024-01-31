package com.example.server.service.order;

import com.example.server.exception.ProductNotFoundException;
import com.example.server.exception.UserNotFoundException;
import com.example.server.model.User;
import com.example.server.model.order.Order;
import com.example.server.model.order.OrderItem;
import com.example.server.model.order.OrderItemRequest;
import com.example.server.model.order.Product;
import com.example.server.repository.OrderRepository;
import com.example.server.repository.ProductRepository;
import com.example.server.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public void createOrder(String userEmail, List<OrderItemRequest> orderItemRequests) {
        User user = getUser(userEmail);

        Order order = getOrder(user);

        addOrderItemListToOrder(orderItemRequests, order);

        orderRepository.save(order);
    }

    private void addOrderItemListToOrder(List<OrderItemRequest> orderItemRequests, Order order) {
        List<OrderItem> orderItemList = new ArrayList<>();
        for (OrderItemRequest orderItemRequest : orderItemRequests
        ) {
            Product byId = productRepository.findById(orderItemRequest.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Product cant be found"));
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(byId);
            orderItem.setQuantity(orderItemRequest.getQuantity());
            orderItem.setOrder(order);
            orderItemList.add(orderItem);
        }

        order.setOrderItems(orderItemList);
    }

    private Order getOrder(User user) {
        Order order = new Order();
        order.setUser(user);
        return order;
    }

    private User getUser(String userEmail) {
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("User cant find!"));
    }
}
