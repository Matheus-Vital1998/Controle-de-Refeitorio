/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.cefsa.imeal_crud;

import Database.RefeicaoDAO;
import Domain.Refeicao;
import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Matheus
 */
public class ALN_Cardapio_SemanalController implements Initializable {

    public static LocalDate dataEscolhida;
    public static String diaDaSemanaEscolhido = "";
    public static Refeicao refeicaoEscolhida;

    private static LocalDate[] diasDaSemana;
    private static RefeicaoDAO refeicaoDAO;

    @FXML
    Label lblDiaSegunda;
    @FXML
    Label lblDiaTerca;
    @FXML
    Label lblDiaQuarta;
    @FXML
    Label lblDiaQuinta;
    @FXML
    Label lblDiaSexta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refeicaoDAO = new RefeicaoDAO();
        dataEscolhida = LocalDate.now();
        diasDaSemana = getDiasDaSemana(dataEscolhida);

        lblDiaSegunda.setText(diasDaSemana[0].format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        lblDiaTerca.setText(diasDaSemana[1].format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        lblDiaQuarta.setText(diasDaSemana[2].format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        lblDiaQuinta.setText(diasDaSemana[3].format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        lblDiaSexta.setText(diasDaSemana[4].format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
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

    @FXML
    private void OnClick_btnVoltar() throws IOException {
        App.setRoot("ViewLogin");
    }

    @FXML
    private void OnClick_btnCatraca() throws IOException {
        App.setRoot("ViewALN_Catraca");
    }
            
    private void MsgErro(String msg) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Erro");
        alert.setHeaderText(msg);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            alert.close();
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

    private Boolean RefeicaoEscolhida(String nome_refeicao, String dia_da_semana) {
        Boolean valid = true;
        try {
            //Refeição
            if (nome_refeicao == "Janta") {
                refeicaoEscolhida = refeicaoDAO.read("Janta");
            } else if (nome_refeicao == "Lanche reforçado") {
                refeicaoEscolhida = refeicaoDAO.read("Lanche reforçado");
            } else {
                MsgErro("Ocorreu algo de errado. Tente reiniciar o programa.");
                valid = false;
            }
            
            if (refeicaoEscolhida.getHorarioLimiteReserva().isAfter(LocalTime.now())){
                MsgBox("Inválido","Horário limite de reserva da refeição excedido!\nHorário limite: "+
                        refeicaoEscolhida.getHorarioLimiteReserva());
                valid = false;
            }

            //Dia
            if (dia_da_semana == "Segunda") {
                diaDaSemanaEscolhido = "Segunda";
                dataEscolhida = diasDaSemana[0];
            } else if (dia_da_semana == "Terça") {
                diaDaSemanaEscolhido = "Terça";
                dataEscolhida = diasDaSemana[1];
            } else if (dia_da_semana == "Quarta") {
                diaDaSemanaEscolhido = "Quarta";
                dataEscolhida = diasDaSemana[2];
            } else if (dia_da_semana == "Quinta") {
                diaDaSemanaEscolhido = "Quinta";
                dataEscolhida = diasDaSemana[3];
            } else if (dia_da_semana == "Sexta") {
                diaDaSemanaEscolhido = "Sexta";
                dataEscolhida = diasDaSemana[4];
            } else {
                MsgErro("Ocorreu algo de errado. Tente reiniciar o programa.");
                valid = false;
            }
        } catch (Exception erro) {
            valid = false;
        }
        return valid;
    }

    //Vai para a tela de reserva, levando o valor de janta consigo
    @FXML
    private void OnClick_Seg_Janta() throws IOException {
        if(RefeicaoEscolhida("Janta", "Segunda")){
            App.setRoot("ViewALN_Reserva");}
    }

    @FXML
    private void OnClick_Ter_Janta() throws IOException {
        if(RefeicaoEscolhida("Janta", "Terça")){
        App.setRoot("ViewALN_Reserva");}
    }

    @FXML
    private void OnClick_Qua_Janta() throws IOException {
        if(RefeicaoEscolhida("Janta", "Quarta")){
        App.setRoot("ViewALN_Reserva");}
    }

    @FXML
    private void OnClick_Qui_Janta() throws IOException {
        if(RefeicaoEscolhida("Janta", "Quinta")){
        App.setRoot("ViewALN_Reserva");}
    }

    @FXML
    private void OnClick_Sex_Janta() throws IOException {
        if(RefeicaoEscolhida("Janta", "Sexta")){
        App.setRoot("ViewALN_Reserva");}
    }

    @FXML
    private void OnClick_Seg_Lanche_Reforcado() throws IOException {
        if(RefeicaoEscolhida("Lanche reforçado", "Segunda")){
        App.setRoot("ViewALN_Reserva");}
    }

    @FXML
    private void OnClick_Ter_Lanche_Reforcado() throws IOException {
        if(RefeicaoEscolhida("Lanche reforçado", "Terça")){
        App.setRoot("ViewALN_Reserva");}
    }

    @FXML
    private void OnClick_Qua_Lanche_Reforcado() throws IOException {
        if(RefeicaoEscolhida("Lanche reforçado", "Quarta")){
        App.setRoot("ViewALN_Reserva");}
    }

    @FXML
    private void OnClick_Qui_Lanche_Reforcado() throws IOException {
        if(RefeicaoEscolhida("Lanche reforçado", "Quinta")){
        App.setRoot("ViewALN_Reserva");}
    }

    @FXML
    private void OnClick_Sex_Lanche_Reforcado() throws IOException {
        if(RefeicaoEscolhida("Lanche reforçado", "Sexta")){
        App.setRoot("ViewALN_Reserva");}
    }
}
