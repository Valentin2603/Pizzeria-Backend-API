package com.example.pizzeria.mapper;

import com.example.pizzeria.dto.ingredient.IngredientResponse;
import com.example.pizzeria.model.Ingredient;
import org.springframework.stereotype.Component;

@Component
public class IngredientMapper {

    public IngredientResponse toResponse(Ingredient ingredient) {
        if (ingredient == null) {
            return null;
        }
        return new IngredientResponse(ingredient.getId(), ingredient.getName(), ingredient.getPrice());
    }
}
