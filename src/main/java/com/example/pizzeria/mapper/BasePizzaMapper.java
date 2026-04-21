package com.example.pizzeria.mapper;

import com.example.pizzeria.dto.basePizza.BasePizzaResponse;
import com.example.pizzeria.model.BasePizza;
import org.springframework.stereotype.Component;

@Component
public class BasePizzaMapper {
    public BasePizzaResponse toResponse(BasePizza basePizza) {
        if (basePizza == null) {
            return null;
        }

        return new BasePizzaResponse(basePizza.getId(), basePizza.getName(), basePizza.getPrice());

    }
}
