package Model;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items; // List to store all inventory items

    // Constructor to initialize the inventory list
    public Inventory() {
        this.items = new ArrayList<>();
    }

    // Method to add an item to the inventory
    public void addItem(Item item) {
        items.add(item);
        System.out.println("Item added to inventory: " + item);
    }

    // Method to remove an item from the inventory by ID
    public void removeItem(int id) {
        items.removeIf(item -> item.getId() == id);
        System.out.println("Item removed with ID: " + id);
    }

    // Method to update the quantity of an item in the inventory
    public void updateItemQuantity(int id, int quantityChange) {
        for (Item item : items) {
            if (item.getId() == id) {
                int newQuantity = item.getQuantity() + quantityChange;
                if (newQuantity < 0) {
                    System.out.println("Not enough quantity available for item: " + item.getName());
                } else {
                    item.setQuantity(newQuantity);
                    System.out.println("Item updated: " + item);
                }
                return;
            }
        }
        System.out.println("Item not found with ID: " + id);
    }


    // Method to display all items in the inventory
    public void displayInventory() {
        System.out.println("Current Inventory:");
        for (Item item : items) {
            System.out.println(item);
        }
    }

    // Method to handle a user taking an item
    public void takeItem(int id, int quantity) {
        updateItemQuantity(id, -quantity);
    }
}
