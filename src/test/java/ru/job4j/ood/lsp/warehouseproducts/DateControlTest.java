package ru.job4j.ood.lsp.warehouseproducts;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class DateControlTest {
    private DateControl dateControl = new DateControl();

    @Test
    void whenTodayIsCreateDayThen0Percent() {
        LocalDate create = LocalDate.now();
        LocalDate expire = create.plusDays(5);
        assertThat(dateControl.calculateRemainDayLife(create, expire)).isEqualTo(0);
    }

    @Test
    void whenTodayisExpireDayThen100Percent() {
        LocalDate expire = LocalDate.now();
        LocalDate create = expire.minusDays(7);
        assertThat(dateControl.calculateRemainDayLife(create, expire)).isEqualTo(100);
    }

    @Test
    void whenFoodLifeMore0AndLess25Percent() {
        LocalDate create = LocalDate.now().minusDays(1);
        LocalDate expire = LocalDate.now().plusDays(5);
        assertThat(dateControl.calculateRemainDayLife(create, expire)).isEqualTo(17);
    }

    @Test
    void whenFoodLifeMore25AndLess75Percent() {
        LocalDate create = LocalDate.now().minusDays(5);
        LocalDate expire = LocalDate.now().plusDays(5);
        assertThat(dateControl.calculateRemainDayLife(create, expire)).isEqualTo(50);
    }

    @Test
    void whenFoodLifeMore75AndLess100Percent() {
        LocalDate create = LocalDate.now().minusDays(5);
        LocalDate expire = LocalDate.now().plusDays(2);
        assertThat(dateControl.calculateRemainDayLife(create, expire)).isEqualTo(71);
    }
}