package Model.levels;

public class Item {
    private String name;
    private int quantity;

    // Constructor with name only
    public Item(String name) {
        this.name = name;
        this.quantity = 0; // Default quantity
    }

    // Constructor with name and quantity
    public Item(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    // Getter and Setter for quantity
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Create default items
    public static Model.levels.Item createKey() {
        return new Model.levels.Item("key");
    }

    public static Model.levels.Item createFlashlight() {
        return new Model.levels.Item("flashlight");
    }

    public static Model.levels.Item createMap() {
        return new Model.levels.Item("map");
    }
}


