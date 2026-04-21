package com.example.pizzeria.service.impl;

import com.example.pizzeria.model.BasePizza;
import com.example.pizzeria.repository.BasePizzaRepository;
import com.example.pizzeria.service.BasePizzaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasePizzaServiceImpl implements BasePizzaService {
    private final BasePizzaRepository basePizzaRepository;

    public BasePizzaServiceImpl(BasePizzaRepository basePizzaRepository) {
        this.basePizzaRepository = basePizzaRepository;
    }

    @Override
    public List<BasePizza> findAll() {
        return basePizzaRepository.getAll();
    }

    @Override
    public BasePizza findById(Long id) {
        return basePizzaRepository.getById(id);
    }

    @Override
    public BasePizza create(String name, double price) {
        return basePizzaRepository.save(new BasePizza(name, price));
    }

    @Override
    public BasePizza update(Long id, String name, double price) {
        BasePizza basePizza = basePizzaRepository.getById(id);
        if (basePizza == null) {
            return null;
        }

        basePizza.setName(name);
        basePizza.setPrice(price);

        return basePizzaRepository.update(id, basePizza);
    }

    @Override
    public void deleteById(Long id) {
        basePizzaRepository.deleteById(id);
    }

}
