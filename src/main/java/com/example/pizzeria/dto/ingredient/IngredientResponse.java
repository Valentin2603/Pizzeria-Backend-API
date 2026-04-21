package com.example.pizzeria.dto.ingredient;

public record IngredientResponse(
        Long id,
        String name,
        double price
) {
}
