//package com.example.pizzeria.model;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CombinedBorder extends Border {
//
//    private Border left;
//    private Border right;
//
//    public CombinedBorder(Border left, Border right) {
//        super(left.getName() + " / " + right.getName(), combineIngredients(left, right), new ArrayList<>());
//
//        this.left = left;
//        this.right = right;
//    }
//
//    private static List<Ingredient> combineIngredients(Border left, Border right) {
//        List<Ingredient> result = new ArrayList<>();
//
//        result.addAll(left.getIngredientIds());
//        result.addAll(right.getIngredientIds());
//
//        return result;
//    }
//
//    @Override
//    public double calculatePrice() {
//
//        return (left.calculatePrice() + right.calculatePrice()) / 2;
//
//    }
//
//}