package com.example.pizzeria.model;

public class BasePizza extends Product {
    private Long id;

    public BasePizza(String name, double price) {
        super(name, price);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
