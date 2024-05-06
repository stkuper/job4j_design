package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;

class PrinterTest {
    public static final ActionDelegate STUB_ACTION = System.out::println;
    public static Menu menu = new SimpleMenu();
    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    @BeforeAll
    public static void init() {
        menu.add(Menu.ROOT, "Сходить на работу", STUB_ACTION);
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add(Menu.ROOT, "Позаниматься JAVA", STUB_ACTION);
        menu.add("Сходить на работу", "Поработать", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        menu.add("Позаниматься JAVA", "Сделать тест правильно", STUB_ACTION);
    }

    @Test
    void whenPrinted() {
        System.setOut(new PrintStream(byteArrayOutputStream));
        String separator = System.lineSeparator();
        String expected = "Сходить на работу 1." + separator
                          + "----Поработать 1.1." + separator
                          + "Сходить в магазин 2." + separator
                          + "----Купить продукты 2.1." + separator
                          + "--------Купить хлеб 2.1.1." + separator
                          + "--------Купить молоко 2.1.2." + separator
                          + "Покормить собаку 3." + separator
                          + "Позаниматься JAVA 4." + separator
                          + "----Сделать тест правильно 4.1.";
        MenuPrinter menuPrinter = new Printer();
        menuPrinter.print(menu);
        assertThat(byteArrayOutputStream.toString().trim()).isEqualTo(expected);
        System.setOut(System.out);
    }
}