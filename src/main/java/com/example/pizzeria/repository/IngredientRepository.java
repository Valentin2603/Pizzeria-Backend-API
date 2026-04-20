package com.example.pizzeria.repository;

import com.example.pizzeria.model.Ingredient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class IngredientRepository {
    private final Map<Long, Ingredient> storage = new LinkedHashMap<>();
    private long nextId = 1L;

    public IngredientRepository() {
        save(new Ingredient("Сыр", 50));
        save(new Ingredient("Томаты", 30));
        save(new Ingredient("Кетчуп", 40));
    }

    public List<Ingredient> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Ingredient findById(Long id) {
        return storage.get(id);
    }

    public Ingredient save(Ingredient ingredient) {
        ingredient.setId(nextId);
        storage.put(nextId, ingredient);
        nextId++;
        return ingredient;
    }

    public Ingredient update(Long id, Ingredient ingredient) {
        ingredient.setId(id);
        storage.put(id, ingredient);
        return ingredient;
    }

    public void deleteById(Long id) {
        storage.remove(id);
    }
}
