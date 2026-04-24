package com.example.pizzeria.controller;

import com.example.pizzeria.dto.basePizza.BasePizzaRequest;
import com.example.pizzeria.dto.basePizza.BasePizzaResponse;
import com.example.pizzeria.mapper.BasePizzaMapper;
import com.example.pizzeria.model.BasePizza;
import com.example.pizzeria.service.BasePizzaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bases")
public class BasePizzaController {
    private final BasePizzaService basePizzaService;
    private final BasePizzaMapper basePizzaMapper;

    public BasePizzaController(BasePizzaService basePizzaService, BasePizzaMapper basePizzaMapper) {
        this.basePizzaService = basePizzaService;
        this.basePizzaMapper = basePizzaMapper;
    }

    @GetMapping
    public List<BasePizzaResponse> getAll() {
        return basePizzaService.findAll().stream()
                .map(basePizzaMapper:: toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BasePizzaResponse> getById(@PathVariable Long id) {
        BasePizza basePizza = basePizzaService.findById(id);

        BasePizzaResponse response = basePizzaMapper.toResponse(basePizza);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<BasePizzaResponse> create(@RequestBody BasePizzaRequest request) {
        BasePizza basePizza = basePizzaService.create(request.name(), request.price());
        BasePizzaResponse response =  basePizzaMapper.toResponse(basePizza);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BasePizzaResponse> update(
            @PathVariable Long id,
            @RequestBody BasePizzaRequest request
    ) {
        BasePizza basePizza = basePizzaService.update(id, request.name(), request.price());

        BasePizzaResponse response = basePizzaMapper.toResponse(basePizza);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        basePizzaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
