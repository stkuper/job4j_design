package ru.job4j.ood.lsp.warehouseproducts.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.warehouseproducts.DateControl;
import ru.job4j.ood.lsp.warehouseproducts.food.Egg;
import ru.job4j.ood.lsp.warehouseproducts.food.Food;
import ru.job4j.ood.lsp.warehouseproducts.food.Meat;
import ru.job4j.ood.lsp.warehouseproducts.food.Milk;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ShopTest {
    private Store store = new Shop();
    private LocalDate today = LocalDate.now();
    private DateControl dateControl = new DateControl();

    @Test
    void whenLessThan25ThenFalse() {
        LocalDate create = today.minusDays(2);
        LocalDate expire = today.plusDays(7);
        Food egg = new Egg("Egg", expire, create, 120);
        dateControl.calculateRemainDayLife(egg, today);
        assertThat(store.check(egg)).isFalse();
    }

    @Test
    void whenMoreThan100ThenFalse() {
        LocalDate create = today.minusDays(5);
        LocalDate expire = today.minusDays(1);
        Food milk = new Milk("Milk", expire, create, 75.75);
        dateControl.calculateRemainDayLife(milk, today);
        assertThat(store.check(milk)).isFalse();
    }

    @Test
    void whenMoreThan25AndLessThan75ThenTrue() {
        LocalDate create = today.minusDays(5);
        LocalDate expire = today.plusDays(5);
        Food meat = new Meat("Meat", expire, create, 800.80);
        dateControl.calculateRemainDayLife(meat, today);
        assertThat(store.check(meat)).isTrue();
    }

    @Test
    void whenMoreThan75AndLessThan100ThenTrueAndDiscount() {
        LocalDate create = today.minusDays(10);
        LocalDate expire = today.plusDays(2);
        Food egg = new Egg("Egg", expire, create, 100);
        dateControl.calculateRemainDayLife(egg, today);
        assertThat(store.check(egg)).isTrue();
        assertThat(egg.getPrice()).isEqualTo(80);
    }
}