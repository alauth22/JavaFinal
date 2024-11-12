package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private Connection databaseLink;

    // Method to establish a connection to the SQLite database
    public Connection getConnection() {
        // Use a relative path to the database file in the resources folder
        String url = "jdbc:sqlite:src/Resources/FinalJava.db";

        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
            // Establish the connection to the SQLite database
            databaseLink = DriverManager.getConnection(url);
            System.out.println("Connected to SQLite database!");
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC Driver not found");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection to SQLite failed");
            e.printStackTrace();
        }

        return databaseLink; // Return the established connection
    }
}