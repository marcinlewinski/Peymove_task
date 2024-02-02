package com.example.server.mapper;

import com.example.server.model.User;
import com.example.server.model.order.Order;
import com.example.server.model.order.OrderItem;
import com.example.server.model.order.dto.OrderItemResponseDto;
import com.example.server.model.order.dto.OrderResponseDto;
import com.example.server.model.order.dto.UserResponseDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoMapperImpl implements DtoMapper {
    @Override
    public List<OrderResponseDto> mapToOrderResponseDtoList(List<Order> orders) {
        return orders.stream()
                .map(this::mapToOrderResponseDto)
                .collect(Collectors.toList());
    }

    private OrderResponseDto mapToOrderResponseDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderId(order.getOrderId());
        orderResponseDto.setUserEmail(order.getUser().getEmail());

        List<OrderItemResponseDto> orderItemResponseDtos = order.getOrderItems()
                .stream()
                .map(this::mapToOrderItemResponseDto)
                .collect(Collectors.toList());

        orderResponseDto.setOrderItems(orderItemResponseDtos);
        return orderResponseDto;
    }


    private OrderItemResponseDto mapToOrderItemResponseDto(OrderItem orderItem) {
        OrderItemResponseDto orderItemResponseDto = new OrderItemResponseDto();
        orderItemResponseDto.setProduct(orderItem.getProduct());
        orderItemResponseDto.setQuantity(orderItem.getQuantity());

        return orderItemResponseDto;
    }

    @Override
    public List<UserResponseDto> mapUserResponseDtoList(List<User> users) {
        if (users == null) {
            return Collections.emptyList();
        }
        return users
                .stream()
                .map(this::mapToUserResponseDto)
                .collect(Collectors.toList());

    }

    private UserResponseDto mapToUserResponseDto(User user) {
        return new UserResponseDto(user.getEmail(), user.getName(), getRoles(user.getAuthorities()));
    }

    private String getRoles(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));


    }

}
