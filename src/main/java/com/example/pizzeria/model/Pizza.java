package com.example.pizzeria.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Pizza {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private Long basePizzaId;
    @Getter
    @Setter
    private List<Long> ingredientIds;
    @Getter
    @Setter
    private Long id;

    public Pizza(String name, Long basePizzaId, List<Long> ingredientIds) {
        this.name = name;
        this.basePizzaId = basePizzaId;
    }

    public void setBaseId(Long userBaseId) {
        this.basePizzaId = userBaseId;
    }

    public Long getBaseId() {
        return basePizzaId;
    }

    public void addIngredientId(Long ingredientId) {
        ingredientIds.add(ingredientId);
    }

}