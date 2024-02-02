package com.example.server.service.order;

import com.example.server.exception.OrderServiceException;
import com.example.server.exception.ProductNotFoundException;
import com.example.server.mapper.DtoMapper;
import com.example.server.model.User;
import com.example.server.model.order.Order;
import com.example.server.model.order.OrderItem;
import com.example.server.model.order.OrderItemRequest;
import com.example.server.model.order.Product;
import com.example.server.model.order.dto.OrderResponseDto;
import com.example.server.repository.OrderRepository;
import com.example.server.repository.ProductRepository;
import com.example.server.service.UserDetailsServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service implementation for managing orders in the system.
 */
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserDetailsServiceImpl userDetailsService;
    private final ProductRepository productRepository;
    private final DtoMapper dtoMapperImpl;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserDetailsServiceImpl userDetailsService, ProductRepository productRepository, DtoMapper dtoMapperImpl) {
        this.orderRepository = orderRepository;
        this.userDetailsService = userDetailsService;
        this.productRepository = productRepository;
        this.dtoMapperImpl = dtoMapperImpl;
    }

    /**
     * Creates a new order for the specified user based on the provided list of order items.
     *
     * @param userEmail         Email of the user placing the order.
     * @param orderItemRequests List of order items containing product IDs and quantities.
     * @throws ProductNotFoundException if a product in the order is not found.
     * @throws OrderServiceException    if an error occurs while creating the order.
     */
    @Override
    @Transactional
    public void createOrder(String userEmail, List<OrderItemRequest> orderItemRequests) {
        User user = userDetailsService.getUser(userEmail);

        Order order = getOrder(user);

        addOrderItemListToOrder(orderItemRequests, order);

        orderRepository.save(order);
    }

    /**
     * Retrieves a list of all orders in the system.
     *
     * @return List of OrderResponseDto representing all orders.
     * @throws OrderServiceException if an error occurs while retrieving orders.
     */
    @Override
    public List<OrderResponseDto> getAllOrders() {
        try {
            List<Order> orders = orderRepository.findAll();
            return dtoMapperImpl.mapToOrderResponseDtoList(orders);
        } catch (Exception e) {
            e.printStackTrace();
            throw new OrderServiceException("An error occurred while downloading orders. Details: ");
        }
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


}
