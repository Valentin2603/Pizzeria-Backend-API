package com.example.pizzeria.dto.pizza;

import com.example.pizzeria.dto.basePizza.BasePizzaResponse;
import com.example.pizzeria.dto.ingredient.IngredientResponse;

import java.util.List;

public record PizzaResponse (
        Long id,
        String name,
        double price,
        BasePizzaResponse basePizza,
        List<IngredientResponse> ingredients
){
}
