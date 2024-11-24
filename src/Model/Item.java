package Model;

import java.util.ArrayList;
import java.util.List;

public class Item {
    private int id; // ID of the item (unique for each item)
    private String name; // Name of the item
    private int quantity; // Quantity of the item available

    // Static list to store predefined nouns
    private static final List<String> nouns = new ArrayList<>();

    // Static block to initialize the nouns list
    static {
        nouns.add("door");
        nouns.add("room");
        nouns.add("flashlight");
        nouns.add("award");
        nouns.add("upstairs");
        nouns.add("downstairs");
        nouns.add("key");
    }

    // Constructor to initialize the Item object with ID, name, and quantity
    public Item(int id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    // Getter method for id
    public int getId() {
        return id;
    }

    // Setter method for id
    public void setId(int id) {
        this.id = id;
    }

    // Getter method for name
    public String getName() {
        return name;
    }

    // Setter method for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter method for quantity
    public int getQuantity() {
        return quantity;
    }

    // Setter method for quantity
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Getter method for nouns list
    public static List<String> getNouns() {
        return nouns;
    }

    // Method to return a string representation of the Item object
    @Override
    public String toString() {
        return "Item{id=" + id + ", name='" + name + "', quantity=" + quantity + "}";
    }
}
