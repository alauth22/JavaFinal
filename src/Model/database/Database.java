package Model.database;

import java.sql.*;

public class Database {
    private Connection databaseLink;

    // Method to establish a connection to the SQLite database
    public Connection getConnection() throws SQLException
    {
        Connection conn = null;
        try
        {
            conn = DriverManager.getConnection("jdbc:sqlite:src/Resources/FinalJava.db");
            System.out.println("Connection Established");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            throw e;
        }

        return conn;
    }
}