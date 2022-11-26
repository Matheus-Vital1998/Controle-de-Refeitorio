package Database;

import Domain.Cardapio;
import Domain.HistoricoConsumo;
import Domain.HistoricoConsumoLimitado;
import Domain.Usuario;
import java.sql.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

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
    
    public List<HistoricoConsumoLimitado> read(Usuario usuario, Cardapio cardapio) throws Exception {
        String sql = 
            "SELECT * FROM HISTORICO_CONSUMO WHERE USUARIO_ID = " + usuario.getId() 
                + " And CARDAPIO_ID = " + cardapio.getId();

        Connection connection = connectionFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        connection.close();
        
        return limitedDeserialize(result);
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
    
    private List<HistoricoConsumoLimitado> limitedDeserialize(ResultSet result){
        
        List<HistoricoConsumoLimitado> historicoConsumo = new LinkedList<HistoricoConsumoLimitado>();
        try {
            Integer i = 0;
            while (result.next()) {
                
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                Usuario usuarioAux = usuarioDAO.read(result.getInt("USUARIO_ID"));
                historicoConsumo.get(i).setNome(usuarioAux.getNome());
                historicoConsumo.get(i).setRa(usuarioAux.getRa());
                
                CardapioDAO cardapioDAO = new CardapioDAO();
                Cardapio cardapioAux = cardapioDAO.read(result.getInt("CARDAPIO_ID"));
                historicoConsumo.get(i).setRefeicao(cardapioAux.getRefeicao().getNome());
                historicoConsumo.get(i).setData(cardapioAux.getData());
                historicoConsumo.get(i).setDescricao(cardapioAux.getDescricao());
                
                historicoConsumo.get(i).setHorarioChegada(LocalTime.parse(result.getString("HORARIO_CHEGADA"), DateTimeFormatter.ofPattern("HH:mm:ss.S")));                
                historicoConsumo.get(i).setEntradaAutorizada(result.getBoolean("ENTRADA_AUTORIZADA"));                
                historicoConsumo.get(i).setMotivo(result.getString("MOTIVO"));
                
                i++;
            }
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return historicoConsumo;
    }
    
    public List<HistoricoConsumoLimitado> readVw_AlunosCompareceram(Integer id) throws Exception {
        String sql = 
            "SELECT * FROM vw_Compareceram";

        Connection connection = connectionFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        connection.close();
        
        return limitedDeserialize(result);
    }
}
