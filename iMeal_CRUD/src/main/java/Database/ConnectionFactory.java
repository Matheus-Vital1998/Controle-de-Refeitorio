package Database;

import java.sql.*;

public class ConnectionFactory {
    
    //Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
    private final String connectionString = "jdbc:derby://localhost:1527/iMeal";

    private static ConnectionFactory instance = null;

    private ConnectionFactory() { }

    public static ConnectionFactory getInstance(){
        if (instance == null)
            instance = new ConnectionFactory();

        return instance;
    }

    public Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(connectionString,"iMeal","iMeal");
            return connection;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }
}
