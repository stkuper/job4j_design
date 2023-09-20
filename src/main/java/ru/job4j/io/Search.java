package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) {
        Path start = Paths.get(".");
        search(start, path -> path.toFile().getName().endsWith(".js")).forEach(System.out::println);
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
}
