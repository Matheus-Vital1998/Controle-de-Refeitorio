/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.cefsa.imeal_crud;

import Domain.Cardapio;
import Domain.HistoricoConsumo;
import Domain.HistoricoConsumoLimitado;
import Domain.Refeicao;
import Domain.TipoUsuario;
import Domain.Usuario;
import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Matheus
 */
public class ADM_Relatorios_TabelasController implements Initializable {
    
    @FXML private Label lblDia;
    @FXML private Label lblTitulo;
    @FXML private CheckBox cbJanta;
    @FXML private CheckBox cbLancheReforcado;
    @FXML private TableView<HistoricoConsumoLimitado> tableViewInfos;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            //att filtros checkbox
            cbJanta.setSelected(ADM_Relatorios_CardsController.filtroJanta);
            cbLancheReforcado.setSelected(ADM_Relatorios_CardsController.filtroLancheReforcado);
            lblTitulo.setText(ADM_Relatorios_CardsController.cardClicado);
            SetData();         
            SetTabela();
        }catch (Exception erro){
            MsgBox("Erro", "Ocorreu algo de errado. Tente reiniciar o programa.");
        }
    }  
    
    @FXML
    private void OnClick_btnVoltar() throws IOException {
        App.setRoot("ViewADM_Relatorios_Cards");
    }
    
    private void SetTabela(){
        List<HistoricoConsumoLimitado> dados = new LinkedList<HistoricoConsumoLimitado>();
        dados.add(new HistoricoConsumoLimitado(
                "081200024", 
                "Matheus Vinicius Miranda Brito", 
                "Janta", 
                LocalDate.MAX, 
                "Feijoada", 
                LocalTime.MIDNIGHT, 
                Boolean.FALSE, 
                "Tentou entrar mais de uma vez na mesma refeição"));
        
               
        List<TableColumn> colunas = new LinkedList<TableColumn>();
        Integer i = 0;
        
        colunas.add(new TableColumn<HistoricoConsumoLimitado, String>("Nome"));
        colunas.get(i++).setCellValueFactory(new PropertyValueFactory<HistoricoConsumoLimitado, String>("nome"));
        
        colunas.add(new TableColumn<HistoricoConsumoLimitado, String>("RA"));
        colunas.get(i++).setCellValueFactory(new PropertyValueFactory<HistoricoConsumoLimitado, String>("ra"));
        
        colunas.add(new TableColumn<HistoricoConsumoLimitado, String>("Refeicao"));
        colunas.get(i++).setCellValueFactory(new PropertyValueFactory<HistoricoConsumoLimitado, String>("refeicao"));
        
        colunas.add(new TableColumn<HistoricoConsumoLimitado, LocalDate>("Data"));
        colunas.get(i++).setCellValueFactory(new PropertyValueFactory<HistoricoConsumoLimitado, LocalDate>("data"));
        
        colunas.add(new TableColumn<Cardapio, String>("Cardapio"));
        colunas.get(i++).setCellValueFactory(new PropertyValueFactory<Cardapio, String>("descricao"));
        
        colunas.add(new TableColumn<HistoricoConsumoLimitado, LocalTime>("Horário de chegada"));
        colunas.get(i++).setCellValueFactory(new PropertyValueFactory<HistoricoConsumoLimitado, LocalTime>("horarioChegada"));
        
        colunas.add(new TableColumn<HistoricoConsumoLimitado, Boolean>("Entrada autorizada?"));
        colunas.get(i++).setCellValueFactory(new PropertyValueFactory<HistoricoConsumoLimitado, Boolean>("entradaAutorizada"));
        
        colunas.add(new TableColumn<HistoricoConsumoLimitado, String>("Motivo"));
        colunas.get(i++).setCellValueFactory(new PropertyValueFactory<HistoricoConsumoLimitado, String>("motivo"));
        
        for (TableColumn coluna : colunas){
            tableViewInfos.getColumns().add(coluna);
        }
        
        tableViewInfos.getItems().setAll(dados);
        
        tableViewInfos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
    
    private String FormataDia(LocalDate date){
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    
    private LocalDate[] getDiasDaSemana(LocalDate dataAtual) {
        //0 - Seg, 1 - Ter, ..., 6 - Dom
        Integer diaDaSemanaAtual = DayOfWeek.from(dataAtual).getValue() - 1;

        LocalDate[] diasDaSemana = new LocalDate[7];

        if (diaDaSemanaAtual < 5) { //normal
            diasDaSemana[0] = dataAtual.minusDays(diaDaSemanaAtual);

        } else { //sábado ou domingo, vai pra próxima semana
            diasDaSemana[0] = dataAtual.minusDays(diaDaSemanaAtual + 7);
            diaDaSemanaAtual = DayOfWeek.from(dataAtual).getValue() - 1;
        }
        
        for(int i = 1; i<7; i++){     
            diasDaSemana[i] = diasDaSemana[0].plusDays(i);
        }

        return diasDaSemana;
    }
    
    private String FormataSemana(LocalDate date){
        LocalDate[] diasDaSemana = getDiasDaSemana(date); 
        return diasDaSemana[0].format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + " - "
                + diasDaSemana[4].format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    
    private void SetData(){  
        if (ADM_RelatoriosController.tipoRelatorio == "Diario"){
            lblDia.setText(FormataDia(ADM_RelatoriosController.dataEscolhida[0]));
        } else if(ADM_RelatoriosController.tipoRelatorio == "Semanal"){
            lblDia.setText(FormataSemana(ADM_RelatoriosController.dataEscolhida[0]));
        }else{
            MsgBox("Erro", "Tipo de relatório inválido. Tente reiniciar o programa.");
        }
    }
    
    private Integer MsgBox(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(msg);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            alert.close();
            return 0;
        } else {
            return 1;
        }
    }
}
