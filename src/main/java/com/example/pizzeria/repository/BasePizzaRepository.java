package com.example.pizzeria.repository;

import com.example.pizzeria.model.BasePizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasePizzaRepository extends JpaRepository<BasePizza, Long> {

}
