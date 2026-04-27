package com.example.pizzeria.service;

import com.example.pizzeria.dto.border.BorderRequest;
import com.example.pizzeria.model.Border;

public interface BorderService extends CrudService<Border, Long> {
    Border create(BorderRequest request);

    Border update(Long id, BorderRequest request);
}
