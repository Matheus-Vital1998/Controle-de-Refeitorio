/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.cefsa.imeal_crud;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Matheus
 */
public class ADM_Cardapio_SemanalController implements Initializable {
    
    public static LocalDate dataEscolhida;
    public static String diaDaSemanaEscolhido = "";    
    public static String refeicaoEscolhida = "";
    private static Integer[] diasDaSemana;
    
    @FXML Label lblDiaSegunda;
    @FXML Label lblDiaTerca;
    @FXML Label lblDiaQuarta;
    @FXML Label lblDiaQuinta;
    @FXML Label lblDiaSexta;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        dataEscolhida = LocalDate.now();
        diasDaSemana = getDiasDaSemana(dataEscolhida);      
        
        lblDiaSegunda.setText(String.format("%02d", diasDaSemana[0]) + "/" 
                + String.format("%02d", dataEscolhida.getMonthValue()) + "/" 
                + dataEscolhida.getYear());
        lblDiaTerca.setText(String.format("%02d", diasDaSemana[1])+ "/" 
                + String.format("%02d", dataEscolhida.getMonthValue()) + "/" 
                + dataEscolhida.getYear());
        lblDiaQuarta.setText(String.format("%02d", diasDaSemana[2])+ "/" 
                + String.format("%02d", dataEscolhida.getMonthValue()) + "/" 
                + dataEscolhida.getYear());
        lblDiaQuinta.setText(String.format("%02d", diasDaSemana[3])+ "/" 
                + String.format("%02d", dataEscolhida.getMonthValue()) + "/" 
                + dataEscolhida.getYear());
        lblDiaSexta.setText(String.format("%02d", diasDaSemana[4])+ "/" 
                + String.format("%02d", dataEscolhida.getMonthValue()) + "/" 
                + dataEscolhida.getYear());       
    }
    
    private Integer[] getDiasDaSemana(LocalDate dataAtual){
        //0 - Seg, 1 - Ter, ..., 6 - Dom
        Integer diaDaSemanaAtual = DayOfWeek.from(dataAtual).getValue() - 1;
        
        Integer[] diasDaSemana = new Integer[7];
        
        diasDaSemana[0] = dataAtual.getDayOfMonth() - diaDaSemanaAtual;
        diasDaSemana[1] = diasDaSemana[0] + 1;
        diasDaSemana[2] = diasDaSemana[0] + 2;
        diasDaSemana[3] = diasDaSemana[0] + 3;
        diasDaSemana[4] = diasDaSemana[0] + 4;
        diasDaSemana[5] = diasDaSemana[0] + 5;
        diasDaSemana[6] = diasDaSemana[0] + 6;
        
        return diasDaSemana;
    }

    @FXML
    private void OnClick_btnVoltar() throws IOException {
        App.setRoot("ViewADM_Inicial");
    }

    //Vai para a tela de reserva, levando o valor de janta consigo
    @FXML
    private void OnClick_Seg_Janta() throws IOException {
        diaDaSemanaEscolhido = "Segunda";
        dataEscolhida = dataEscolhida.withDayOfMonth(diasDaSemana[0]);
        refeicaoEscolhida = "Janta";
        App.setRoot("ViewADM_CRUD_Cardapio");
    }

    @FXML
    private void OnClick_Ter_Janta() throws IOException {
        diaDaSemanaEscolhido = "Terça";
        dataEscolhida = dataEscolhida.withDayOfMonth(diasDaSemana[1]);
        refeicaoEscolhida = "Janta";
        App.setRoot("ViewADM_CRUD_Cardapio");
    }

    @FXML
    private void OnClick_Qua_Janta() throws IOException {
        diaDaSemanaEscolhido = "Quarta";
        dataEscolhida = dataEscolhida.withDayOfMonth(diasDaSemana[2]);
        refeicaoEscolhida = "Janta";
        App.setRoot("ViewADM_CRUD_Cardapio");
    }

    @FXML
    private void OnClick_Qui_Janta() throws IOException {
        diaDaSemanaEscolhido = "Quinta";
        dataEscolhida = dataEscolhida.withDayOfMonth(diasDaSemana[3]);
        refeicaoEscolhida = "Janta";
        App.setRoot("ViewADM_CRUD_Cardapio");
    }

    @FXML
    private void OnClick_Sex_Janta() throws IOException {
        diaDaSemanaEscolhido = "Sexta";
        dataEscolhida = dataEscolhida.withDayOfMonth(diasDaSemana[4]);
        refeicaoEscolhida = "Janta";
        App.setRoot("ViewADM_CRUD_Cardapio");
    }

    @FXML
    private void OnClick_Seg_Lanche_Reforcado() throws IOException {
        diaDaSemanaEscolhido = "Segunda";
        dataEscolhida = dataEscolhida.withDayOfMonth(diasDaSemana[0]);
        refeicaoEscolhida = "Lanche reforçado";
        App.setRoot("ViewADM_CRUD_Cardapio");
    }

    @FXML
    private void OnClick_Ter_Lanche_Reforcado() throws IOException {
        diaDaSemanaEscolhido = "Terça";
        dataEscolhida = dataEscolhida.withDayOfMonth(diasDaSemana[1]);
        refeicaoEscolhida = "Lanche reforçado";
        App.setRoot("ViewADM_CRUD_Cardapio");
    }

    @FXML
    private void OnClick_Qua_Lanche_Reforcado() throws IOException {
        diaDaSemanaEscolhido = "Quarta";
        dataEscolhida = dataEscolhida.withDayOfMonth(diasDaSemana[2]);
        refeicaoEscolhida = "Lanche reforçado";
        App.setRoot("ViewADM_CRUD_Cardapio");
    }

    @FXML
    private void OnClick_Qui_Lanche_Reforcado() throws IOException {
        diaDaSemanaEscolhido = "Quinta";
        dataEscolhida = dataEscolhida.withDayOfMonth(diasDaSemana[3]);
        refeicaoEscolhida = "Lanche reforçado";
        App.setRoot("ViewADM_CRUD_Cardapio");
    }

    @FXML
    private void OnClick_Sex_Lanche_Reforcado() throws IOException {
        diaDaSemanaEscolhido = "Sexta";
        dataEscolhida = dataEscolhida.withDayOfMonth(diasDaSemana[4]);
        refeicaoEscolhida = "Lanche reforçado";
        App.setRoot("ViewADM_CRUD_Cardapio");
    }
}
