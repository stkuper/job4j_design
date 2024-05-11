package ru.job4j.ood.lsp.warehouseproducts.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.warehouseproducts.DateControl;
import ru.job4j.ood.lsp.warehouseproducts.food.Egg;
import ru.job4j.ood.lsp.warehouseproducts.food.Food;
import ru.job4j.ood.lsp.warehouseproducts.food.Meat;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class WarehouseTest {
    private Store store = new Warehouse();
    private LocalDate today = LocalDate.now();
    private DateControl dateControl = new DateControl();

    @Test
    void whenLessThan25ThenTrue() {
        LocalDate create = today.minusDays(2);
        LocalDate expire = today.plusDays(10);
        Food meat = new Meat("Meat", expire, create, 500.50);
        dateControl.calculateRemainDayLife(meat, today);
        assertThat(store.check(meat)).isTrue();
    }

    @Test
    void whenMoreThan25ThenFalse() {
        LocalDate create = today.minusDays(1);
        LocalDate expire = today.plusDays(2);
        Food egg = new Egg("Egg", expire, create, 100.50);
        dateControl.calculateRemainDayLife(egg, today);
        assertThat(store.check(egg)).isFalse();
    }
}