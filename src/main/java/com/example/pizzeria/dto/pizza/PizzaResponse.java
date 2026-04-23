package com.example.pizzeria.dto.pizza;

import com.example.pizzeria.model.BasePizza;
import com.example.pizzeria.model.Ingredient;

import java.util.List;

public record PizzaResponse (
        String name,
        double price,
        BasePizza basePizza,
        List<Ingredient> ingredients
){
}
