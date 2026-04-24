package com.example.pizzeria.service.impl;

import com.example.pizzeria.dto.pizza.PizzaRequest;
import com.example.pizzeria.exception.ResourceNotFoundException;
import com.example.pizzeria.mapper.PizzaMapper;
import com.example.pizzeria.model.Ingredient;
import com.example.pizzeria.model.Pizza;
import com.example.pizzeria.repository.PizzaRepository;
import com.example.pizzeria.service.PizzaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaServiceImpl implements PizzaService {
    private final PizzaRepository pizzaRepository;
    private final PizzaMapper pizzaMapper;

    public PizzaServiceImpl(PizzaRepository pizzaRepository, PizzaMapper pizzaMapper) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaMapper = pizzaMapper;
    }


    @Override
    public Pizza create(String name, Long basePizzaId, List<Long> ingredientIds) {
        Pizza pizza = pizzaMapper.toModel(new PizzaRequest(name, basePizzaId, ingredientIds));

        return pizzaRepository.save(pizza);
    }

    @Override
    public Pizza update(Long id, String name, Long basePizzaId, List<Long> ingredientIds) {
        pizzaRepository.findById(id).orElseThrow( () ->
                new ResourceNotFoundException("Pizza not found: " + id)
        );

        Pizza newPizza = pizzaMapper.toModel(new PizzaRequest(name, basePizzaId, ingredientIds));

        return pizzaRepository.save(newPizza);
    }

    @Override
    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

    @Override
    public Pizza findById(Long id) {
        return pizzaRepository.findById(id).orElseThrow( () ->
                new ResourceNotFoundException("Pizza not found: " + id)
        );

    }

    @Override
    public void deleteById(Long id) {
        pizzaRepository.deleteById(id);
    }
}
