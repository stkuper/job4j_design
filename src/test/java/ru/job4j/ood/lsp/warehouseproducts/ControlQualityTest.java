package ru.job4j.ood.lsp.warehouseproducts;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.warehouseproducts.food.Egg;
import ru.job4j.ood.lsp.warehouseproducts.food.Food;
import ru.job4j.ood.lsp.warehouseproducts.food.Meat;
import ru.job4j.ood.lsp.warehouseproducts.food.Milk;
import ru.job4j.ood.lsp.warehouseproducts.store.Shop;
import ru.job4j.ood.lsp.warehouseproducts.store.Store;
import ru.job4j.ood.lsp.warehouseproducts.store.Trash;
import ru.job4j.ood.lsp.warehouseproducts.store.Warehouse;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class ControlQualityTest {
    private LocalDate today = LocalDate.now();

    @Test
    void whenAddFreshFoodToWarehouseThenTrue() {
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.addStore(new Warehouse());
        LocalDate create = today.minusDays(1);
        LocalDate expire = today.plusDays(10);
        Food meat = new Meat("Meat", expire, create, 800);
        assertThat(controlQuality.checkQuality(meat, today)).isTrue();
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
        assertThat(controlQuality.checkQuality(meat, today)).isFalse();
        assertThat(controlQuality.checkQuality(egg, today)).isFalse();
    }

    @Test
    void whenAddMediumFoodToShopThenTrue() {
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.addStore(new Shop());
        LocalDate create = today.minusDays(10);
        LocalDate expire = today.plusDays(10);
        Food meat = new Meat("Meat", expire, create, 800);
        assertThat(controlQuality.checkQuality(meat, today)).isTrue();
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
        assertThat(controlQuality.checkQuality(meat, today)).isFalse();
        assertThat(controlQuality.checkQuality(milk, today)).isFalse();
    }

    @Test
    void whenAddExpireFoodToTrashThenTrue() {
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.addStore(new Trash());
        LocalDate create = today.minusDays(7);
        LocalDate expire = today.minusDays(1);
        Food milk = new Milk("Milk", expire, create, 80);
        assertThat(controlQuality.checkQuality(milk, today)).isTrue();
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
        assertThat(controlQuality.checkQuality(meat, today)).isFalse();
        assertThat(controlQuality.checkQuality(egg, today)).isFalse();
    }

    @Test
    void whenResortMoveToShopFromWarehouse() {
        ControlQuality controlQuality = new ControlQuality();
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        controlQuality.addStore(warehouse);
        controlQuality.addStore(shop);
        LocalDate create = today.minusDays(3);
        LocalDate expire = today.plusDays(9);
        Food meat = new Meat("Meat", expire, create, 700);
        assertThat(controlQuality.checkQuality(meat, today)).isTrue();
        LocalDate checkDay = today.plusDays(4);
        controlQuality.resort(checkDay);
        String expected = "Meat";
        assertThat(warehouse.findAll()).isEmpty();
        assertThat(shop.findAll()).contains(meat);
        assertThat(expected).isEqualTo(meat.getName());
    }

    @Test
    void whenResortMoveToTrashFromShop() {
        ControlQuality controlQuality = new ControlQuality();
        Store shop = new Shop();
        Store trash = new Trash();
        controlQuality.addStore(shop);
        controlQuality.addStore(trash);
        LocalDate create = today.minusDays(5);
        LocalDate expire = today.plusDays(5);
        Food meat = new Meat("Meat", expire, create, 900);
        assertThat(controlQuality.checkQuality(meat, today)).isTrue();
        LocalDate checkDay = today.plusDays(5);
        controlQuality.resort(checkDay);
        double price = 900d;
        assertThat(shop.findAll()).isEmpty();
        assertThat(trash.findAll()).contains(meat);
        assertThat(price).isEqualTo(meat.getPrice(), withPrecision(0.01d));
    }
}