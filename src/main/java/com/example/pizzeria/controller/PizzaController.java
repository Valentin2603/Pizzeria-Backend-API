package com.example.pizzeria.controller;

import com.example.pizzeria.model.Pizza;
import com.example.pizzeria.service.PizzaServiceImp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {
    private final PizzaServiceImp pizzaService;

    public PizzaController(PizzaServiceImp pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping("/{id}")
    public Pizza getPizzaById(
            @PathVariable("id") int id
    ) {
        return pizzaService.getById(id);
    }
}
