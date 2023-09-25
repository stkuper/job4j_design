package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String[] filters = argsName.get("filter").split(",");
        List<String> csvList = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(path))) {
            scanner.useDelimiter(delimiter);
            String[] split = scanner.nextLine().split(delimiter);
            String[] finish = new String[split.length];
            for (int i = 0; i < filters.length; i++) {
                for (String str : split) {
                    if (filters[i].equals(str)) {
                        finish[i] = str;
                        break;
                    }
                }
            }
            csvList.add(argsName.get("filter").replace(",", delimiter));
            while (scanner.hasNextLine()) {
                String[] column = scanner.nextLine().split(delimiter);
                StringJoiner stringJoiner = new StringJoiner(delimiter);
                for (int i = 0; i < column.length; i++) {
                    if (finish[i] != null) {
                        int index = Arrays.asList(split).indexOf(finish[i]);
                        stringJoiner.add(column[index]);
                    }
                }
                csvList.add(stringJoiner.toString());
            }
            print(csvList, argsName);
        }
    }

    private static void print(List<String> list, ArgsName argsName) {
        String out = argsName.get("out");
        if ("stdout".equals(out)) {
            list.forEach(System.out::println);
        } else {
            try (PrintWriter printWriter = new PrintWriter(out)) {
                list.forEach(printWriter::println);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void validateParams(ArgsName argsName) {
        String file = argsName.get("path");
        if (!Files.exists(Path.of(file))) {
            throw new IllegalArgumentException(String.format(
                    "File '%s' not exist", argsName.get("path")
            ));
        }
        if (!argsName.get("delimiter").matches("[,;]")) {
            throw new IllegalArgumentException(String.format(
                    "Not supported '%s' delimiter", argsName.get("delimiter")
            ));
        }
        String outFile = argsName.get("out");
        if (!outFile.matches("stdout")
                || !outFile.matches(file)) {
            throw new IllegalArgumentException(String.format(
                    "Invalid value '%s'. Check the value", outFile
            ));
        }
        if (argsName.get("filter").endsWith("=")) {
            throw new IllegalArgumentException("No filter specified");
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("Must be 4 arguments. Check the arguments");
        }
        ArgsName argsName = ArgsName.of(args);
        validateParams(argsName);
        handle(argsName);
    }
}
