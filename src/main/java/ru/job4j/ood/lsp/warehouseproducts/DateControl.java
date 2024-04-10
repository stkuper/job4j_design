package ru.job4j.ood.lsp.warehouseproducts;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateControl {
    public int calculateRemainDayLife(LocalDate create, LocalDate expire) {
        LocalDate today = LocalDate.now();
        long totalDayLife = ChronoUnit.DAYS.between(expire, create);
        long foodLived = ChronoUnit.DAYS.between(today, create);
        return Math.round((foodLived * 1f / totalDayLife) * 100);
    }
}