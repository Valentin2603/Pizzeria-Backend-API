package com.example.pizzeria.controller;

import com.example.pizzeria.dto.ingredient.IngredientRequest;
import com.example.pizzeria.dto.ingredient.IngredientResponse;
import com.example.pizzeria.mapper.IngredientMapper;
import com.example.pizzeria.model.Ingredient;
import com.example.pizzeria.service.IngredientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;
    private final IngredientMapper ingredientMapper;

    public IngredientController(IngredientService ingredientService, IngredientMapper ingredientMapper) {
        this.ingredientService = ingredientService;
        this.ingredientMapper = ingredientMapper;
    }

    @GetMapping
    public List<IngredientResponse> findAll() {
        return ingredientService.findAll().stream()
                .map(ingredientMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public IngredientResponse findById(@PathVariable Long id) {
        Ingredient ingredient = ingredientService.findById(id);
        return ingredientMapper.toResponse(ingredient);
    }

    @PostMapping
    public ResponseEntity<IngredientResponse> create(@RequestBody @Valid IngredientRequest request) {
        Ingredient ingredient = ingredientService.create(request.name(), request.price());
        IngredientResponse response = ingredientMapper.toResponse(ingredient);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredientResponse> update(@PathVariable Long id, @RequestBody @Valid IngredientRequest request) {
        Ingredient ingredient = ingredientService.update(id, request.name(), request.price());
        return ResponseEntity.ok(ingredientMapper.toResponse(ingredient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ingredientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
