package ru.job4j.ood.lsp.warehouseproducts;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.warehouseproducts.food.Egg;
import ru.job4j.ood.lsp.warehouseproducts.food.Food;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class DateControlTest {
    private DateControl dateControl = new DateControl();
    private LocalDate today = LocalDate.now();

    @Test
    void whenTodayIsCreateDayThen0Percent() {
        LocalDate create = today;
        LocalDate expire = today.plusDays(5);
        Food egg = new Egg("Egg", expire, create, 120);
        dateControl.calculateRemainDayLife(egg, today);
        assertThat(egg.getProductLife()).isEqualTo(0d, withPrecision(0.01d));
    }

    @Test
    void whenTodayisExpireDayThen100Percent() {
        LocalDate expire = today;
        LocalDate create = today.minusDays(7);
        Food egg = new Egg("Egg", expire, create, 120);
        dateControl.calculateRemainDayLife(egg, today);
        assertThat(egg.getProductLife()).isEqualTo(100d, withPrecision(0.01d));
    }

    @Test
    void whenFoodLifeMore0AndLess25Percent() {
        LocalDate create = today.minusDays(1);
        LocalDate expire = today.plusDays(5);
        Food egg = new Egg("Egg", expire, create, 120);
        dateControl.calculateRemainDayLife(egg, today);
        assertThat(egg.getProductLife()).isEqualTo(16.66d, withPrecision(0.01d));
    }

    @Test
    void whenFoodLifeMore25AndLess75Percent() {
        LocalDate create = today.minusDays(5);
        LocalDate expire = today.plusDays(5);
        Food egg = new Egg("Egg", expire, create, 120);
        dateControl.calculateRemainDayLife(egg, today);
        assertThat(egg.getProductLife()).isEqualTo(50d, withPrecision(0.01d));
    }

    @Test
    void whenFoodLifeMore75AndLess100Percent() {
        LocalDate create = today.minusDays(5);
        LocalDate expire = today.plusDays(2);
        Food egg = new Egg("Egg", expire, create, 120);
        dateControl.calculateRemainDayLife(egg, today);
        assertThat(egg.getProductLife()).isEqualTo(71.42, withPrecision(0.01d));
    }
}