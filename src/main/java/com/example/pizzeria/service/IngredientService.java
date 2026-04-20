package com.example.pizzeria.service;

import com.example.pizzeria.model.Ingredient;

public interface IngredientService extends CrudService<Ingredient, Long> {

    Ingredient create(String name, int price);

    Ingredient update(Long id, String name, int price);
}
