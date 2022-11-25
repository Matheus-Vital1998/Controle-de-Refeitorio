/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.cefsa.imeal_crud;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Matheus
 */
public class ADM_Relatorios_CardsController implements Initializable {
    
    @FXML private Label lblTopLeft;
    @FXML private Label lblTopRight;
    @FXML private Label lblBopLeft;
    @FXML private Label lblBotRight;
    @FXML private Label lblDia;
    @FXML private CheckBox cbJanta;
    @FXML private CheckBox cbLancheReforcado;
    public static String cardClicado;
    public static Boolean filtroJanta;
    public static Boolean filtroLancheReforcado;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            cardClicado = null;
            
            SetFiltroRefeicao();
            SetData();        
            
            //Falta setar os valores dos cards (very important)
        }catch (Exception erro){
            MsgBox("Erro", "Ocorreu algo de errado. Tente reiniciar o programa.");
        }
    }   
    
    @FXML
    private void OnClick_btnVoltar() throws IOException {
        App.setRoot("ViewADM_Relatorios");
    }
    
    @FXML
    private void SetAndGoToTabela(String cardClicadoVar) throws IOException{
        cardClicado = cardClicadoVar;
        filtroJanta = cbJanta.isSelected();
        filtroLancheReforcado = cbLancheReforcado.isSelected();
        App.setRoot("ViewADM_Relatorios_Tabelas");
    }
    
    @FXML
    private void OnClick_btnTopLeft() throws IOException{
        SetAndGoToTabela("Tabela de todos os alunos que compareceram");
    }
    
    @FXML
    private void OnClick_btnTopRight() throws IOException{
        SetAndGoToTabela("Tabela de todos os alunos que reservaram suas entradas e não compareceram");
    }
    
    @FXML
    private void OnClick_btnBotLeft() throws IOException{
        SetAndGoToTabela("Tabela de todas as tentativas de entrada sem agendamento");
    }
    
    @FXML
    private void OnClick_btnBotRight() throws IOException{
        SetAndGoToTabela("Tabela de todas as tentativas de entrar mais de uma vez na mesma refeição");
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
    
    private void SetFiltroRefeicao(){
        if (filtroJanta != null) {
            cbJanta.setSelected(filtroJanta);
        } else{
            filtroJanta = cbJanta.isSelected();
        }
        if (filtroLancheReforcado != null) {
            cbLancheReforcado.setSelected(filtroLancheReforcado);
        }else{
            filtroLancheReforcado = cbLancheReforcado.isSelected();
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
