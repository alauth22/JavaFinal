package Model.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


import java.util.ArrayList;
import java.util.List;

public class Player {
    private int id; // ID of the player (unique for each player)
    private String name; // Name of the player
    private String achievement; // Achievement the player has earned
    private String item; // Current item the player possesses
    private int quantity; // Quantity of the current item

    // Static list to store available items
    private static final List<String> items = new ArrayList<>();

    // Static block to initialize the list of items
    static {
        items.add("door");
        items.add("room");
        items.add("flashlight");
        items.add("award");
        items.add("upstairs");
        items.add("downstairs");
        items.add("key");
    }

    // Constructor to initialize the Player object
    public Player(int id, String name, String achievement, String item, int quantity) {
        this.id = id;
        this.name = name;
        this.achievement = achievement;
        this.item = item;
        this.quantity = quantity;
    }

    // Getters and setters for all fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //Method-query to update Quantity
    public void updateQuantity(Database db, int newQuantity) {
        String query = "UPDATE player SET quantity = ? WHERE id = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, newQuantity); // Replace the first '?' with the new quantity
            pstmt.setInt(2, this.id);     // Replace the second '?' with the player's ID

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                this.quantity = newQuantity;
                System.out.println("Quantity updated successfully for Player ID: " + this.id);
            } else {
                System.out.println("No record found for the given ID.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating quantity: " + e.getMessage());
        }
    }

    //Method-query to update Achievement
    public void updateAchievement(Database db, String newAchievement){
        String query = "UPDATE player SET achievement = ? WHERE id ?";

        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Set the values for the placeholders
            pstmt.setString(1, newAchievement);  // Set the new achievement
            pstmt.setInt(2, this.id);  // Set the player's ID as the condition for update

            // Execute the update query
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                this.achievement = newAchievement;  // Update the achievement in the Player object
                System.out.println("Achievement updated successfully for Player ID: " + this.id);
            } else {
                System.out.println("No record found for the given Player ID.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating achievement: " + e.getMessage());
        }
    }

    // Method-query to update Item
    public void updateItem(Database db, String newItem) {
        String query = "UPDATE player SET item = ? WHERE id = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Set the values for the placeholders
            pstmt.setString(1, newItem);  // Set the new item
            pstmt.setInt(2, this.id);     // Set the player's ID as the condition for update

            // Execute the update query
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                this.item = newItem;  // Update the item in the Player object
                System.out.println("Item updated successfully for Player ID: " + this.id);
            } else {
                System.out.println("No record found for the given Player ID.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating item: " + e.getMessage());
        }
    }

    // Method-query to update Name
    public void updateName(Database db, String newName) {
        String query = "UPDATE player SET name = ? WHERE id = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Set the values for the placeholders
            pstmt.setString(1, newName);  // Set the new name
            pstmt.setInt(2, this.id);     // Set the player's ID as the condition for update

            // Execute the update query
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                this.name = newName;  // Update the name in the Player object
                System.out.println("Name updated successfully for Player ID: " + this.id);
            } else {
                System.out.println("No record found for the given Player ID.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating name: " + e.getMessage());
        }
    }


    // Static method to get the list of available items
    public static List<String> getItems() {
        return items;
    }

    // toString method to represent the Player object as a string
    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", achievement='" + achievement + '\'' +
                ", item='" + item + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}

