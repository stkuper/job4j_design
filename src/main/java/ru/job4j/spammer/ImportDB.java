package ru.job4j.spammer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class ImportDB {

    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        String str;
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            while ((str = rd.readLine()) != null) {
                if (!str.isBlank() && str.contains(";")) {
                    String[] lines = str.split(";", 2);
                    if (lines.length == 2 && (lines[0].isBlank() || lines[1].isBlank())) {
                        throw new IllegalArgumentException(
                                "UserName or UserEmail at line %s is empty".formatted("str"));
                    }
                    users.add(new User(lines[0], lines[1]));
                } else {
                    throw new IllegalArgumentException(
                            "Line %s is empty or without the symbol ';'".formatted(str));
                }
            }
        }
        return users;
    }

    public List<User> loadStream() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            return rd.lines()
                    .map(s -> s.split(";", 2))
                    .peek(this::check)
                    .map(result -> new User(result[0], result[1]))
                    .collect(Collectors.toList());
        }
    }

    public void check(String[] lines) {
        if (lines.length != 2 || lines[0].isBlank() || lines[1].isBlank()) {
            throw new IllegalArgumentException("Length of array not equal 2 or line is empty");
        }
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement(
                        "INSERT INTO users(name, email) VALUES(?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    public void createTable() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS users(
                name TEXT,
                email TEXT
                );
                """;
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password"));
             PreparedStatement statement = cnt.prepareStatement(sql)) {
            System.out.println(statement);
            statement.execute();
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./data/dump.txt");
        db.createTable();
        db.save(db.loadStream());
    }
}