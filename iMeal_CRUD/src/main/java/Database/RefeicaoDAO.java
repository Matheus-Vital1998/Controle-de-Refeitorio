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
                "INSERT INTO REFEICAO (NOME, HORARIO_INICIO, HORARIO_FIM, HORARIO_LIMITE_RESERVA) VALUES ("
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
            "SELECT * FROM REFEICAO WHERE ID = " + id;

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
            "SELECT * FROM REFEICAO WHERE NOME = '" + nome_refeicao + "'";

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
                "UPDATE REFEICAO SET "
                + "NOME = \'%s\'"
                + ", HORARIO_INICIO = \'%s\'"
                + ", HORARIO_FIM = \'%s\'"
                + ", HORARIO_LIMITE_RESERVA = \'%s\'"
                + "WHERE ID = %d"
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
            "DELETE FROM REFEICAO WHERE ID = " + id;

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
                refeicao.nome = result.getString("NOME");
                refeicao.horarioInicio = LocalTime.parse(result.getString("HORARIO_INICIO"));
                refeicao.horarioFim = LocalTime.parse(result.getString("HORARIO_FIM"));
                refeicao.horarioLimiteReserva = LocalTime.parse(result.getString("HORARIO_LIMITE_RESERVA"));
            }
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return refeicao;
    }
}
