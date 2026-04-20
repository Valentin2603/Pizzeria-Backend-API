package com.example.pizzeria.service.impl;

import com.example.pizzeria.model.Ingredient;
import com.example.pizzeria.repository.IngredientRepository;
import com.example.pizzeria.service.IngredientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    @Override
    public Ingredient findById(Long id) {
        return ingredientRepository.findById(id);
    }

    @Override
    public Ingredient create(String name, int price) {
        return ingredientRepository.save(new Ingredient(name, price));
    }

    @Override
    public Ingredient update(Long id, String name, int price) {
        Ingredient ingredient = ingredientRepository.findById(id);
        if (ingredient == null) {
            return null;
        }

        ingredient.setName(name);
        ingredient.setPrice(price);
        return ingredientRepository.update(id, ingredient);
    }

    @Override
    public void deleteById(Long id) {
        ingredientRepository.deleteById(id);
    }
}
