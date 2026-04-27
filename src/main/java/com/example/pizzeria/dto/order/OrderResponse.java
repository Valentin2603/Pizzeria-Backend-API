package com.example.pizzeria.dto.order;

import com.example.pizzeria.dto.OrderItem.OrderItemResponse;
import com.example.pizzeria.model.enums.CustomerOrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        Long id,
        double price,
        LocalDateTime createdAt,
        LocalDateTime scheduledTime,
        CustomerOrderStatus status,
        List<OrderItemResponse> items
) {
}
