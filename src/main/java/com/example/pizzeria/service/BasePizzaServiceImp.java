package com.example.pizzeria.service;

import com.example.pizzeria.model.BasePizza;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class BasePizzaServiceImp implements ServiceImp<BasePizza> {
    private final List<BasePizza> baseManager;
    private final BasePizza mainBase;

    public BasePizzaServiceImp(List<BasePizza> baseService, BasePizza mainBase) {
        this.baseService = baseService;
        this.mainBase = mainBase;
        baseService.add(mainBase);
    }

    public void add(BasePizza base) {
        for (BasePizza p : baseManager){
            if (p.getName().equals(base.getName())){
                System.out.println("Ошибка! Основа с таким названием уже существует.");
                return;
            }
        }
        baseManager.add(base);

    }

    public void change(BasePizza base, String name, int price){
        base.setName(name);
        base.setPrice(price);
    }

    public void remove(BasePizza baseForDelete){
        baseManager.remove(baseForDelete);
    }

    public int getSize(){
        return baseManager.size();
    }

    public BasePizza getById(int id){
        return baseService.get(id);
    }

    public List<BasePizza> getAll(){
        return baseManager;
    }

    @Override
    public BasePizza getById() {
        return null;
    }


}