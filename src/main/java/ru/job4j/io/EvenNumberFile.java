package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("data/even.txt")) {
            StringBuilder sb = new StringBuilder();
            int read;
            while ((read = fis.read()) != -1) {
                sb.append((char) read);
            }
            String[] lines = sb.toString().split(System.lineSeparator());
            for (String line : lines) {
                if (Integer.parseInt(line) % 2 == 0) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
