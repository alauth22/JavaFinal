package Model;
import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    public Connection databaseLink; // Declare a Connection object to hold the database link

    //Method to establish a connection to the database
    public Connection getConnection(){
        String databaseUser = "root";
        String databasePassword = "javaproject2024-";
        String url = "jdbc:mysql://localhost:3306/finaljava";

        try{
            //Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish the connection using the DriverManager and store it in the databaselink
            databaseLink = DriverManager.getConnection(url,databaseUser, databasePassword);

        }catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return databaseLink; //Return the established connection
    }
}

//switch statement to call the correct verb method
//Singleton for the map quest
//flat filer to help log user input
//have nested values