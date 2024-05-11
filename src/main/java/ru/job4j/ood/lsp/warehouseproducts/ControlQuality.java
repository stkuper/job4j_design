package ru.job4j.ood.lsp.warehouseproducts;

import ru.job4j.ood.lsp.warehouseproducts.food.Food;
import ru.job4j.ood.lsp.warehouseproducts.store.Store;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private List<Store> storeList = new ArrayList<>();
    private DateControl dateControl = new DateControl();

    public boolean addStore(Store store) {
        return this.storeList.add(store);
    }

    public boolean checkQuality(Food food, LocalDate date) {
        boolean result = false;
        dateControl.calculateRemainDayLife(food, date);
        for (Store store : storeList) {
            if (store.add(food)) {
                result = true;
            }
        }
        return result;
    }

    public void resort(LocalDate date) {
        List<Food> foodTemporaryList = new ArrayList<>();
        for (Store store : storeList) {
            foodTemporaryList.addAll(store.findAll());
            store.findAll().clear();
        }
        foodTemporaryList.forEach(food -> checkQuality(food, date));
    }
}