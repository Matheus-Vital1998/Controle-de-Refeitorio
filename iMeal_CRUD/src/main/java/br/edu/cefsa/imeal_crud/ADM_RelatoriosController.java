/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.cefsa.imeal_crud;

import static br.edu.cefsa.imeal_crud.ADM_Cardapio_SemanalController.dataEscolhida;
import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
/**
 * FXML Controller class
 *
 * @author Matheus
 */
public class ADM_RelatoriosController implements Initializable {
    
    @FXML private Label lblTitulo;
    @FXML private DatePicker txtData;
    @FXML private RadioButton rbtRelDiario;
    public LocalDate dataEscolhida;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtData.setValue(LocalDate.now());
        AtualizaTitulo();
    }    
    
    
    @FXML
    private void OnClick_btnVoltar() throws IOException {
        App.setRoot("ViewADM_Inicial");
    }
    
    @FXML
    private void OnClick_btnGerarRelatorio() throws IOException {
        App.setRoot("ViewADM_Relatorios_Cards");
    }
    
    @FXML
    private void OnChange_txtData(){
        AtualizaTitulo();
    }
    
    @FXML
    private void OnChange_rbtRel(){
        AtualizaTitulo();
    }
    
    private void AtualizaTitulo(){
        dataEscolhida = txtData.getValue();
        
        if (rbtRelDiario.isSelected()){
            lblTitulo.setText("Relatório do dia " + FormataDia(dataEscolhida));
        } else{
            lblTitulo.setText("Relatório da semana dos dias " + FormataSemana(dataEscolhida));
        }
    }
    
    private String FormataDia(LocalDate date){
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    
    private Integer[] getDiasDaSemana(LocalDate dataAtual) {
        //0 - Seg, 1 - Ter, ..., 6 - Dom
        Integer diaDaSemanaAtual = DayOfWeek.from(dataAtual).getValue() - 1;

        Integer[] diasDaSemana = new Integer[7];

        if (diaDaSemanaAtual < 5) { //normal
            diasDaSemana[0] = dataAtual.getDayOfMonth() - diaDaSemanaAtual;

        } else { //sábado ou domingo, vai pra próxima semana
            diasDaSemana[0] = dataAtual.getDayOfMonth() - diaDaSemanaAtual + 7;
            diaDaSemanaAtual = DayOfWeek.from(dataAtual).getValue() - 1;
        }

        diasDaSemana[1] = diasDaSemana[0] + 1;
        diasDaSemana[2] = diasDaSemana[0] + 2;
        diasDaSemana[3] = diasDaSemana[0] + 3;
        diasDaSemana[4] = diasDaSemana[0] + 4;
        diasDaSemana[5] = diasDaSemana[0] + 5;
        diasDaSemana[6] = diasDaSemana[0] + 6;

        return diasDaSemana;
    }
    
    private String FormataSemana(LocalDate date){
        Integer[] diasDaSemana = getDiasDaSemana(date); 
        return String.format("%02d", diasDaSemana[0]) + "/"
                + String.format("%02d", date.getMonthValue()) + "/"
                + date.getYear() 
                + " - "
                + String.format("%02d", diasDaSemana[4]) + "/"
                + String.format("%02d", date.getMonthValue()) + "/"
                + date.getYear();
    }
}
