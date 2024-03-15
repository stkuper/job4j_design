package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FoolTest {
    @Test
    void num() {
        assertThat(Fool.getCheck(4)).isEqualTo("4");
    }

    @Test
    void fizz() {
        assertThat(Fool.getCheck(9)).isEqualTo("Fizz");
    }

    @Test
    void buzz() {
        assertThat(Fool.getCheck(20)).isEqualTo("Buzz");
    }

    @Test
    void fizzBuzz() {
        assertThat(Fool.getCheck(30)).isEqualTo("FizzBuzz");
    }
}