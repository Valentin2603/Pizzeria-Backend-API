package com.example.pizzeria.service;

import com.example.pizzeria.model.BasePizza;
import com.example.pizzeria.model.Ingredient;
import com.example.pizzeria.model.Pizza;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class PizzaServiceImp implements ServiceImp<Pizza> {
    private final List<Pizza> pizzaService;


    public PizzaServiceImp(List<Pizza> pizzaService){
        this.pizzaService = pizzaService;
    }

    public int getSize(){
        return pizzaService.size();
    }

    public Pizza getById(int id){
        return pizzaService.get(id);
    }

    public void add(Pizza userPizza){

        for (Pizza p : pizzaService){
            if (p.getName().equals(userPizza.getName())){
                System.out.println("Ошибка! Пицца с таким названием уже существует.\n\n");
                return;
            }
        }

        pizzaService.add(userPizza);
    }

    public void changePizzaName(Pizza pizza, String name){
        pizza.setName(name);
    }

    public void changePizzaBase(Pizza pizza, BasePizza base) {
        pizza.setBase(base);
    }

    public void changePizzaIngredients(Pizza pizza, List<Ingredient> ingredientList){
        pizza.setIngredients(ingredientList);
    }

    public void remove(Pizza pizzaForDelete) {
        pizzaService.remove(pizzaForDelete);
    }

    public List<Pizza> getAll() {
        return pizzaService;
    }
}