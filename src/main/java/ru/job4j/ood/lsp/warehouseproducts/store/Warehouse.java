package ru.job4j.ood.lsp.warehouseproducts.store;

import ru.job4j.ood.lsp.warehouseproducts.DateControl;
import ru.job4j.ood.lsp.warehouseproducts.food.Food;

public class Warehouse extends AbstractStore {
    DateControl control = new DateControl();

    @Override
    public boolean check(Food food) {
        return food.getProductLife() <= WAREHOUSE_END_PERCENT;
    }
}