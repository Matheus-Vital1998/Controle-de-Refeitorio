package Database;

import Domain.Reserva;
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
                "INSERT INTO [dbo].[Reserva] ([UsuarioID],[CardapioID],[HorarioReserva]) VALUES ("
                + "%d"
                + ", %d"
                + ", '%s')"
                , reserva.usuarioID
                , reserva.cardapioID
                , reserva.horario.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        
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
            "SELECT * FROM [dbo].[Reserva] WHERE [ID] = " + id;

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
                "UPDATE [dbo].[Reserva] SET"
                + " [UsuarioID] = %d"
                + ", [CardapioID] = %d"
                + ", [HorarioReserva] = '%s'"
                + " WHERE [ID] = %d"
                , reserva.usuarioID
                , reserva.cardapioID
                , reserva.horario.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                , reserva.id);
        
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

        String sql = "DELETE [dbo].[Reserva] WHERE [ID] = " + id;
        
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
                reserva.id = result.getInt("ID");
                reserva.usuarioID = result.getInt("UsuarioID");
                reserva.cardapioID = result.getInt("CardapioID");
                reserva.horario = LocalDateTime.parse(result.getString("HorarioReserva"), DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss.S"));
            }
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return reserva;
    }    
}
