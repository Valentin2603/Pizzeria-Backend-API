package com.example.pizzeria.repository;

import com.example.pizzeria.model.Pizza;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PizzaRepository {
    private Map<Long, Pizza> storage = new LinkedHashMap<>();
    private Long nextId = 1L;

    public PizzaRepository() {

    }

    public List<Pizza> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Pizza findById(Long id) {
        return storage.get(id);
    }

    public Pizza save(Pizza pizzaForSave) {
        pizzaForSave.setId(nextId);
        storage.put(nextId, pizzaForSave);
        nextId++;
        return pizzaForSave;
    }

    public Pizza update(Long id, Pizza pizzaForUpdate) {
        pizzaForUpdate.setId(id);
        storage.put(id, pizzaForUpdate);
        return pizzaForUpdate;
    }

    public void deleteById(Long id) {
        storage.remove(id);
    }
}
