package com.example.pizzeria.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;
@Getter
@Setter
@Entity
public class Border {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private double price;

    @ManyToMany
    @JoinTable(
            name = "border_ingredients",
            joinColumns = @JoinColumn(name = "border_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients;

    @ManyToMany
    @JoinTable(
            name = "forbidden_pizzas",
            joinColumns = @JoinColumn(name = "border_id"),
            inverseJoinColumns = @JoinColumn(name = "pizza_id")
    )
    private List<Pizza> forbiddenPizzas;

    public Border(String name, double price, List<Ingredient> ingredients, List<Pizza> forbiddenPizzas){
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
        this.forbiddenPizzas = forbiddenPizzas;
    }

    public Border() {

    }


    public boolean isCompatibleWith(Pizza pizza) {
        if (forbiddenPizzas == null) {
            return true;
        }

        return forbiddenPizzas.stream()
                .noneMatch(forbidden -> forbidden.getId().equals(pizza.getId()));

    }


}