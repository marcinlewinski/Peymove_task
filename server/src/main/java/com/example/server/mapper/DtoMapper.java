package com.example.server.mapper;

import com.example.server.model.User;
import com.example.server.model.order.Order;
import com.example.server.model.order.dto.OrderResponseDto;
import com.example.server.model.order.dto.UserResponseDto;

import java.util.List;

public interface DtoMapper {
    List<OrderResponseDto> mapToOrderResponseDtoList(List<Order> orders);
    List<UserResponseDto> mapUserResponseDtoList(List<User> users);
}
