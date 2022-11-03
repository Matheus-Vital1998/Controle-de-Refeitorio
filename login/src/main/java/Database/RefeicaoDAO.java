package Database;

import java.sql.*;
import Domain.Refeicao;

public class RefeicaoDAO implements DAO<Refeicao>{
    private ConnectionFactory instance;

    public RefeicaoDAO() {
        instance = ConnectionFactory.getInstance();
    }

    @Override
    public void create(Refeicao refeicao) throws Exception {
        String sql = 
            String.format(
                "INSERT INTO [dbo].[Refeicao] ([RA],[Nome],[Login],[Senha],[Tipo]) VALUES ("
                + "\'%nome\'"
                + ", \'%horarioInicio\' ,"
                + ", \'%horarioFim\' ,"
                + ", \'%horarioLimiteReserva\');"
                , refeicao.nome
                , refeicao.horarioInicio
                , refeicao.horarioFim
                , refeicao.horarioLimiteReserva);
            
        Connection connection = instance.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(sql);
        connection.close();
    }

    @Override
    public Refeicao read(Integer id) throws Exception {
        String sql =
            "SELECT * FROM [dbo].[Refeicao] WHERE [ID] = " + id;
            
        Connection connection = instance.getConnection();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        connection.close();

        return deserialize(result);
    }

    @Override
    public void update(Refeicao refeicao) throws Exception {
        String sql = 
            String.format(
                "UPDATE [dbo].[Refeicao] SET"
                + "[Nome] = \'%Nome\'"
                + ", [HorarioInicio] = \'%horarioInicio\' ,"
                + ", [HorarioFim] = \'%horarioFim\' ,"
                + ", [HorarioLimiteReserva] = \'%horarioLimiteReserva\'"
                + "WHERE [ID] = %id"
                , refeicao.nome
                , refeicao.horarioInicio
                , refeicao.horarioFim
                , refeicao.horarioLimiteReserva
                , refeicao.id);
            
        Connection connection = instance.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(sql);
        connection.close();
        
    }

    @Override
    public void delete(Integer id) throws Exception {
        String sql = 
            "DELETE [dbo].[Refeicao] WHERE [ID] = " + id;

        Connection connection = instance.getConnection();
        Statement statement = connection.createStatement();
        statement.executeQuery(sql);
        connection.close();      
    }    

    private Refeicao deserialize(ResultSet result) {
        return null;
    }
}
