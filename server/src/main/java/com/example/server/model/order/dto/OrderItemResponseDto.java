package com.example.server.model.order.dto;

import com.example.server.model.order.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderItemResponseDto {
    private Product product;
    private int quantity;
}
