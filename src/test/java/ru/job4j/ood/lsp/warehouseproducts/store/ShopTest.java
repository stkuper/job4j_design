package ru.job4j.ood.lsp.warehouseproducts.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.warehouseproducts.food.Egg;
import ru.job4j.ood.lsp.warehouseproducts.food.Food;
import ru.job4j.ood.lsp.warehouseproducts.food.Meat;
import ru.job4j.ood.lsp.warehouseproducts.food.Milk;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ShopTest {
    private Store store = new Shop();

    @Test
    void whenLessThan25ThenFalse() {
        LocalDate today = LocalDate.now();
        LocalDate create = today.minusDays(2);
        LocalDate expire = today.plusDays(7);
        Food egg = new Egg("Egg", expire, create, 120);
        assertThat(store.check(egg)).isFalse();
    }

    @Test
    void whenMoreThan100ThenFalse() {
        LocalDate today = LocalDate.now();
        LocalDate create = today.minusDays(5);
        LocalDate expire = today.minusDays(1);
        Food milk = new Milk("Milk", expire, create, 75.75);
        assertThat(store.check(milk)).isFalse();
    }

    @Test
    void whenMoreThan25AndLessThan75ThenTrue() {
        LocalDate today = LocalDate.now();
        LocalDate create = today.minusDays(5);
        LocalDate expire = today.plusDays(5);
        Food meat = new Meat("Meat", expire, create, 800.80);
        assertThat(store.check(meat)).isTrue();
    }

    @Test
    void whenMoreThan75AndLessThan100ThenTrueAndDiscount() {
        LocalDate today = LocalDate.now();
        LocalDate create = today.minusDays(10);
        LocalDate expire = today.plusDays(2);
        Food egg = new Egg("Egg", expire, create, 100);
        assertThat(store.check(egg)).isTrue();
        assertThat(egg.getPrice()).isEqualTo(80);
    }
}