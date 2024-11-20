package Model;
import java.sql.SQLException;
//assignment project 4.5
public class Main {
    public static void main(String[] args) {
        Database db = new Database();

        try {
            // Add a new item
            Item newItem = new Item(1, "Laptop", 10);
            db.addItem(newItem);

            // Retrieve the item from the database
            Item retrievedItem = db.getItemById(1);
            if (retrievedItem != null) {
                System.out.println("Retrieved Item: " + retrievedItem);
            }

            // Update the item's quantity
            db.updateItemQuantity(1, 20);

            // Retrieve the updated item from the database
            Item updatedItem = db.getItemById(1);
            if (updatedItem != null) {
                System.out.println("Updated Item: " + updatedItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
