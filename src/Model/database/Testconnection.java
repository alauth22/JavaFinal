package Model.database;
import Model.Database;

import java.sql.Connection;
import java.sql.SQLException;

//class to test database connection
public class Testconnection {
    public static void main(String[] args) throws SQLException {
        Model.Database db = new Database();
        Connection connection = db.getConnection();

        if (connection != null) {
            System.out.println("Database connection established successfully!");
        } else {
            System.out.println("Failed to establish database connection.");
        }
    }
}
