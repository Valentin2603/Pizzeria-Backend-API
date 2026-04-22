package com.example.pizzeria.repository;

import com.example.pizzeria.model.BasePizza;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BasePizzaRepository {
    private final Map<Long, BasePizza> storage = new LinkedHashMap<>();
    private Long nextId = 1L;

    public BasePizza save(BasePizza basePizza) {
        basePizza.setId(nextId);
        storage.put(nextId, basePizza);
        nextId++;
        return basePizza;
    }

    public BasePizza findById(Long id) {
        return storage.get(id);
    }

    public List<BasePizza> findAll() {
        return new ArrayList<>(storage.values());
    }

    public BasePizza updateById(Long id, BasePizza basePizza) {
        basePizza.setId(id);
        storage.put(id, basePizza);
        return basePizza;
    }

    public void deleteById(Long id) {
        storage.remove(id);
    }
}
