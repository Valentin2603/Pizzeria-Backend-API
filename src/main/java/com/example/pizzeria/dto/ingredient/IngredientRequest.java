package com.example.pizzeria.dto.ingredient;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record IngredientRequest(
        @NotBlank
        String name,
        @PositiveOrZero
        double price
) {
}
