package com.example.pizzeria.service;

import com.example.pizzeria.model.Pizza;

import java.util.List;

public interface PizzaService extends CrudService<Pizza, Long>{
    double calculatePrice(Pizza pizza);

    Pizza create(String name, Long basePizzaId, List<Long> ingredientIds);

    Pizza update(Long id, String name, Long basePizzaId, List<Long> ingredientIds);



}
