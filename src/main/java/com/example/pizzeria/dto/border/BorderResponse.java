package com.example.pizzeria.dto.border;

import com.example.pizzeria.dto.ingredient.IngredientResponse;

import java.util.List;

public record BorderResponse(
        Long id,
        String name,
        List<IngredientResponse> ingredients,
        List<Long> forbiddenPizzaIds
) {
}
