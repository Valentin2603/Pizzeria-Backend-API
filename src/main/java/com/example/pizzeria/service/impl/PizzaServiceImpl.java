package com.example.pizzeria.service.impl;

import com.example.pizzeria.model.BasePizza;
import com.example.pizzeria.model.Ingredient;
import com.example.pizzeria.model.Pizza;
import com.example.pizzeria.repository.BasePizzaRepository;
import com.example.pizzeria.repository.IngredientRepository;
import com.example.pizzeria.repository.PizzaRepository;
import com.example.pizzeria.service.PizzaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaServiceImpl implements PizzaService {
    private final PizzaRepository pizzaRepository;
    private final IngredientRepository ingredientRepository;
    private final BasePizzaRepository basePizzaRepository;

    public PizzaServiceImpl(PizzaRepository pizzaRepository, IngredientRepository ingredientRepository, BasePizzaRepository basePizzaRepository) {
        this.pizzaRepository = pizzaRepository;
        this.ingredientRepository = ingredientRepository;
        this.basePizzaRepository = basePizzaRepository;
    }

    @Override
    public double calculatePrice(Pizza pizza) {
        double basePizzaPrice = basePizzaRepository.findById(pizza.getBasePizzaId()).getPrice();
        double ingredientsPrice = ingredientRepository.findByIds(pizza.getIngredientIds()).stream()
                .mapToDouble(Ingredient:: getPrice)
                .sum();
        return basePizzaPrice + ingredientsPrice;

    }

    @Override
    public Pizza create(String name, Long basePizzaId, List<Long> ingredientIds) {
        BasePizza basePizza = basePizzaRepository.findById(basePizzaId);
        if (basePizza == null) {
            throw new IllegalArgumentException("Base pizza not found: " + basePizzaId);
        }
        Pizza pizza = new Pizza(name, basePizzaId, ingredientIds);
        pizzaRepository.save(pizza);
        return pizza;
    }

    @Override
    public Pizza update(Long id, String name, Long basePizzaId, List<Long> ingredientIds) {
        Pizza pizza = pizzaRepository.findById(id);
        if (pizza == null) {
            throw new IllegalArgumentException("Pizza not found: " + basePizzaId);
        }

        pizza.setBaseId(basePizzaId);
        pizza.setName(name);
        pizza.setIngredientIds(ingredientIds);
        pizzaRepository.update(id, pizza);
        return pizza;
    }

    @Override
    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

    @Override
    public Pizza findById(Long id) {
        var pizza = pizzaRepository.findById(id);
        if (pizza == null) {
            throw new IllegalArgumentException("Pizza not found: " + id);
        }
        return pizza;
    }

    @Override
    public void deleteById(Long id) {
        var pizza = pizzaRepository.findById(id);
        if (pizza == null) {
            throw new IllegalArgumentException("Pizza not found: " + id);
        }
        pizzaRepository.deleteById(id);
    }
}
