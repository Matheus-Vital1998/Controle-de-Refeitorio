package Database;

import java.sql.*;
import java.time.LocalDate;

import Domain.Cardapio;

public class CardapioDAO implements DAO<Cardapio> {

    private ConnectionFactory instance;

    public CardapioDAO() {
        instance = ConnectionFactory.getInstance();
    }

    @Override
    public void create(Cardapio cardapio) {
        Connection connection = null;
        Statement statement = null;

        String sql =
            String.format(
                "INSERT INTO iMeal.CARDAPIO VALUES ("
                + "%d"
                + ", '%s'"
                + ", '%s')"
                , cardapio.refeicaoID
                , cardapio.data
                , cardapio.descricao);
        
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
    public Cardapio read(Integer id) {
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        Cardapio cardapio = null;
        
        String sql = "SELECT * FROM iMeal.CARDAPIO WHERE ID = " + id;
        
        try {
            connection = instance.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            cardapio = deserialize(result);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        finally {
            try { statement.close(); } catch (Exception exception) {/* Ignored */}
            try { connection.close(); } catch (Exception exception) {/* Ignored */}
        }
        
        return cardapio;
    }
    public Cardapio read(LocalDate data, Integer idRefeicao) {
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        Cardapio cardapio = null;
        
        String sql = "SELECT * FROM iMeal.CARDAPIO WHERE REFEICAO_ID = " 
                + idRefeicao + " And DATA = '" + data + "'";
        
        try {
            connection = instance.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            cardapio = deserialize(result);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        finally {
            try { statement.close(); } catch (Exception exception) {/* Ignored */}
            try { connection.close(); } catch (Exception exception) {/* Ignored */}
        }
        
        return cardapio;
    }

    @Override
    public void update(Cardapio cardapio) {
        Connection connection = null;
        Statement statement = null;

        String sql =
            String.format(
                "UPDATE iMeal.CARDAPIO SET"
                + "REFEICAO_ID = %d"
                + ", DATA = \'%s\'"
                + ", DESCRICAO = \'%s\'"
                + " WHERE ID = %d"
                , cardapio.refeicaoID
                , cardapio.data
                , cardapio.descricao
                , cardapio.id);
        
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
    public void delete(Integer id) {
        Connection connection = null;
        Statement statement = null;

        String sql = "DELETE iMeal.CARDAPIO WHERE ID = " + id;
        
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

    private Cardapio deserialize(ResultSet result){
        Cardapio cardapio = new Cardapio();
        try {
            while (result.next()) {
                cardapio.id = result.getInt("ID");
                cardapio.refeicaoID = result.getInt("REFEICAO_ID");
                cardapio.data = LocalDate.parse(result.getString("DATA"));
                cardapio.descricao = result.getString("DESCRICAO");
            }
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return cardapio;
    }    
    
    
}
