package com.example.pizzeria.model;


public class Ingredient extends Product {
    private Long id;

    public Ingredient(String nameIngredient, int priceIngredient){
        super(nameIngredient, priceIngredient);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
