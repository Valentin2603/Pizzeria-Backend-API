package com.example.pizzeria.service;

import java.util.List;

public interface CrudService<T, ID> {

    List<T> findAll();

    T findById(ID id);

    void deleteById(ID id);
}
