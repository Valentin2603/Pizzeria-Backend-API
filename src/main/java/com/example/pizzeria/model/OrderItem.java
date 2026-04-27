package com.example.pizzeria.model;

import com.example.pizzeria.model.enums.PizzaSize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;

    @Enumerated(value = EnumType.STRING)
    private PizzaSize size;

    @ManyToOne
    @JoinColumn(name = "border_id")
    private Border border;

    private int quantity;

    private double price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private CustomerOrder order;

    public OrderItem(Pizza pizza, PizzaSize size, Border border, int quantity) {
        this.pizza = pizza;
        this.size = size;
        this.border = border;
        this.quantity = quantity;
    }


    public OrderItem() {

    }


}
