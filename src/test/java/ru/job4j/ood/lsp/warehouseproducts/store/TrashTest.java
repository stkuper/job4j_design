package ru.job4j.ood.lsp.warehouseproducts.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.warehouseproducts.DateControl;
import ru.job4j.ood.lsp.warehouseproducts.food.Food;
import ru.job4j.ood.lsp.warehouseproducts.food.Meat;
import ru.job4j.ood.lsp.warehouseproducts.food.Milk;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class TrashTest {
    private Store store = new Trash();
    private LocalDate today = LocalDate.now();
    private DateControl dateControl = new DateControl();

    @Test
    void whenLessThan100ThenFalse() {
        LocalDate create = today.minusDays(5);
        LocalDate expire = today.plusDays(1);
        Food milk = new Milk("Milk", expire, create, 80);
        dateControl.calculateRemainDayLife(milk, today);
        assertThat(store.check(milk)).isFalse();
    }

    @Test
    void whenMoreThan100ThenTrue() {
        LocalDate create = today.minusDays(10);
        LocalDate expire = today.minusDays(1);
        Food meat = new Meat("Meat", expire, create, 700.70);
        dateControl.calculateRemainDayLife(meat, today);
        assertThat(store.check(meat)).isTrue();
    }
}