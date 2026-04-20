package com.example.pizzeria.service;

import java.util.List;

public interface ServiceImp<T> {

    void add(T item);

    void remove(T item);

    List<T> getAll();

    T getById();

}