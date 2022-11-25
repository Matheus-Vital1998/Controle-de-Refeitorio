/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.cefsa.imeal_crud;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
    public static LocalDate[] dataEscolhida;
    public static String tipoRelatorio;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dataEscolhida = new LocalDate[2];
        tipoRelatorio = "Diario";
        txtData.setValue(LocalDate.now());
        AtualizaTitulo();
    }    
    
    
    @FXML
    private void OnClick_btnVoltar() throws IOException {
        App.setRoot("ViewADM_Inicial");
    }
    
    @FXML
    private void OnClick_btnGerarRelatorio() throws IOException {
        if(!Atribui_next_vars()){
            return;
        }
        App.setRoot("ViewADM_Relatorios_Cards");
    }
    
    private Boolean Atribui_next_vars(){
        Boolean valid = true;
        try{
            AtualizaTitulo();
            tipoRelatorio = rbtRelDiario.isSelected() ? "Diario" : "Semanal";
            
            if (tipoRelatorio == null || tipoRelatorio == ""){
                MsgBox("Erro", "Tipo de relatório inválido. Tente reiniciar o programa.");
                valid = false;
            }
            
            if(dataEscolhida[0] == null || 
              (dataEscolhida[1] == null && tipoRelatorio == "Semanal")){
                MsgBox("Erro", "Período selecionado inválido. Tente reiniciar o programa.");
                valid = false;
            }
            
        } catch (Exception erro){
            MsgBox("Erro", "Ocorreu algo de errado. Tente reiniciar o programa.");
            valid = false;
        }finally{
            return valid;
        }
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
        LocalDate diaEscolhido = txtData.getValue();
        
        if (rbtRelDiario.isSelected()){
            dataEscolhida[0] = diaEscolhido;
            lblTitulo.setText("Relatório do dia " + FormataDia(diaEscolhido));
        } else{
            dataEscolhida = ComecoEFimSemana(diaEscolhido);
            lblTitulo.setText("Relatório da semana dos dias " + FormataSemana(diaEscolhido));
        }
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
    
    private LocalDate[] ComecoEFimSemana(LocalDate date){
        LocalDate[] diasDaSemana = getDiasDaSemana(date); 
        LocalDate[] comeco_e_fim = {diasDaSemana[0], diasDaSemana[4]};
        return comeco_e_fim;
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
