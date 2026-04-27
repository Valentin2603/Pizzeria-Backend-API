package com.example.pizzeria.controller;

import com.example.pizzeria.dto.pizza.PizzaRequest;
import com.example.pizzeria.dto.pizza.PizzaResponse;
import com.example.pizzeria.mapper.PizzaMapper;
import com.example.pizzeria.model.Pizza;
import com.example.pizzeria.service.PizzaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;
    private final PizzaMapper pizzaMapper;

    public PizzaController(PizzaService pizzaService, PizzaMapper pizzaMapper) {
        this.pizzaService = pizzaService;
        this.pizzaMapper = pizzaMapper;
    }

    @GetMapping
    public ResponseEntity<List<PizzaResponse>> getAll() {
        List<PizzaResponse> responses = pizzaService.findAll().stream()
                .map(pizzaMapper:: toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PizzaResponse> getById(@PathVariable Long id) {
        Pizza pizza = pizzaService.findById(id);
        PizzaResponse response = pizzaMapper.toResponse(pizza);

        return ResponseEntity.ok(response);
    }


    @PostMapping()
    public ResponseEntity<PizzaResponse> create(@RequestBody @Valid PizzaRequest request) {
        Pizza pizza = pizzaService.create(request.name(), request.basePizzaId(), request.ingredientIds());
        PizzaResponse response = pizzaMapper.toResponse(pizza);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PizzaResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid PizzaRequest request
    ) {
        Pizza pizza = pizzaService.update(id, request.name(), request.basePizzaId(), request.ingredientIds());
        PizzaResponse response = pizzaMapper.toResponse(pizza);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        pizzaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

