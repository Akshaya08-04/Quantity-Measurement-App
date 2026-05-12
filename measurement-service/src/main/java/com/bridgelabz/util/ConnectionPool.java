package com.bridgelabz.util;

import com.bridgelabz.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
    private static ConnectionPool instance;

    private final List<Connection> availableConnections = new ArrayList<>();
    private final List<Connection> usedConnections = new ArrayList<>();

    private final String url;
    private final String username;
    private final String password;
    private final int initialSize;
    private final int maxSize;

    private ConnectionPool() {
        ApplicationConfig config = ApplicationConfig.getInstance();
        this.url = config.getProperty("db.url");
        this.username = config.getProperty("db.username");
        this.password = config.getProperty("db.password");
        this.initialSize = config.getIntProperty("db.pool.initialSize", 2);
        this.maxSize = config.getIntProperty("db.pool.maxSize", 5);

        try {
            Class.forName(config.getProperty("db.driver"));
            for (int i = 0; i < initialSize; i++) {
                availableConnections.add(createConnection());
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw DatabaseException.connectionFailed("Failed to initialize connection pool", e);
        }
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public synchronized Connection getConnection() {
        try {
            if (!availableConnections.isEmpty()) {
                Connection connection = availableConnections.remove(0);
                usedConnections.add(connection);
                return connection;
            }

            if (usedConnections.size() < maxSize) {
                Connection connection = createConnection();
                usedConnections.add(connection);
                return connection;
            }

            throw new DatabaseException("Connection pool exhausted");
        } catch (SQLException e) {
            throw DatabaseException.connectionFailed("Unable to get connection", e);
        }
    }

    public synchronized void releaseConnection(Connection connection) {
        if (connection != null) {
            usedConnections.remove(connection);
            availableConnections.add(connection);
        }
    }

    public synchronized void shutdown() {
        for (Connection connection : availableConnections) {
            closeQuietly(connection);
        }
        for (Connection connection : usedConnections) {
            closeQuietly(connection);
        }
        availableConnections.clear();
        usedConnections.clear();
    }

    private void closeQuietly(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException ignored) {
        }
    }
}