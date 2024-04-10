package ru.job4j.ood.lsp.warehouseproducts.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.warehouseproducts.food.Food;
import ru.job4j.ood.lsp.warehouseproducts.food.Meat;
import ru.job4j.ood.lsp.warehouseproducts.food.Milk;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class TrashTest {
    private Store store = new Trash();

    @Test
    void whenLessThan100ThenFalse() {
        LocalDate today = LocalDate.now();
        LocalDate create = today.minusDays(5);
        LocalDate expire = today.plusDays(1);
        Food milk = new Milk("Milk", expire, create, 80);
        assertThat(store.check(milk)).isFalse();
    }

    @Test
    void whenMoreThan100ThenTrue() {
        LocalDate today = LocalDate.now();
        LocalDate create = today.minusDays(10);
        LocalDate expire = today.minusDays(1);
        Food meat = new Meat("Meat", expire, create, 700.70);
        assertThat(store.check(meat)).isTrue();
    }
}