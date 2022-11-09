package Database;

import java.sql.*;
import java.time.LocalTime;

import Domain.Refeicao;

public class RefeicaoDAO implements DAO<Refeicao>{
    private ConnectionFactory instance;

    public RefeicaoDAO() {
        instance = ConnectionFactory.getInstance();
    }

    @Override
    public void create(Refeicao refeicao) {
        Connection connection = null;
        Statement statement = null;

        String sql = 
            String.format(
                "INSERT INTO [dbo].[Refeicao] ([Nome],[HorarioInicio],[HorarioFim],[HorarioLimiteReserva]) VALUES ("
                + "\'%s\'"
                + ", \'%s\'"
                + ", \'%s\'"
                + ", \'%s\');"
                , refeicao.nome
                , refeicao.horarioInicio
                , refeicao.horarioFim
                , refeicao.horarioLimiteReserva);
        
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
    public Refeicao read(Integer id) {
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        Refeicao refeicao = null;

        String sql =
            "SELECT * FROM [dbo].[Refeicao] WHERE [ID] = " + id;

        try {
            connection = instance.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            refeicao = deserialize(result);
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        finally {
            try { result.close(); } catch (Exception exception) {/* Ignored */}
            try { statement.close(); } catch (Exception exception) {/* Ignored */}
            try { connection.close(); } catch (Exception exception) {/* Ignored */}
        }

        return refeicao;
    }
    public Refeicao read(String nome_refeicao) {
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        Refeicao refeicao = null;

        String sql =
            "SELECT * FROM [dbo].[Refeicao] WHERE [Nome] = " + nome_refeicao;

        try {
            connection = instance.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            refeicao = deserialize(result);
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        finally {
            try { result.close(); } catch (Exception exception) {/* Ignored */}
            try { statement.close(); } catch (Exception exception) {/* Ignored */}
            try { connection.close(); } catch (Exception exception) {/* Ignored */}
        }

        return refeicao;
    }

    @Override
    public void update(Refeicao refeicao) {
        Connection connection = null;
        Statement statement = null;

        String sql = 
            String.format(
                "UPDATE [dbo].[Refeicao] SET"
                + "[Nome] = \'%s\'"
                + ", [HorarioInicio] = \'%s\'"
                + ", [HorarioFim] = \'%s\'"
                + ", [HorarioLimiteReserva] = \'%s\'"
                + "WHERE [ID] = %d"
                , refeicao.nome
                , refeicao.horarioInicio
                , refeicao.horarioFim
                , refeicao.horarioLimiteReserva
                , refeicao.id);

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

        String sql = 
            "DELETE [dbo].[Refeicao] WHERE [ID] = " + id;

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

    private Refeicao deserialize(ResultSet result) {
        Refeicao refeicao = new Refeicao();
        try {
            while (result.next()) {
                refeicao.id = result.getInt("ID");
                refeicao.nome = result.getString("Nome");
                refeicao.horarioInicio = LocalTime.parse(result.getString("HorarioInicio"));
                refeicao.horarioFim = LocalTime.parse(result.getString("HorarioFim"));
                refeicao.horarioLimiteReserva = LocalTime.parse(result.getString("HorarioLimiteReserva"));
            }
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return refeicao;
    }
}
