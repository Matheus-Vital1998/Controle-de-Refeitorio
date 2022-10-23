package Database;

import java.sql.*;

import Domain.User;

public class UserDAO {
    private static ConnectionFactory instance = ConnectionFactory.getInstance();

    //TODO: Implement exception handling
    //TODO: Ensure connection is closed even when exception is thrown
    public static void Create(User user) throws Exception {
        String sql = 
            "INSERT INTO [dbo].[User] ([Name],[RA],[Login],[Password]) VALUES" +
            "(\'"+user.name+"\','"+user.ra+"\',\'"+user.login+"\',\'"+user.password+"\');";
        
        try (Connection connection = instance.getConnection()) {            
            Statement statement = connection.createStatement();
            statement.execute(sql);
            connection.close();
        }
    }

    public static Boolean Authenticate(String login, String password) throws Exception {
        String sql = 
            "SELECT * FROM [dbo].[User]"
            + " WHERE [Login] = " + login
            + " AND [Password] = " + password;

        try (Connection connection = instance.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            return resultSet.next();
        }
    }

    //TODO: Iterate through all possible results
    //TODO: Implement exception handling
    //TODO: Generalize this class
    //TODO: Do not return empty user
    private static User CreateUserFromResultSet(ResultSet resultSet) {
        User user = new User();
        
        try {
            resultSet.next();
            user.id = resultSet.getInt("ID");
            user.name = resultSet.getString("Name");
            user.ra = resultSet.getString("RA");
            user.login = resultSet.getString("Login");
            user.password = resultSet.getString("Password");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return user;
    }

    /*
    //TODO: Implement exception handling
    //TODO: Do not return empty user inside catch
    //TODO: Generalize this class
    //TODO: Consider using stored procedures instead
    //TODO: Ensure connection is closed even when exception is thrown
    public static User Read(int id) {
        String sql = 
            "SELECT * FROM [dbo].[User] WHERE [ID] = " + id;

        try {
            Connection connection = instance.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            connection.close();
            User user = CreateUserFromResultSet(resultSet);
            return user;
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            return new User();
        }        
    }
    
    //TODO: Delete specific user
    //TODO: Implement exception handling
    //TODO: Generalize this class
    //TODO: Consider using stored procedures instead
    //TODO: Ensure connection is closed even when exception is thrown
    public static void Delete() {
        String sql = 
            "DELETE [dbo].[User]";

        try {
            Connection connection = instance.getConnection();
            Statement statement = connection.createStatement();
            statement.executeQuery(sql);
            connection.close();
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        
    }
    
    public static User Update(User user) {
        try {
            Connection connection = instance.getConnection();
        }
        catch (Exception exception) {
            throw exception;
        }
    }
    */
}
