package com.example.pizzeria.dto.basePizza;

public record BasePizzaResponse(
        Long id,
        String name,
        double price
) {
}
