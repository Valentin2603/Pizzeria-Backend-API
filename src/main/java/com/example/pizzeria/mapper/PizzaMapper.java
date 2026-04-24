package com.example.pizzeria.mapper;

import com.example.pizzeria.dto.pizza.PizzaRequest;
import com.example.pizzeria.dto.pizza.PizzaResponse;
import com.example.pizzeria.exception.ResourceNotFoundException;
import com.example.pizzeria.model.BasePizza;
import com.example.pizzeria.model.Ingredient;
import com.example.pizzeria.model.Pizza;
import com.example.pizzeria.repository.BasePizzaRepository;
import com.example.pizzeria.repository.IngredientRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PizzaMapper {
    private final BasePizzaRepository basePizzaRepository;
    private final IngredientRepository ingredientRepository;
    private final BasePizzaMapper basePizzaMapper;
    private final IngredientMapper ingredientMapper;

    public PizzaMapper(BasePizzaRepository basePizzaRepository, IngredientRepository ingredientRepository, BasePizzaMapper basePizzaMapper, IngredientMapper ingredientMapper) {
        this.basePizzaRepository = basePizzaRepository;
        this.ingredientRepository = ingredientRepository;
        this.basePizzaMapper = basePizzaMapper;
        this.ingredientMapper = ingredientMapper;
    }

    public Pizza toModel(PizzaRequest request) {
        BasePizza basePizza = basePizzaRepository.findById(request.basePizzaId()).orElseThrow( () ->
                new ResourceNotFoundException("Base not found: " + request.basePizzaId())
        );

        List<Ingredient> ingredients = request.ingredientIds().stream()
                .map(id -> ingredientRepository.findById(id).orElseThrow(() ->
                        new ResourceNotFoundException("Ingredient not found: " + id)
                ))
                .toList();

        return new Pizza(request.name(), basePizza, ingredients);
    }

    public double calculatePrice(Pizza pizza) {
        double basePizzaPrice = pizza.getBasePizza().getPrice();
        double ingredientsPrice = pizza.getIngredients().stream()
                .mapToDouble(Ingredient::getPrice)
                .sum();

        return basePizzaPrice + ingredientsPrice;

    }

    public PizzaResponse toResponse(Pizza pizza) {
        double price = calculatePrice(pizza);

        return new PizzaResponse(
                pizza.getId(),
                pizza.getName(),
                price,
                basePizzaMapper.toResponse(pizza.getBasePizza()),
                pizza.getIngredients().stream()
                        .map(ingredientMapper::toResponse)
                        .toList()
        );
    }
}
