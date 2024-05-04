package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;

class PrinterTest {
    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenOutputWhithoutChild() {
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new Printer();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        printer.print(menu);
        String consoleOutput = byteArrayOutputStream.toString();
        assertThat(consoleOutput).doesNotContain("----");
        System.setOut(System.out);
    }

    @Test
    public void whenOutputWithChild() {
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new Printer();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add("Сходить в магазин", "Покормить собаку", STUB_ACTION);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        printer.print(menu);
        String consoleOutput = byteArrayOutputStream.toString();
        assertThat(consoleOutput).contains("----");
        System.setOut(System.out);
    }

    @Test
    public void whenOutputMoreChild() {
        Menu menu = new SimpleMenu();
        MenuPrinter menuPrinter = new Printer();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add("Сходить в магазин", "Покормить собаку", STUB_ACTION);
        menu.add("Покормить собаку", "Погладить кошку", STUB_ACTION);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        menuPrinter.print(menu);
        String consoleOutput = byteArrayOutputStream.toString();
        assertThat(consoleOutput).contains("----")
                .contains("--------");
        System.setOut(System.out);
    }
}