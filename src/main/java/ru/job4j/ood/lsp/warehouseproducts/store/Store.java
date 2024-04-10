package ru.job4j.ood.lsp.warehouseproducts.store;

import ru.job4j.ood.lsp.warehouseproducts.food.Food;

import java.util.List;

public interface Store {
    boolean add(Food food);

    boolean check(Food food);

    List<Food> findAll();
}