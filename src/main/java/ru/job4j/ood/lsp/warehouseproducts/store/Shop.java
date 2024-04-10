package ru.job4j.ood.lsp.warehouseproducts.store;

import ru.job4j.ood.lsp.warehouseproducts.DateControl;
import ru.job4j.ood.lsp.warehouseproducts.food.Food;

public class Shop extends AbstractStore {
    DateControl control = new DateControl();

    @Override
    public boolean check(Food food) {
        boolean result = false;
        int foodLifeLivedPercent =
                control.calculateRemainDayLife(food.getCreateDate(), food.getExpiryDate());
        if (foodLifeLivedPercent >= SHOP_START_DISCOUNT_PERCENT
            && foodLifeLivedPercent < TRASH_START_PERCENT) {
            food.setDiscount(DISCOUNT);
        }
        if (foodLifeLivedPercent > WAREHOUSE_END_PERCENT
            && foodLifeLivedPercent < TRASH_START_PERCENT) {
            result = true;
        }
        return result;
    }
}