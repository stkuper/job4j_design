package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, List<Path>> filePropertyListMap = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (Files.isRegularFile(file)) {
            FileProperty fileProperty = new FileProperty(Files.size(file),
                    file.getFileName().toString());
            if (filePropertyListMap.containsKey(fileProperty)) {
                filePropertyListMap.get(fileProperty).add(file);
            }
            filePropertyListMap.putIfAbsent(fileProperty,
                    new ArrayList<>(List.of(file)));
        }
        return super.visitFile(file, attrs);
    }

    public void demonstrateDuplicates() {
        for (FileProperty fileProperty : filePropertyListMap.keySet()) {
            if (filePropertyListMap.get(fileProperty).size() > 1) {
                System.out.printf("Filename %s,  size %d : location %n",
                        fileProperty.getName(), fileProperty.getSize());
                for (Path path : filePropertyListMap.get(fileProperty)) {
                    System.out.println(path.toAbsolutePath());
                }
            }
        }
    }
}
