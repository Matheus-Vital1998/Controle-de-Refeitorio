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
public class ADM_Cardapio_SemanalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    private void OnClick_btnVoltar() throws IOException {
        App.setRoot("ViewADM_Inicial");
    }
    
    //Vai para a tela de reserva, levando o valor de janta consigo
    
    @FXML
    private void OnClick_Seg_Janta() throws IOException {
        
        App.setRoot("ViewADM_CRUD_Cardapio");
    }
    @FXML
    private void OnClick_Ter_Janta() throws IOException {
        
        App.setRoot("ViewADM_CRUD_Cardapio");
    }
    @FXML
    private void OnClick_Qua_Janta() throws IOException {
        
        App.setRoot("ViewADM_CRUD_Cardapio");
    }
    @FXML
    private void OnClick_Qui_Janta() throws IOException {
        
        App.setRoot("ViewADM_CRUD_Cardapio");
    }
    @FXML
    private void OnClick_Sex_Janta() throws IOException {
        
        App.setRoot("ViewADM_CRUD_Cardapio");
    }
    @FXML
    private void OnClick_Seg_Lanche_Reforcado() throws IOException {
        
        App.setRoot("ViewADM_CRUD_Cardapio");
    }
    @FXML
    private void OnClick_Ter_Lanche_Reforcado() throws IOException {
        
        App.setRoot("ViewADM_CRUD_Cardapio");
    }
    @FXML
    private void OnClick_Qua_Lanche_Reforcado() throws IOException {
        
        App.setRoot("ViewADM_CRUD_Cardapio");
    }
    @FXML
    private void OnClick_Qui_Lanche_Reforcado() throws IOException {
        
        App.setRoot("ViewADM_CRUD_Cardapio");
    }
    @FXML
    private void OnClick_Sex_Lanche_Reforcado() throws IOException {
        
        App.setRoot("ViewADM_CRUD_Cardapio");
    }
}
