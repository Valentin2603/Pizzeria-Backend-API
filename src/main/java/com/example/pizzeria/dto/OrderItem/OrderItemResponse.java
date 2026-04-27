package com.example.pizzeria.dto.OrderItem;

import com.example.pizzeria.dto.border.BorderResponse;
import com.example.pizzeria.dto.pizza.PizzaResponse;
import com.example.pizzeria.model.enums.PizzaSize;

public record OrderItemResponse(
        Long id,
        PizzaResponse pizza,
        BorderResponse border,
        PizzaSize size,
        int quantity,
        double price
) {
}
