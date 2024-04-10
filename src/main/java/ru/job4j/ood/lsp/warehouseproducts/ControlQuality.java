package ru.job4j.ood.lsp.warehouseproducts;

import ru.job4j.ood.lsp.warehouseproducts.food.Food;
import ru.job4j.ood.lsp.warehouseproducts.store.Store;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private List<Store> storeList = new ArrayList<>();
    private DateControl dateControl = new DateControl();

    public boolean addStore(Store store) {
        return this.storeList.add(store);
    }

    public boolean checkQuality(Food food) {
        boolean result = false;
        for (Store store : storeList) {
            if (store.add(food)) {
                result = true;
            }
        }
        return result;
    }
}