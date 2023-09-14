package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        String str;
        try (BufferedReader reader = new BufferedReader(
                new FileReader(this.path))) {
            while ((str = reader.readLine()) != null) {
                if (!str.isBlank() && !str.startsWith("#")) {
                    if (!str.contains("=")) {
                        throw new IllegalArgumentException("Line :%s without the symbol '='".formatted(str));
                    }
                    String[] lines = str.split("=", 2);
                    if (lines.length == 2 && (lines[0].isBlank() || lines[1].isBlank())) {
                        throw new IllegalArgumentException("Key or value at line :%s is empty".formatted(str));
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