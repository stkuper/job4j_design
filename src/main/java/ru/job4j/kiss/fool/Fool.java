package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {
    private static int num = 1;
    private static final String FIZZBUZZ = "FizzBuzz";
    private static final String FIZZ = "Fizz";
    private static final String BUZZ = "Buzz";

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        Scanner scanner = new Scanner(System.in);
        while (num < 100) {
            System.out.println(getCheck(num++));
            String answer = scanner.nextLine();
            if (!answer.equals(getCheck(num))) {
                System.out.println("Ошибка. Начинай снова.");
                num = 0;
            }
            num++;
        }
    }

    static String getCheck(int calculate) {
        String str;
        if (calculate % 5 == 0 && calculate % 3 == 0) {
            str = FIZZBUZZ;
        } else if (calculate % 3 == 0) {
            str = FIZZ;
        } else if (calculate % 5 == 0) {
            str = BUZZ;
        } else {
            str = "";
        }
        return str.isBlank() ? String.valueOf(calculate) : str;
    }
}