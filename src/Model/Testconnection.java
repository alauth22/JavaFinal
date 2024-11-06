package Model;
import java.sql.Connection;

//class to test database connection
public class Testconnection {
    public static void main(String[] args) {
        Database db = new Database();
        Connection connection = db.getConnection();

        if (connection != null) {
            System.out.println("Database connection established successfully!");
        } else {
            System.out.println("Failed to establish database connection.");
        }
    }
}
