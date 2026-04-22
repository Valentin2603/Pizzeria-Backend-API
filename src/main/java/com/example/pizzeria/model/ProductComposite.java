//package com.example.pizzeria.model;
//
//import java.util.List;
//
//public abstract class ProductComposite {
//    protected String name;
//    protected double price;
//    private final List<Long> ingredientIds;
//
//    public ProductComposite(String name, List<Long> ingredientIds){
//        this.name = name;
//        this.ingredientIds = ingredientIds;
//    }
//
//    public void setName(String pizzaName) {
//        this.name = pizzaName;
//    }
//
//
//    public void setIngredientIds(List<Long> userIngredients) {
//        ingredientIds.clear();
//        ingredientIds.addAll(userIngredients);
//    }
//
//    public void setPrice() {
//        price = 0;
//        for (Long id : ingredientIds) {
//            Ingredient ingredient =
//            price += ing.getPrice();
//        }
//
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public List<Ingredient> getIngredientIds() {
//        return ingredientIds;
//    }
//
//}