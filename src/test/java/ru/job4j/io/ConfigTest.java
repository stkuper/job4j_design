package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {
    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithComment() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.password")).isEqualTo("password");
    }

    @Test
    void whenPairContainsTwoSignAnyway() {
        String path = "./data/app_two_sign_anyway.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Stas Kupriyanov=Kupriyanov Stas");
    }

    @Test
    void whenFileWithoutPair() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("names")).isNull();
    }

    @Test
    void whenFileWithCommentsAndNullLines() {
        String path = "./data/file_with_comments_and_without_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("PostgreSQL")).isNull();
    }

    @Test
    void whenPairWithoutValue() {
        String path = "./data/app_interrupt1.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Key or value at line"
                        + " :hibernate.connection.username= is empty");
    }

    @Test
    void whenPairWithoutKey() {
        String path = "./data/app_interrupt2.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Key or value at line :=password is empty");
    }

    @Test
    void whenPairWithoutKeyAndValue() {
        String path = "./data/app_interrupt3.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Key or value at line :                = is empty");
    }

    @Test
    void whenFileWithoutSignAnyway() {
        String path = "./data/app_without_sign_anyway.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Line :nameStas KupriyanovKupriyanov Stas"
                        + " without the symbol '='");

    }
}