package com.example.pizzeria.service;

import com.example.pizzeria.model.Ingredient;
import com.example.pizzeria.model.OrderItem;
import com.example.pizzeria.model.Pizza;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PricingService {

    public double calculatePizzaPrice(Pizza pizza) {
        double basePizzaPrice = pizza.getBasePizza().getPrice();
        double ingredientsPrice = pizza.getIngredients().stream()
                .mapToDouble(Ingredient::getPrice)
                .sum();

        return basePizzaPrice + ingredientsPrice;
    }

    public double calculateOrderItemPrice(OrderItem item) {
        double pizzaPrice = calculatePizzaPrice(item.getPizza());
        double borderPrice = item.getBorder().getPrice();
        double sizeCoefficient = item.getSize().getCoefficient();

        return (pizzaPrice + borderPrice) * sizeCoefficient * item.getQuantity();
    }

    public double calculateOrderPrice(List<OrderItem> items){
        return items.stream()
                .mapToDouble(OrderItem::getPrice)
                .sum();
    }
}
