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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    private void OnClick_btnVoltar() throws IOException {
        App.setRoot("ViewADM_Relatorios");
    }
    
    @FXML
    private void OnClick_btnTopLeft() throws IOException{
        
        App.setRoot("ViewADM_Relatorios_Tabelas");
    }
    
    @FXML
    private void OnClick_btnTopRight() throws IOException{
        
        App.setRoot("ViewADM_Relatorios_Tabelas");
    }
    
    @FXML
    private void OnClick_btnBotLeft() throws IOException{
        
        App.setRoot("ViewADM_Relatorios_Tabelas");
    }
    
    @FXML
    private void OnClick_btnBotRight() throws IOException{
        
        App.setRoot("ViewADM_Relatorios_Tabelas");
    }
    
    @FXML
    private void OnClick_btnJanta() throws IOException{
        
        App.setRoot("ViewADM_Relatorios_Tabelas");
    }
    
    @FXML
    private void OnClick_btnLanche_Reforcado() throws IOException{
        
        App.setRoot("ViewADM_Relatorios_Tabelas");
    }
}
