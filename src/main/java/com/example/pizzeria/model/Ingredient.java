package com.example.pizzeria.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.*;
@Setter
@Getter
@Entity
public class Ingredient{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private double price;

    public Ingredient(String name, double price){
        this.name = name;
        this.price = price;
    }

    public Ingredient() {

    }


}
