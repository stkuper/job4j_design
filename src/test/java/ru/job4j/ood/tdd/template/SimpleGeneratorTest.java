package ru.job4j.ood.tdd.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
class SimpleGeneratorTest {
    @Test
    void whenCorrectKeys() {
        Generator generator = new SimpleGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = Map.of("name", "Stanislav Kupriyanov", "subject", "you");
        assertThat(generator.produce(template, map))
                .isEqualTo("I am a Stanislav Kupriyanov, Who are you? ");
    }

    @Test
    void whenIncorrectKeys() {
        Generator generator = new SimpleGenerator();
        String template = "I am a ${name}, Who are ${subject}?, Where are my $(house)? ";
        Map<String, String> map = Map.of("name", "Stanislav Kupriyanov", "subject", "you");
        assertThatThrownBy(() -> generator.produce(template, map))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Incorrect key");
    }

    @Test
    void whenAdditionalKeys() {
        Generator generator = new SimpleGenerator();
        String template = "I am a ${name}";
        Map<String, String> map = Map.of("name", "Stanislav Kupriyanov", "subject", "you");
        assertThatThrownBy(() -> generator.produce(template, map))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Additional key");
    }
}