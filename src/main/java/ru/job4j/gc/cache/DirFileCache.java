package ru.job4j.gc.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String str = null;
        try {
            str = Files.readString(Path.of(String.format("%s%s%s", cachingDir, "\\", key)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}