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
        if (array.length == 0) {
            throw new IllegalArgumentException(
                    "Root folder and file extension is null. Usage Root_Folder.");
        }
        if (array.length < 2) {
            throw new IllegalArgumentException("Arguments less 2. Check send arguments.");
        }
    }
}