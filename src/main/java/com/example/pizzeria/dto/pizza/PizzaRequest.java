package com.example.pizzeria.dto.pizza;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PizzaRequest (
        @NotBlank
        String name,
        @NotNull
        Long basePizzaId,
        @NotEmpty
        List<Long> ingredientIds
){

}
