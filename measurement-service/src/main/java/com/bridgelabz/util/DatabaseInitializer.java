package com.bridgelabz.util;

import com.bridgelabz.exceptions.DatabaseException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Statement;
import java.util.stream.Collectors;

public class DatabaseInitializer {

    public static void initializeSchema() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        try (InputStream inputStream = DatabaseInitializer.class.getClassLoader()
                .getResourceAsStream("db/schema.sql")) {

            if (inputStream == null) {
                throw new DatabaseException("schema.sql not found");
            }

            String sql = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));

            try (Statement statement = connection.createStatement()) {
                for (String query : sql.split(";")) {
                    if (!query.trim().isEmpty()) {
                        statement.execute(query.trim());
                    }
                }
            }

        } catch (Exception e) {
            throw new DatabaseException("Schema initialization failed", e);
        } finally {
            pool.releaseConnection(connection);
        }
    }
}
