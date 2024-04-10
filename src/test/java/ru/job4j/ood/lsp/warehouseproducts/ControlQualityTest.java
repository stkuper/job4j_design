package ru.job4j.ood.lsp.warehouseproducts;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.warehouseproducts.food.Egg;
import ru.job4j.ood.lsp.warehouseproducts.food.Food;
import ru.job4j.ood.lsp.warehouseproducts.food.Meat;
import ru.job4j.ood.lsp.warehouseproducts.food.Milk;
import ru.job4j.ood.lsp.warehouseproducts.store.Shop;
import ru.job4j.ood.lsp.warehouseproducts.store.Trash;
import ru.job4j.ood.lsp.warehouseproducts.store.Warehouse;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ControlQualityTest {
    private LocalDate today = LocalDate.now();

    @Test
    void whenAddFreshFoodToWarehouseThenTrue() {
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.addStore(new Warehouse());
        LocalDate create = today.minusDays(1);
        LocalDate expire = today.plusDays(10);
        Food meat = new Meat("Meat", expire, create, 800);
        assertThat(controlQuality.checkQuality(meat)).isTrue();
    }

    @Test
    void whenAddFreshFoodNotToWarehouseThenFalse() {
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.addStore(new Shop());
        controlQuality.addStore(new Trash());
        LocalDate create = today.minusDays(1);
        LocalDate expire = today.plusDays(10);
        Food meat = new Meat("Meat", expire, create, 800);
        Food egg = new Egg("Egg", expire, create, 100);
        assertThat(controlQuality.checkQuality(meat)).isFalse();
        assertThat(controlQuality.checkQuality(egg)).isFalse();
    }

    @Test
    void whenAddMediumFoodToShopThenTrue() {
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.addStore(new Shop());
        LocalDate create = today.minusDays(10);
        LocalDate expire = today.plusDays(10);
        Food meat = new Meat("Meat", expire, create, 800);
        assertThat(controlQuality.checkQuality(meat)).isTrue();
    }

    @Test
    void whenAddMediumFoodNotToShopThenFalse() {
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.addStore(new Warehouse());
        controlQuality.addStore(new Trash());
        LocalDate create = today.minusDays(7);
        LocalDate expire = today.plusDays(8);
        Food meat = new Meat("Meat", expire, create, 800);
        Food milk = new Egg("Milk", expire, create, 100);
        assertThat(controlQuality.checkQuality(meat)).isFalse();
        assertThat(controlQuality.checkQuality(milk)).isFalse();
    }

    @Test
    void whenAddExpireFoodToTrashThenTrue() {
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.addStore(new Trash());
        LocalDate create = today.minusDays(7);
        LocalDate expire = today.minusDays(1);
        Food milk = new Milk("Milk", expire, create, 80);
        assertThat(controlQuality.checkQuality(milk)).isTrue();
    }

    @Test
    void whenAddExpireFoodNotToTrashThenFalse() {
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.addStore(new Warehouse());
        controlQuality.addStore(new Shop());
        LocalDate create = today.minusDays(10);
        LocalDate expire = today.minusDays(2);
        Food meat = new Meat("Meat", expire, create, 800);
        Food egg = new Egg("Egg", expire, create, 100);
        assertThat(controlQuality.checkQuality(meat)).isFalse();
        assertThat(controlQuality.checkQuality(egg)).isFalse();
    }
}