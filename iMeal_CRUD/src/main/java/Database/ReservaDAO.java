package Database;

import Domain.Cardapio;
import Domain.Reserva;
import Domain.Usuario;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReservaDAO implements DAO<Reserva> {
    
    private ConnectionFactory instance;

    public ReservaDAO() {
        instance = ConnectionFactory.getInstance();
    }

    @Override
    public void create(Reserva reserva) {
        Connection connection = null;
        Statement statement = null;

        String sql = 
            String.format(
                "INSERT INTO RESERVA (USUARIO_ID, CARDAPIO_ID, HORARIO_RESERVA) VALUES ("
                + "%d"
                + ", %d"
                + ", '%s')"
                , reserva.getUsuario().getId()
                , reserva.getCardapio().getId()
                , reserva.getHorario().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replaceAll("T"," "));
        
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

    @Override
    public Reserva read(Integer id) {
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        Reserva reserva = null;

        String sql = 
            "SELECT * FROM RESERVA WHERE ID = " + id;

        try {
            connection = instance.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            reserva = deserialize(result);
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        finally {
            try { result.close(); } catch (Exception exception) {/* Ignored */}
            try { statement.close(); } catch (Exception exception) {/* Ignored */}
            try { connection.close(); } catch (Exception exception) {/* Ignored */}
        }
        
        return reserva;
    }
    public Reserva read(Usuario usuario, Cardapio cardapio) {
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        Reserva reserva = null;

        String sql = 
            "SELECT * FROM RESERVA WHERE USUARIO_ID = " + usuario.getId() 
                + " And CARDAPIO_ID = " + cardapio.getId();

        try {
            connection = instance.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            reserva = deserialize(result);
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        finally {
            try { result.close(); } catch (Exception exception) {/* Ignored */}
            try { statement.close(); } catch (Exception exception) {/* Ignored */}
            try { connection.close(); } catch (Exception exception) {/* Ignored */}
        }
        
        return reserva;
    }

    @Override
    public void update(Reserva reserva) {
        Connection connection = null;
        Statement statement = null;

        String sql = 
            String.format(
                "UPDATE RESERVA SET"
                + " USUARIO_ID = %d"
                + ", CARDAPIO_ID = %d"
                + ", HORARIO_RESERVA = '%s'"
                + " WHERE ID = %d"
                , reserva.getUsuario().getId()
                , reserva.getCardapio().getId()
                , reserva.getHorario().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                , reserva.getId());
        
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

    @Override
    public void delete(Integer id) {
        Connection connection = null;
        Statement statement = null;

        String sql = "DELETE FROM RESERVA WHERE ID = " + id;
        
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

    private Reserva deserialize(ResultSet result){
        Reserva reserva = new Reserva();
        try {
            while (result.next()) {
                reserva.setId(result.getInt("ID"));
                
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                Usuario usuarioAux = usuarioDAO.read(result.getInt("USUARIO_ID"));
                reserva.setUsuario(usuarioAux);
                
                CardapioDAO cardapioDAO = new CardapioDAO();
                Cardapio cardapioAux = cardapioDAO.read(result.getInt("CARDAPIO_ID"));
                reserva.setCardapio(cardapioAux);
                
                reserva.setHorario(LocalDateTime.parse(result.getString("HORARIO_RESERVA"), DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss.S")));
            }
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return reserva;
    }    
}
