package com.example.pizzeria.service.impl;

import com.example.pizzeria.dto.border.BorderRequest;
import com.example.pizzeria.exception.ResourceNotFoundException;
import com.example.pizzeria.model.Border;
import com.example.pizzeria.model.Ingredient;
import com.example.pizzeria.model.Pizza;
import com.example.pizzeria.repository.BorderRepository;
import com.example.pizzeria.repository.IngredientRepository;
import com.example.pizzeria.repository.PizzaRepository;
import com.example.pizzeria.service.BorderService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BorderServiceImpl implements BorderService {
    private final BorderRepository borderRepository;
    private final IngredientRepository ingredientRepository;
    private final PizzaRepository pizzaRepository;


    public BorderServiceImpl(BorderRepository borderRepository, IngredientRepository ingredientRepository, PizzaRepository pizzaRepository) {
        this.borderRepository = borderRepository;
        this.ingredientRepository = ingredientRepository;
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public Border create(BorderRequest request) {
        List<Ingredient> ingredients = request.ingredientIds().stream()
                .map(ingredientId -> ingredientRepository.findById(ingredientId).orElseThrow(() ->
                        new ResourceNotFoundException("Ingredient not found: " + ingredientId)))
                .toList();

        List<Pizza> forbiddenPizzas = request.forbiddenPizzaIds().stream()
                .map(pizzaId -> pizzaRepository.findById(pizzaId).orElseThrow(() ->
                        new ResourceNotFoundException("Forbidden pizza not found: " + pizzaId)))
                .toList();

        Border border = new Border(
                request.name(),
                request.price(),
                ingredients,
                forbiddenPizzas
        );
        return borderRepository.save(border);
    }

    @Override
    public Border update(Long id, BorderRequest request) {
        Border borderToUpdate = borderRepository.findById(id).orElseThrow( () ->
                new ResourceNotFoundException("Border not found: " + id)
        );

        List<Ingredient> ingredients = request.ingredientIds().stream()
                .map(ingredientId -> ingredientRepository.findById(ingredientId).orElseThrow( () ->
                        new ResourceNotFoundException("Ingredient not found: " + ingredientId)))
                .toList();

        List<Pizza> forbiddenPizzas = request.forbiddenPizzaIds().stream()
                .map(pizzaId -> pizzaRepository.findById(pizzaId).orElseThrow(() ->
                        new ResourceNotFoundException("Forbidden pizza not found: " + pizzaId)))
                .toList();

        borderToUpdate.setName(request.name());
        borderToUpdate.setPrice(request.price());
        borderToUpdate.setForbiddenPizzas(forbiddenPizzas);
        borderToUpdate.setIngredients(ingredients);

        return borderRepository.save(borderToUpdate);
    }

    @Override
    public List<Border> findAll() {
        return borderRepository.findAll();
    }

    @Override
    public Border findById(Long id) {
        return borderRepository.findById(id).orElseThrow( () ->
                new ResourceNotFoundException("Border not found: " + id)
        );
    }

    @Override
    public void deleteById(Long id) {
        borderRepository.findById(id).orElseThrow( () ->
                new ResourceNotFoundException("Border not found: " + id)
        );

        borderRepository.deleteById(id);
    }
}
