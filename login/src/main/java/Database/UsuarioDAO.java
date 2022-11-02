package Database;

import java.sql.*;
import Domain.Usuario;

public class UsuarioDAO implements Dao<Usuario> {
    private ConnectionFactory instance;

    public UsuarioDAO() {
        instance = ConnectionFactory.getInstance();
    }

    //TODO: Create custom Exception
    //TODO: Ensure connection is closed even when exception is thrown
    public void create(Usuario user) throws Exception {
        String sql = 
            "INSERT INTO [dbo].[User] ([Name],[RA],[Login],[Password]) VALUES" +
            "(\'"+user.name+"\','"+user.ra+"\',\'"+user.login+"\',\'"+user.password+"\');";
            
        Connection connection = instance.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(sql);
        connection.close();
    }

    //TODO: Create custom Exception
    //TODO: Do not return empty user inside catch
    //TODO: Generalize this class
    //TODO: Consider using stored procedures instead
    //TODO: Ensure connection is closed even when exception is thrown
    public Usuario read(Integer id) throws Exception {
        String sql =
            "SELECT * FROM [dbo].[User] WHERE [ID] = " + id;

        Connection connection = instance.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        connection.close();
        Usuario user = CreateUserFromResultSet(resultSet);
        return user;
    }
    
    //TODO: Create custom Exception
    public void update(Usuario user) throws Exception {
        String sql = 
            "UPDATE [dbo].[User] SET "
            + "[Name] = " + user.name
            + ", [Ra] = " + user.ra
            + ", [Login] = " + user.login
            + ", [Password] = " + user.password
            + "WHERE [ID] = " + user.id;

        Connection connection = instance.getConnection();
        Statement statement = connection.createStatement();
        statement.executeQuery(sql);
        connection.close();   
    }

    //TODO: Create custom Exception
    //TODO: Generalize this class
    //TODO: Consider using stored procedures instead
    //TODO: Ensure connection is closed even when exception is thrown
    public void delete(Integer id) throws Exception {
        String sql = 
            "DELETE [dbo].[User] WHERE [ID] = "+ id;

        Connection connection = instance.getConnection();
        Statement statement = connection.createStatement();
        statement.executeQuery(sql);
        connection.close();        
    }

    public Boolean Authenticate(String login, String password) throws Exception {
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
    
    private Usuario CreateUserFromResultSet(ResultSet resultSet) {
        return null;
    }
}
