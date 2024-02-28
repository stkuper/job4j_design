package ru.job4j.gc.cache.menu;

import ru.job4j.gc.cache.DirFileCache;

import java.util.Scanner;

public class Emulator {
    public static final String DIRECTORY_NAME = "Введите имя директории";
    public static final String FILE_NAME = "Введите имя файла";
    public static final String EXIT = "Конец работы";
    public static final String MENU = """
            Введите 1, чтобы загрузить данные в кэш.
            Введите 2, чтобы получить данные из кэша.
            Введите любое другое число для выхода.
            """;
    public static final int CASHE_LOAD = 1;
    public static final int CASHE_GAIN = 2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            int userChoice = Integer.parseInt(scanner.nextLine());
            if (CASHE_LOAD == userChoice) {
                System.out.println(DIRECTORY_NAME);
                String dir = scanner.nextLine();
                System.out.println(FILE_NAME);
                String file = scanner.nextLine();
                new DirFileCache(dir).get(file);
            } else if (CASHE_GAIN == userChoice) {
                System.out.println(DIRECTORY_NAME);
                String dir = scanner.nextLine();
                System.out.println(FILE_NAME);
                String file = scanner.nextLine();
                System.out.println(new DirFileCache(dir).get(file));
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }
}
