package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            Class.forName(properties.getProperty("db.driver"));
            connection = DriverManager.getConnection(properties.getProperty("db.url"),
                    properties.getProperty("db.login"), properties.getProperty("db.password"));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable(String tableName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "CREATE TABLE IF NOT EXISTS %s(%s, %s);",
                    tableName,
                    "id SERIAL PRIMARY KEY",
                    "name TEXT"
            );
            statement.execute(sql);
            System.out.println(getTableScheme(tableName));
        }
    }

    public void dropTable(String tableName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "DROP TABLE IF EXISTS %s;",
                    tableName
            );
            statement.execute(sql);
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE IF EXISTS %s "
                            + "ADD COLUMN %s %s;",
                    tableName,
                    columnName,
                    type
            );
            statement.execute(sql);
            System.out.println(getTableScheme(tableName));
        }
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE IF EXISTS %s "
                            + "DROP COLUMN %s;",
                    tableName,
                    columnName
            );
            statement.execute(sql);
            System.out.println(getTableScheme(tableName));
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName)
            throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE IF EXISTS %s "
                            + "RENAME COLUMN %s TO %s;",
                    tableName,
                    columnName,
                    newColumnName
            );
            statement.execute(sql);
            System.out.println(getTableScheme(tableName));
        }
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i)));
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties prop = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader()
                .getResourceAsStream("application.properties")) {
            prop.load(in);
        }
        TableEditor editor = new TableEditor(prop);
        editor.createTable("test");
        editor.addColumn("test", "number", "decimal");
        editor.dropColumn("test", "name");
        editor.renameColumn("test", "number", "НОМЕР");
        editor.dropTable("test");
    }
}