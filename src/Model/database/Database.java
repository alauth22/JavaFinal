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

    // method-query that would return the quantity of an item for a specific player and item
    public int updateQuantity(Database db, int playerId, String item) {
        String getQuery = "SELECT Quantity FROM Player WHERE ID = ? AND Item = ?";
        String updateQuery = "UPDATE Player SET Quantity = ? WHERE ID = ? AND Item = ?";
        int updatedQuantity = 0;  // Default value in case of failure

        try (Connection conn = db.getConnection();
             PreparedStatement getPstmt = conn.prepareStatement(getQuery)) {

            // Get the current quantity of the specific item
            getPstmt.setInt(1, playerId); // Player's ID to retrieve quantity
            getPstmt.setString(2, item);  // Item name
            ResultSet rs = getPstmt.executeQuery();

            if (rs.next()) {
                int currentQuantity = rs.getInt("Quantity"); // Retrieve current quantity from database
                updatedQuantity = currentQuantity + 1; // Increment the quantity by 1 (add one item)

                // Now update the quantity in the database
                try (PreparedStatement updatePstmt = conn.prepareStatement(updateQuery)) {
                    updatePstmt.setInt(1, updatedQuantity);  // Set the new quantity
                    updatePstmt.setInt(2, playerId);         // Player's ID to update
                    updatePstmt.setString(3, item);          // Item name to update
                    int rowsAffected = updatePstmt.executeUpdate();

                    if (rowsAffected > 0) {
                        // If the update was successful, say confirmation
                        System.out.println("Quantity updated successfully for Player ID: " + playerId + " and Item: " + item);
                    } else {
                        System.out.println("No record found for the given Player ID and Item.");
                    }
                }
            } else {
                System.out.println("No record found for Player ID: " + playerId + " and Item: " + item);
            }

        } catch (SQLException e) {
            System.out.println("Error updating quantity: " + e.getMessage());
        }

        return updatedQuantity;  // Return updated quantity or 0 if unsuccessful
    }


}
