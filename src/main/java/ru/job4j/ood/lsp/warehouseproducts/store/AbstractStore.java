package ru.job4j.ood.lsp.warehouseproducts.store;

import ru.job4j.ood.lsp.warehouseproducts.food.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    private List<Food> foodList = new ArrayList<>();
    protected final static int DISCOUNT = 20;
    protected final static int WAREHOUSE_END_PERCENT = 25;
    protected final static int SHOP_START_DISCOUNT_PERCENT = 75;
    protected final static int TRASH_START_PERCENT = 100;

    @Override
    public boolean add(Food food) {
        boolean result = false;
        if (check(food)) {
            foodList.add(food);
            result = true;
        }
        return result;
    }

    @Override
    public List<Food> findAll() {
        return foodList;
    }
}