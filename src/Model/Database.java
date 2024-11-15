package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

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

    // Method to add an item to the database
    public void addItem(Item item) throws SQLException {
        String query = "INSERT INTO items (id, name, quantity) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, item.getId());
            pstmt.setString(2, item.getName());
            pstmt.setInt(3, item.getQuantity());
            pstmt.executeUpdate();
            System.out.println("Item added: " + item);
        }
    }

    // Method to delete an item from the database
    public void deleteItem(int id) throws SQLException {
        String query = "DELETE FROM items WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Item deleted with ID: " + id);
        }
    }

    // Method to update an item's quantity in the database
    public void updateItemQuantity(int id, int newQuantity) throws SQLException {
        String query = "UPDATE items SET quantity = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, newQuantity);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("Item updated: ID=" + id + ", New Quantity=" + newQuantity);
        }
    }
}