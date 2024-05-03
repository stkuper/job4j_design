package ru.job4j.ood.isp.menu;

import java.util.Optional;
import java.util.Scanner;

public class TodoApp {
    Menu menu;
    MenuPrinter menuPrinter;
    final static String MENU = """
            Выберите пункт меню:
            1. Добавить элемент в корень меню
            2. Добавить элемент к родительскому элементу
            3. Вызвать действие, привязанное к пункту меню
            4. Вывести меню в консоль
            5. Завершить работу
            """;
    final static ActionDelegate DEFAULT_ACTION = () -> System.out.println("Some action");
    Scanner scanner = new Scanner(System.in);

    public TodoApp(Menu menu, MenuPrinter menuPrinter) {
        this.menu = menu;
        this.menuPrinter = menuPrinter;
    }

    public void start() {
        int choice = 0;
        while (choice != 5) {
            System.out.println(MENU);
            choice = 0;
            choice = scanner.nextInt();
            checkChoice(choice);
        }
    }

    private void checkChoice(int choice) {
        if (choice < 1 || choice > 5) {
            System.out.println("Вы ввели отсутствующий пункт меню, выберите от 1 до 5");
        } else if (choice == 1) {
            scanner.nextLine();
            System.out.println("Введите имя элемента, для добавления в корень меню");
            String rootElement = scanner.nextLine();
            menu.add(null, rootElement, DEFAULT_ACTION);
        } else if (choice == 2) {
            scanner.nextLine();
            System.out.println("Введите имя родительского элемента");
            String rootElement = scanner.nextLine();
            Optional<Menu.MenuItemInfo> optionalMenuItemInfo = menu.select(rootElement);
            if (optionalMenuItemInfo.isPresent()) {
                System.out.println("Введите название дочернего элемента");
                String childElement = scanner.nextLine();
                menu.add(rootElement, childElement, DEFAULT_ACTION);
            } else {
                System.out.printf("Корневой элемент меню %s не найден/n", rootElement);
            }
        } else if (choice == 3) {
            scanner.nextLine();
            System.out.println("Введите имя элемента меню");
            String rootElement = scanner.nextLine();
            Optional<Menu.MenuItemInfo> optionalMenuItemInfo = menu.select(rootElement);
            if (optionalMenuItemInfo.isPresent()) {
                System.out.printf("%s ->", optionalMenuItemInfo.get().getName());
                optionalMenuItemInfo.get().getActionDelegate().delegate();
            } else {
                System.out.printf("Элемент меню %s не найден", rootElement);
            }
        } else if (choice == 4) {
            menuPrinter.print(menu);
        } else {
            System.out.println("Завершение работы программы");
        }
    }

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new Printer();
        TodoApp todoApp = new TodoApp(menu, printer);
        todoApp.start();
    }
}