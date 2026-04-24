package com.example.pizzeria.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Table(name = "pizzas")
@Entity
public class Pizza {
    @Id
    @Column(name = "pizza_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "pizza_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "base_pizza_id")
    private BasePizza basePizza;

    @ManyToMany
    @JoinTable(
            name = "pizza_ingredients",
            joinColumns = @JoinColumn(name = "base_pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients;



    public Pizza(String name, BasePizza basePizza, List<Ingredient> ingredients) {
        this.name = name;
        this.basePizza = basePizza;
        this.ingredients = ingredients;
    }


    public Pizza() {

    }
}