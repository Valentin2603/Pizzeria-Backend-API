package com.example.pizzeria.mapper;

import com.example.pizzeria.dto.pizza.PizzaResponse;
import com.example.pizzeria.model.BasePizza;
import com.example.pizzeria.model.Ingredient;
import com.example.pizzeria.model.Pizza;
import com.example.pizzeria.service.BasePizzaService;
import com.example.pizzeria.service.IngredientService;
import com.example.pizzeria.service.PizzaService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PizzaMapper {
    private final IngredientService ingredientService;
    private final BasePizzaService basePizzaService;
    private final PizzaService pizzaService;

    public PizzaMapper(IngredientService ingredientService, BasePizzaService basePizzaService, PizzaService pizzaService) {
        this.ingredientService = ingredientService;
        this.basePizzaService = basePizzaService;
        this.pizzaService = pizzaService;
    }

    public PizzaResponse toResponse(Pizza pizza) {
        if (pizza == null) {
            return null;
        }

        List<Long> ingredientIds = pizza.getIngredientIds() != null
                ? pizza.getIngredientIds()
                : List.of();

        List<Ingredient> ingredients = ingredientIds.stream()
                .map(ingredientService::findById).toList();

        BasePizza basePizza = basePizzaService.findById(pizza.getBasePizzaId());

        double price = pizzaService.calculatePrice(pizza);

        return new PizzaResponse(pizza.getName(), price, basePizza, ingredients);
    }
}
