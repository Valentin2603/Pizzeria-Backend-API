package com.example.pizzeria.service;

import com.example.pizzeria.model.BasePizza;

public interface BasePizzaService extends CrudService<BasePizza, Long> {
    BasePizza create(String name, double price);

    BasePizza update(Long id, String name, double price);
}
