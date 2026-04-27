package com.example.pizzeria.mapper;

import com.example.pizzeria.dto.border.BorderResponse;
import com.example.pizzeria.dto.ingredient.IngredientResponse;
import com.example.pizzeria.dto.pizza.PizzaResponse;
import com.example.pizzeria.model.Border;
import com.example.pizzeria.model.Pizza;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class BorderMapper {
    private final PizzaMapper pizzaMapper;
    private final IngredientMapper ingredientMapper;

    public BorderMapper(PizzaMapper pizzaMapper, IngredientMapper ingredientMapper) {
        this.pizzaMapper = pizzaMapper;
        this.ingredientMapper = ingredientMapper;
    }


    public BorderResponse toResponse(Border border) {
        List<PizzaResponse> forbiddenPizzas = border.getForbiddenPizzas().stream()
                .map(pizzaMapper::toResponse)
                .toList();

        List<IngredientResponse> ingredients = border.getIngredients().stream()
                .map(ingredientMapper::toResponse)
                .toList();

        return new BorderResponse(
                border.getId(),
                border.getName(),
                border.getIngredients().stream()
                        .map(ingredientMapper::toResponse)
                        .toList(),
                border.getForbiddenPizzas().stream()
                        .map(Pizza::getId)
                        .toList()
                );
    }
}
