package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(new FileOutputStream(target))) {
            String str;
            boolean isWork = true;
            while ((str = reader.readLine()) != null) {
                if (isWork && (str.startsWith("400") || str.startsWith("500"))) {
                    isWork = false;
                    String[] lines = str.split(" ");
                    writer.print(lines[1] + ";");
                } else if (!isWork && (str.startsWith("200") || str.startsWith("300"))) {
                    isWork = true;
                    String[] lines = str.split(" ");
                    writer.print(lines[1] + ";" + System.lineSeparator());
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server2.log", "data/targer.csv");
    }
}
