package Database;

import java.sql.*;

import Domain.TipoUsuario;
import Domain.Usuario;

public class UsuarioDAO implements DAO<Usuario> {
    private ConnectionFactory instance;

    public UsuarioDAO() {
        instance = ConnectionFactory.getInstance();
    }

    //TODO: Create custom Exception
    @Override
    public void create(Usuario usuario) {
        Connection connection = null;
        Statement statement = null;

        String sql = 
            String.format(
                "INSERT INTO USUARIO (RA, NOME, LOGIN, SENHA, TIPO) VALUES ("
                + "\'%s\'"
                + ", \'%s\'"
                + ", \'%s\'"
                + ", \'%s\'"
                + ", \'%s\');"
                , usuario.ra, usuario.nome, usuario.login, usuario.senha, usuario.tipo);
        
        try {
            connection = instance.getConnection();
            statement = connection.createStatement();
            statement.execute(sql);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        finally {
            try { statement.close(); } catch (Exception exception) {/* Ignored */}
            try { connection.close(); } catch (Exception exception) {/* Ignored */}
        }
    }

    //TODO: Create custom Exception
    //TODO: Do not return empty user inside catch
    //TODO: Generalize this class
    //TODO: Consider using stored procedures instead
    //TODO: Ensure connection is closed even when exception is thrown
    @Override
    public Usuario read(Integer id) {
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        Usuario usuario = null;

        String sql =
            "SELECT * FROM USUARIO WHERE ID = " + id;

        try {
            connection = instance.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            usuario = deserialize(result);
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        finally {
            try { result.close(); } catch (Exception exception) {/* Ignored */}
            try { statement.close(); } catch (Exception exception) {/* Ignored */}
            try { connection.close(); } catch (Exception exception) {/* Ignored */}
        }

        return usuario;
    }
    
    //TODO: Create custom Exception
    @Override
    public void update(Usuario usuario) {
        Connection connection = null;
        Statement statement = null;

        String sql = 
            String.format(
                "UPDATE USUARIO SET "
                + "RA = \'%s\'"
                + ", NOME = \'%s\'"
                + ", LOGIN = \'%s\'"
                + ", SENHA = \'%s\'"
                + ", TIPO = \'%s\'"
                + "WHERE ID = %d"
                , usuario.ra, usuario.nome, usuario.login, usuario.senha, usuario.tipo, usuario.id);
        
        try {
            connection = instance.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        finally {
            try { statement.close(); } catch (Exception exception) {/* Ignored */}
            try { connection.close(); } catch (Exception exception) {/* Ignored */}
        }
    }

    //TODO: Create custom Exception
    //TODO: Generalize this class
    //TODO: Consider using stored procedures instead
    //TODO: Ensure connection is closed even when exception is thrown
    @Override
    public void delete(Integer id) {
        Connection connection = null;
        Statement statement = null;

        String sql = 
            "DELETE FROM USUARIO WHERE ID = " + id;

        try {
            connection = instance.getConnection();
            statement = connection.createStatement();
            statement.execute(sql);
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        finally {
            try { statement.close(); } catch (Exception exception) {/* Ignored */}
            try { connection.close(); } catch (Exception exception) {/* Ignored */}
        }
    }
    
    private Usuario deserialize(ResultSet result) {
        Usuario usuario = new Usuario();
        try {
            while (result.next()) {
                usuario.id = result.getInt("ID");
                usuario.nome = result.getString("NOME");
                usuario.ra = result.getString("RA");
                usuario.login = result.getString("LOGIN");
                usuario.senha = result.getString("SENHA");
                usuario.tipo = TipoUsuario.valueOf(result.getString("TIPO"));
            }
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return usuario;
    }
    
    
    public Usuario authenticate(String login, String senha) {
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        Usuario usuario = null;

        String sql =
            String.format("SELECT * FROM USUARIO WHERE LOGIN = '%s' And SENHA = '%s'", login, senha);

        try {
            connection = instance.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            usuario = deserialize(result);
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        finally {
            try { result.close(); } catch (Exception exception) {/* Ignored */}
            try { statement.close(); } catch (Exception exception) {/* Ignored */}
            try { connection.close(); } catch (Exception exception) {/* Ignored */}
        }

        return usuario;
    }
}
