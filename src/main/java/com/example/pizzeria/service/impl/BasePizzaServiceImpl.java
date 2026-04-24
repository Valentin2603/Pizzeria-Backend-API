package com.example.pizzeria.service.impl;

import com.example.pizzeria.exception.ResourceNotFoundException;
import com.example.pizzeria.model.BasePizza;
import com.example.pizzeria.repository.BasePizzaRepository;
import com.example.pizzeria.service.BasePizzaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasePizzaServiceImpl implements BasePizzaService {
    private final BasePizzaRepository repository;

    public BasePizzaServiceImpl(BasePizzaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<BasePizza> findAll() {
        return repository.findAll();
    }

    @Override
    public BasePizza findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Base not found: " + id)
        );
    }

    @Override
    public BasePizza create(String name, double price) {
        return repository.save(new BasePizza(name, price));
    }

    @Override
    public BasePizza update(Long id, String name, double price) {
        BasePizza basePizza = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Base not found: " + id)
        );

        basePizza.setName(name);
        basePizza.setPrice(price);

        return repository.save(basePizza);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
