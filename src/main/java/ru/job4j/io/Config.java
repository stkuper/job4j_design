package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        String str;
        try (BufferedReader reader = new BufferedReader(
                new FileReader(this.path))) {
            while ((str = reader.readLine()) != null) {
                if (!str.isBlank() && !str.startsWith("#")) {
                    String[] lines = str.split("=", 2);
                    if (lines[0].isEmpty() || lines[1].isEmpty()) {
                        throw new IllegalArgumentException("Key or value is empty");
                    }
                    values.put(lines[0], lines[1]);
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }
}