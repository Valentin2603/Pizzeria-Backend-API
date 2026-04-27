package com.example.pizzeria.dto.OrderItem;

import com.example.pizzeria.model.enums.PizzaSize;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderItemRequest(
        @NotNull
        Long pizzaId,
        @NotNull
        Long borderId,
        @NotNull
        PizzaSize size,
        @Positive
        int quantity
) {
}
