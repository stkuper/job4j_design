package ru.job4j.io;

import java.io.FileOutputStream;

public class TableMultiply {
    public static void main(String[] args) {
        try (FileOutputStream fos = new FileOutputStream("data/tableMultiply.txt")) {
            fos.write("1 * 2 = 2".getBytes());
            fos.write(System.lineSeparator().getBytes());
            fos.write("1 * 3 = 3".getBytes());
            fos.write(System.lineSeparator().getBytes());
            fos.write("1 * 4 = 4".getBytes());
            fos.write(System.lineSeparator().getBytes());
            fos.write("1 * 5 = 5".getBytes());
            fos.write(System.lineSeparator().getBytes());
            fos.write("1 * 6 = 6".getBytes());
            fos.write(System.lineSeparator().getBytes());
            fos.write("1 * 7 = 7".getBytes());
            fos.write(System.lineSeparator().getBytes());
            fos.write("1 * 8 = 8".getBytes());
            fos.write(System.lineSeparator().getBytes());
            fos.write("1 * 9 = 9".getBytes());
            fos.write(System.lineSeparator().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
