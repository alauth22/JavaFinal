package Model.database;

import java.sql.*;
import java.io.File;

public class Database {
    private Connection databaseLink;

    // Method to establish a connection to the SQLite database
    public Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            // absolute path of the database file
            System.out.println("Database Path: " + new File("src/Resources/FinalJava1.db").getAbsolutePath());

            conn = DriverManager.getConnection("jdbc:sqlite:src/Resources/FinalJava1.db");
            System.out.println("Connection Established");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }

        return conn;
    }

    // Increment the quantity of a specific item for a player and return the updated value
    public int updateQuantity(Database db, int playerId, String item) {
        String updateQuery = "UPDATE Player SET Quantity = Quantity + 1 WHERE ID = ? AND Item = ?";
        String getQuery = "SELECT Quantity FROM Player WHERE ID = ? AND Item = ?";
        int updatedQuantity = -1; // Default value to indicate failure

        try (Connection conn = db.getConnection();
             PreparedStatement updatePstmt = conn.prepareStatement(updateQuery);
             PreparedStatement getPstmt = conn.prepareStatement(getQuery)) {

            // Step 1: Increment the Quantity directly in the database
            updatePstmt.setInt(1, playerId);
            updatePstmt.setString(2, item);
            int rowsAffected = updatePstmt.executeUpdate();

            if (rowsAffected > 0) {
                // Step 2: Retrieve the updated Quantity
                getPstmt.setInt(1, playerId);
                getPstmt.setString(2, item);
                try (ResultSet rs = getPstmt.executeQuery()) {
                    if (rs.next()) {
                        updatedQuantity = rs.getInt("Quantity");
                        System.out.println("Quantity updated successfully for Player ID: " + playerId + " and Item: " + item);
                    }
                }
            } else {
                System.out.println("No record found for Player ID: " + playerId + " and Item: " + item);
            }

        } catch (SQLException e) {
            System.err.println("Error updating quantity: " + e.getMessage());
        }

        return updatedQuantity;
    }



    public void resetAllQuantities(Database db) {
        String resetQuery = "UPDATE Player SET Quantity = 0"; // Updates all items to Quantity 0

        try (Connection conn = db.getConnection();
             PreparedStatement resetPstmt = conn.prepareStatement(resetQuery)) {

            int rowsAffected = resetPstmt.executeUpdate(); // Execute the query and get affected rows
            System.out.println("Reset successful: " + rowsAffected + " rows updated to Quantity 0.");

        } catch (SQLException e) {
            System.out.println("Error resetting quantities: " + e.getMessage());
        }
    }

    public void getAllQuantities(Database db) {
        String selectQuery = "SELECT * FROM Player"; // Select all rows from the Player table

        try (Connection conn = db.getConnection();
             PreparedStatement selectPstmt = conn.prepareStatement(selectQuery);
             ResultSet rs = selectPstmt.executeQuery()) { // Use executeQuery() for SELECT statements

            while (rs.next()) {
                int playerId = rs.getInt("ID");
                String item = rs.getString("Item");
                int quantity = rs.getInt("Quantity");

                // Display the result
                System.out.println("Player ID: " + playerId + ", Item: " + item + ", Quantity: " + quantity);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching quantities: " + e.getMessage());
        }
    }

}
