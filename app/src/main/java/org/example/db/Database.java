package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.flywaydb.core.Flyway;

public class Database {

    private static final String URL = "jdbc:h2:./test";

    static {
        Flyway flyway = Flyway.configure()
                .dataSource(URL, null, null)
                .load();

        flyway.migrate();
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}