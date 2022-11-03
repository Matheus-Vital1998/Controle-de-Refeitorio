package Database;

import java.sql.*;
import Domain.Cardapio;

public class CardapioDAO implements DAO<Cardapio> {

    private ConnectionFactory instance;

    public CardapioDAO() {
        instance = ConnectionFactory.getInstance();
    }

    @Override
    public void create(Cardapio cardapio) throws Exception {
        String sql =
            String.format(
                "INSERT INTO [dbo].[Cardapio] ([RefeicaoID],[Data],[Descricao]) VALUES ("
                + "%refeicaoID"
                + ", \'%data\'"
                + ", \'%descricao\'')"
                , cardapio.refeicaoID
                , cardapio.data
                , cardapio.descricao);
        
        Connection connection = instance.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(sql);
        connection.close();
    }

    @Override
    public Cardapio read(Integer id) throws Exception {
        String sql = "SELECT * FROM [dbo].[Cardapio] WHERE [ID] = " + id;

        Connection connection = instance.getConnection();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        connection.close();
        
        return deserialize(result);
    }

    @Override
    public void update(Cardapio cardapio) throws Exception {
        String sql =
            String.format(
                "UPDATE [dbo].[Cardapio] SET"
                + "[RefeicaoID] = %refeicaoID"
                + "[Data] = \'%data\'"
                + "[Descricao] = \'%descricao\'"
                + "WHERE [ID] = %id"
                , cardapio.refeicaoID
                , cardapio.data
                , cardapio.descricao
                , cardapio.id);
        
        Connection connection = instance.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        connection.close();
    }

    @Override
    public void delete(Integer id) throws Exception {
        String sql = "DELETE [dbo].[Cardapio] WHERE [ID] = " + id;
        
        Connection connection = instance.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(sql);
        connection.close();        
    }

    private Cardapio deserialize(ResultSet result){
        return null;
    }    
}
