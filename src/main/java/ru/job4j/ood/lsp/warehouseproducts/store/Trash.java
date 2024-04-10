package ru.job4j.ood.lsp.warehouseproducts.store;

import ru.job4j.ood.lsp.warehouseproducts.DateControl;
import ru.job4j.ood.lsp.warehouseproducts.food.Food;

public class Trash extends AbstractStore {
    DateControl control = new DateControl();

    @Override
    public boolean check(Food food) {
        return control.calculateRemainDayLife(
                food.getCreateDate(), food.getExpiryDate()) >= TRASH_START_PERCENT;
    }
}