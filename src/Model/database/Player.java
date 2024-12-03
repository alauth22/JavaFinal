package Model.database;

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

    // Method to decrease the quantity of the item
    public void decreaseItemQuantity(int decrement) {
        if (quantity >= decrement) {
            quantity -= decrement;
        } else {
            System.out.println("Not enough quantity to decrease.");
        }
    }

    // Method to update the quantity of the item
    public void updateItemQuantity(int newQuantity) {
        if (newQuantity >= 0) {
            this.quantity = newQuantity;
        } else {
            System.out.println("Quantity cannot be negative.");
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

