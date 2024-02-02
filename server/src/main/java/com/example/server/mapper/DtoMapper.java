package com.example.server.mapper;

import com.example.server.model.User;
import com.example.server.model.order.Order;
import com.example.server.model.order.dto.OrderResponseDto;
import com.example.server.model.order.dto.UserResponseDto;

import java.util.List;

/**
 * Interface defining methods for mapping entities to DTOs.
 */
public interface DtoMapper {
    /**
     * Maps a list of Order entities to a list of OrderResponseDto.
     *
     * @param orders List of Order entities.
     * @return List of OrderResponseDto.
     */
    List<OrderResponseDto> mapToOrderResponseDtoList(List<Order> orders);

    /**
     * Maps a list of User entities to a list of UserResponseDto.
     *
     * @param users List of User entities.
     * @return List of UserResponseDto.
     */
    List<UserResponseDto> mapUserResponseDtoList(List<User> users);
}
