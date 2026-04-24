package com.example.pizzeria.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
public class BasePizza {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private double price;

    public BasePizza() {
    }

    public BasePizza(String name, double price) {
        this.name = name;
        this.price = price;
    }

}
