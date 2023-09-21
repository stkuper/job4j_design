package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) {
        validateArguments(args);
        Path start = Paths.get(args[0]);
        search(start, path -> path.toFile().getName().endsWith(args[1]))
                .forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) {
        SearchFiles searchFiles = new SearchFiles(condition);
        try {
            Files.walkFileTree(root, searchFiles);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return searchFiles.getPath();
    }

    public static void validateArguments(String[] array) {
        if (array.length == 2) {
            if (!Files.exists(Path.of(array[0]))) {
                throw new IllegalArgumentException(String.format(
                        "Root folder %s is incorrect. Usage correct ROOT_FOLDER.", array[0]));
            }
            if (!array[1].contains(".")) {
                throw new IllegalArgumentException(String.format(
                        "File extension %s incorrect. Usage correct FILE-EXTENSION", array[1]));
            }
        } else {
            throw new IllegalArgumentException(
                    "Arguments are not equal to 2. Check send arguments.");
        }
    }
}