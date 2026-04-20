package com.example.pizzeria.service;

import com.example.pizzeria.model.Border;
import com.example.pizzeria.model.Ingredient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service

public class BorderServiceImp implements ServiceImp<Border> {
    List<Border> borderManager;

    public BorderServiceImp() {
        borderManager = new ArrayList<>();
    }

    public void add(Border userBorder) {
        for (Border border : borderManager){
            if (border.getName().equals(userBorder.getName())){
                System.out.println("Ошибка! Бортик с таким названием уже существует.\n\n");
                return;
            }
        }

        borderManager.add(userBorder);
    }


    @Override
    public List<Border> getAll() {
        return borderManager;
    }

    public List<Border> getBorderManager() {
        return borderManager;
    }

    public int getSize() {
        return borderManager.size();
    }

    public Border getBorderIndex(int index) {
        return borderManager.get(index);
    }

    @Override
    public void remove(Border item) {
        borderManager.remove(item);
    }

    public void changeIngredients(Border border, List<Ingredient> ingredients) {
        border.setIngredients(ingredients);
    }

    public void changeName(Border border, String name){
        border.setName(name);
    }

    public void removeBorder(Border border) {
        borderManager.remove(border);
    }
}
