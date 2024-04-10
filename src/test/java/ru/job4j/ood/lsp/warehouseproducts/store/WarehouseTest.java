package ru.job4j.ood.lsp.warehouseproducts.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.warehouseproducts.food.Egg;
import ru.job4j.ood.lsp.warehouseproducts.food.Food;
import ru.job4j.ood.lsp.warehouseproducts.food.Meat;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class WarehouseTest {
    private Store store = new Warehouse();

    @Test
    void whenLessThan25ThenTrue() {
        LocalDate today = LocalDate.now();
        LocalDate create = today.minusDays(2);
        LocalDate expire = today.plusDays(10);
        Food meat = new Meat("Meat", expire, create, 500.50);
        assertThat(store.check(meat)).isTrue();
    }

    @Test
    void whenMoreThan25ThenFalse() {
        LocalDate today = LocalDate.now();
        LocalDate create = today.minusDays(1);
        LocalDate expire = today.plusDays(2);
        Food egg = new Egg("Egg", expire, create, 100.50);
        assertThat(store.check(egg)).isFalse();
    }
}