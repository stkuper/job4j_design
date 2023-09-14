package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            String str;
            boolean isWork = false;
            while ((str = reader.readLine()) != null) {
                String[] lines = str.split(" ");
                if (isWork != Integer.parseInt(lines[0]) >= 400) {
                    isWork = !isWork;
                    writer.append(lines[1]).append(";").append(isWork ? "" : System.lineSeparator());
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
