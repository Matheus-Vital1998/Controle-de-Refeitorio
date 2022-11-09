package Database;

import Domain.HistoricoConsumo;
import java.sql.*;

public class HistoricoConsumoDAO implements DAO<HistoricoConsumo> {
    private ConnectionFactory connectionFactory;

    public HistoricoConsumoDAO() {
        connectionFactory = ConnectionFactory.getInstance();
    }

    @Override
    public void create(HistoricoConsumo historicoConsumo) throws Exception {
        String sql = 
            String.format(
                "INSERT INTO [dbo].[HistoricoConsumo] ([UsuarioID],[CardapioID],[HorarioChegada],[EntradaAutorizada],[Motivo]) VALUES ("
                + "%usuarioID"
                + ", %cardapioID"
                + ", \'%horarioChegada\'"
                + ", %entradaAutorizada"
                + ", \'%motivo\'"
                , historicoConsumo.usuarioID
                , historicoConsumo.cardapioID
                , historicoConsumo.horarioChegada
                , historicoConsumo.entradaAutorizada
                , historicoConsumo.motivo);
        
        Connection connection = connectionFactory.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(sql);
        connection.close();        
    }

    @Override
    public HistoricoConsumo read(Integer id) throws Exception {
        String sql = 
            "SELECT * FROM [dbo].[HistoricoConsumo] WHERE [ID] = " + id;

        Connection connection = connectionFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        connection.close();
        
        return deserialize(result);
    }

    @Override
    public void update(HistoricoConsumo historicoConsumo) throws Exception {
        String sql = 
            String.format(
                "UPDATE [dbo].[HistoricoConsumo] SET"
                + "[UsuarioID] = %usuarioID"
                + ", [CardapioID] = %cardapioID"
                + ", [HorarioChegada] = \'%horarioChegada\'"
                + ", [EntradaAutorizada] = %entradaAutorizada"
                + ", [Motivo] = \'%motivo\'"
                , historicoConsumo.usuarioID
                , historicoConsumo.cardapioID
                , historicoConsumo.horarioChegada
                , historicoConsumo.entradaAutorizada
                , historicoConsumo.motivo);
        
        Connection connection = connectionFactory.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(sql);
        connection.close();
    }

    @Override
    public void delete(Integer id) throws Exception {
        String sql = "DELETE [dbo].[HistoricoConsumo] WHERE [ID] = " + id;

        Connection connection = connectionFactory.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(sql);
        connection.close();
    }

    private HistoricoConsumo deserialize(ResultSet result){
        return null;
    }
}
