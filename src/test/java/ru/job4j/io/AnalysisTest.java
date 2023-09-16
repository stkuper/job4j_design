package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class AnalysisTest {
    @Test
    void analysisTest(@TempDir Path tempDir) throws IOException {
        Analysis analysis = new Analysis();
        File sourse = tempDir.resolve("analysisSource.txt").toFile();
        try (PrintWriter writer = new PrintWriter(sourse)) {
            writer.println("""
                    200 10:56:01
                    500 10:57:01
                    400 10:58:01
                    300 10:59:01
                    500 11:01:02
                    200 11:02:02""");
            writer.println("""
                    200 12:56:01
                    500 12:57:01
                    400 12:58:01
                    500 12:59:01
                    400 13:01:02
                    300 13:02:02""");
        }
        File target = tempDir.resolve("analysisTarget.txt").toFile();
        analysis.unavailable(sourse.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines()
                    .forEach(result::append);
        }
        assertThat("10:57:01;10:59:01;11:01:02;11:02:02;12:57:01;13:02:02;")
                .isEqualTo(result.toString());
    }
}