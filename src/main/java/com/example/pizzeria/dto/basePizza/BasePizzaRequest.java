package com.example.pizzeria.dto.basePizza;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record BasePizzaRequest (
        @NotBlank
        String name,
        @Positive
        double price
) {

}
