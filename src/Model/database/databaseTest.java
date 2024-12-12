package Model.database;

public class databaseTest {
    public static void main(String[] args) {
        Database db = new Database();

        // Assuming player ID 1 exists and the item is "Key"
        int playerId = 1;
        String item = "Key";  // The item to update (e.g., "Key")

        // Call the updateQuantity method with both playerId and item
        int updatedQuantity = db.updateQuantity(db, playerId, item);

        // Output the updated quantity
        System.out.println("Updated Quantity: " + updatedQuantity);
    }
}