package Database;

import java.sql.*;

public class ConnectionFactory {
    
    private final String connectionString = "jdbc:sqlserver://imeal.database.windows.net:1433;"
        + "database=iMeal;"
        + "user=imeal@imeal;"
        + "password=Password#123;"
        + "encrypt=true;"
        + "trustServerCertificate=false;"
        + "hostNameInCertificate=*.database.windows.net;"
        + "loginTimeout=30;";

    private static ConnectionFactory instance = null;

    private ConnectionFactory() { }

    public static ConnectionFactory getInstance(){
        if (instance == null)
            instance = new ConnectionFactory();

        return instance;
    }

    public Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(connectionString);            
            return connection;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }
}
