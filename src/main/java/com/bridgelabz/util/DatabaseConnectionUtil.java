package com.bridgelabz.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/quantity_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Carmel*2004"; // change if your password is different

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to database", e);
        }
    }
}