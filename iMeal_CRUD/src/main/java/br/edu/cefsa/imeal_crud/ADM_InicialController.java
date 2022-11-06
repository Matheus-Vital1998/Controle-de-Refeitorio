/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.cefsa.imeal_crud;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Matheus
 */
public class ADM_InicialController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void OnClick_btnLogoff() throws IOException {
        App.setRoot("ViewLogin");
    }
    
    @FXML
    private void OnClick_btnCardapioSemanal() throws IOException {
        App.setRoot("ViewADM_Cardapio_Semanal");
    }
    @FXML
    private void OnClick_btnRelatorios() throws IOException {
        App.setRoot("ViewADM_Relatorios");
    }
    @FXML
    private void OnClick_btnAlterarReservaAlunos() throws IOException {
        App.setRoot("ViewADM_Alterar_Reserva");
    }
    @FXML
    private void OnClick_btnCriarContaAdmin() throws IOException {
        App.setRoot("ViewADM_Criar_Conta_Admin");
    }
}
