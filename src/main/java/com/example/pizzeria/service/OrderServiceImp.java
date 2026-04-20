package com.example.pizzeria.service;

import com.example.pizzeria.model.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class OrderServiceImp implements ServiceImp<Order> {

    private final List<Order> orders;

    public OrderServiceImp(List<Order> orders) {
        this.orders = orders;
    }

    public Order create() {
        return new Order();
    }

    @Override
    public Order getById() {
        return null;
    }

    public void add(Order order) {
        orders.add(order);
    }

    public List<Order> getAll() {
        return orders;
    }


    public void remove(Order item) {
        orders.remove(item);
    }


}