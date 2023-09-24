package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zipOut = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zipOut.putNextEntry(new ZipEntry(source.toFile().getPath()));
                try (BufferedInputStream outBuff = new BufferedInputStream(
                        new FileInputStream(source.toFile()))) {
                    zipOut.write(outBuff.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(
                    new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validateParams(ArgsName argsName) {
        if (!Files.exists(Path.of(argsName.get("d")))) {
            throw new IllegalArgumentException(String.format(
                    "Directory '%s' is not exists", argsName.get("d")
            ));
        }
        if (!argsName.get("e").contains(".")) {
            throw new IllegalArgumentException(String.format(
                    "File existanse '%s' is incorrect", argsName.get("e")
            ));
        }
        if (!argsName.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException(String.format(
                    "File existance '%s' must be '.zip'", argsName.get("o")
            ));
        }
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException(
                    "Must be 3 arguments. Check the arguments");
        }
        Zip zip = new Zip();
        ArgsName argsName = ArgsName.of(args);
        validateParams(argsName);
        List<Path> pathList = Search.search(Path.of(argsName.get("d")),
                f -> !f.toString().endsWith(argsName.get("e")));
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        zip.packFiles(pathList, new File(argsName.get("o")));
    }
}
