package com.example.pizzeria.dto.border;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public record BorderRequest (
        @NotBlank
        String name,
        @PositiveOrZero
        double price,
        @NotEmpty
        List<Long> ingredientIds,
        @NotNull
        List<Long> forbiddenPizzaIds
) {
}
