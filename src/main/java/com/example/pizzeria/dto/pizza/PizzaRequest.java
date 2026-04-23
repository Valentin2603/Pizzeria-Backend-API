package com.example.pizzeria.dto.pizza;

import java.util.List;

public record PizzaRequest (
        String name,
        Long basePizzaId,
        List<Long> ingredientIds
){

}
