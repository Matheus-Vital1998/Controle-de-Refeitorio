package Database;

import Domain.Cardapio;
import Domain.HistoricoConsumo;
import Domain.Usuario;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class HistoricoConsumoDAO implements DAO<HistoricoConsumo> {
    private ConnectionFactory connectionFactory;

    public HistoricoConsumoDAO() {
        connectionFactory = ConnectionFactory.getInstance();
    }

    @Override
    public void create(HistoricoConsumo historicoConsumo) throws Exception {
        String sql = 
            String.format(
                "INSERT INTO HISTORICO_CONSUMO (USUARIO_ID, CARDAPIO_ID, HORARIO_CHEGADA, ENTRADA_AUTORIZADA, MOTIVO) VALUES ("
                + "%d"
                + ", %d"
                + ", '%s'"
                + ", %d"
                + ", '%s'"
                , historicoConsumo.getUsuario().getId()
                , historicoConsumo.getCardapio().getId()
                , historicoConsumo.getHorarioChegada()
                , historicoConsumo.getEntradaAutorizada()
                , historicoConsumo.getMotivo());
        
        Connection connection = connectionFactory.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(sql);
        connection.close();        
    }

    @Override
    public HistoricoConsumo read(Integer id) throws Exception {
        String sql = 
            "SELECT * FROM HISTORICO_CONSUMO WHERE ID = " + id;

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
                "UPDATE HISTORICO_CONSUMO SET "
                + "USUARIO_ID = %d"
                + ", CARDAPIO_ID = %d"
                + ", HORARIO_CHEGADA = '%s'"
                + ", ENTRADA_AUTORIZADA = %d"
                + ", MOTIVO = '%s'"
                , historicoConsumo.getUsuario().getId()
                , historicoConsumo.getCardapio().getId()
                , historicoConsumo.getHorarioChegada()
                , historicoConsumo.getEntradaAutorizada()
                , historicoConsumo.getMotivo());
        
        Connection connection = connectionFactory.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(sql);
        connection.close();
    }

    @Override
    public void delete(Integer id) throws Exception {
        String sql = "DELETE FROM HISTORICO_CONSUMO WHERE ID = " + id;

        Connection connection = connectionFactory.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(sql);
        connection.close();
    }

    private HistoricoConsumo deserialize(ResultSet result){
        HistoricoConsumo historicoConsumo = new HistoricoConsumo();
        try {
            while (result.next()) {
                historicoConsumo.setId(result.getInt("ID"));
                
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                Usuario usuarioAux = usuarioDAO.read(result.getInt("USUARIO_ID"));
                historicoConsumo.setUsuario(usuarioAux);
                
                CardapioDAO cardapioDAO = new CardapioDAO();
                Cardapio cardapioAux = cardapioDAO.read(result.getInt("CARDAPIO_ID"));
                historicoConsumo.setCardapio(cardapioAux);
                
                historicoConsumo.setHorarioChegada(LocalTime.parse(result.getString("HORARIO_CHEGADA"), DateTimeFormatter.ofPattern("HH:mm:ss.S")));
                
                historicoConsumo.setEntradaAutorizada(result.getBoolean("ENTRADA_AUTORIZADA"));
                
                historicoConsumo.setMotivo(result.getString("MOTIVO"));
            }
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return historicoConsumo;
    }
}
