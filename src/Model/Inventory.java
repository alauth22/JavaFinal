package Model;

public class Inventory {
    private int id; // ID of the inventory item (unique for each inventory item)
    private String name; // Name of the inventory item
    private int quantity; // Quantity of this item in the inventory

    // Constructor to initialize the Inventory object with id, name, and quantity
    public Inventory(int id, String name, int quantity) {
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

    // Method to return a string representation of the Inventory object
    @Override
    public String toString() {
        return "Inventory{id=" + id + ", name='" + name + "', quantity=" + quantity + "}";
    }
}

