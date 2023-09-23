package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        validateKey(key);
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String str : args) {
            String[] lines = str.split("=", 2);
            String key = lines[0].replaceFirst("-", "");
            values.putIfAbsent(key, lines[1]);
        }
    }

    public static ArgsName of(String[] args) {
        validateLine(args);
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void validateLine(String[] lines) {
        if (lines.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        for (String line : lines) {
            if (line.matches("-\\w+=.+")) {
                continue;
            }
            if (!line.contains("=")) {
                throw new IllegalArgumentException(String.format(
                        "Error: This argument '%s' does not contain an equal sign", line
                ));
            }
            if (!line.startsWith("-")) {
                throw new IllegalArgumentException(String.format(
                        "Error: This argument '%s' does not start with a '-' character", line
                ));
            }
            if (line.matches("-=.+")) {
                throw new IllegalArgumentException(String.format(
                        "Error: This argument '%s' does not contain a key", line
                ));
            }
            if (line.matches("-\\w+=")) {
                throw new IllegalArgumentException(String.format(
                        "Error: This argument '%s' does not contain a value", line
                ));
            }
        }
    }

    public void validateKey(String key) {
        Set<String> keys = values.keySet();
        if (!keys.contains(key)) {
            throw new IllegalArgumentException(String.format(
                    "This key: '%s' is missing", key
            ));
        }
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
