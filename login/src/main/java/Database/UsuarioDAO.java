package Database;

import java.sql.*;
import Domain.Usuario;

public class UsuarioDAO implements DAO<Usuario> {
    private ConnectionFactory instance;

    public UsuarioDAO() {
        instance = ConnectionFactory.getInstance();
    }

    //TODO: Create custom Exception
    //TODO: Ensure connection is closed even when exception is thrown
    @Override
    public void create(Usuario usuario) throws Exception {
        String sql = 
            String.format(
                "INSERT INTO [dbo].[Usuario] ([RA],[Nome],[Login],[Senha],[Tipo]) VALUES ("
                + "%ra"
                + ", \'%nome\' ,"
                + ", \'%login\' ,"
                + ", \'%senha\' ,"
                + ", \'%tipo\');"
                , usuario.ra, usuario.nome, usuario.login, usuario.senha, usuario.tipo);
            
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
    @Override
    public Usuario read(Integer id) throws Exception {
        String sql =
            "SELECT * FROM [dbo].[Usuario] WHERE [ID] = " + id;

        Connection connection = instance.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        connection.close();
        
        return deserialize(resultSet);
    }
    
    //TODO: Create custom Exception
    @Override
    public void update(Usuario usuario) throws Exception {
        String sql = 
            String.format(
                "UPDATE [dbo].[Usuario] SET "
                + "[Ra] = %ra'"
                + ", [Nome] = \'%nome\'"
                + ", [Login] = \'%login\'"
                + ", [Senha] = \'%senha\'"
                + ", [Tipo] = \'%tipo\'"
                + "WHERE [ID] = %id"
                , usuario.ra, usuario.nome, usuario.login, usuario.senha, usuario.tipo, usuario.id);

        Connection connection = instance.getConnection();
        Statement statement = connection.createStatement();
        statement.executeQuery(sql);
        connection.close();   
    }

    //TODO: Create custom Exception
    //TODO: Generalize this class
    //TODO: Consider using stored procedures instead
    //TODO: Ensure connection is closed even when exception is thrown
    @Override
    public void delete(Integer id) throws Exception {
        String sql = 
            "DELETE [dbo].[Usuario] WHERE [ID] = " + id;

        Connection connection = instance.getConnection();
        Statement statement = connection.createStatement();
        statement.executeQuery(sql);
        connection.close();        
    }
    
    private Usuario deserialize(ResultSet resultSet) {
        return null;
    }
}
