package com.hospitalmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	public static Connection initializeDatabase() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Register the MySQL JDBC driver
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }

        String url = "jdbc:mysql://localhost:3306/training";
        String username = "root"; // Replace with your database username
        String password = "123456"; // Replace with your database password

        return DriverManager.getConnection(url, username, password);
    }

    public static void closeDatabase(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
