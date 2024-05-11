package ru.job4j.ood.lsp.warehouseproducts;

import ru.job4j.ood.lsp.warehouseproducts.food.Food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateControl {
    public Food calculateRemainDayLife(Food food, LocalDate date) {
        long totalDayLife = ChronoUnit.DAYS.between(food.getExpiryDate(), food.getCreateDate());
        long foodLived = ChronoUnit.DAYS.between(date, food.getCreateDate());
        food.setProductLife((foodLived * 1f / totalDayLife) * 100);
        return food;
    }
}