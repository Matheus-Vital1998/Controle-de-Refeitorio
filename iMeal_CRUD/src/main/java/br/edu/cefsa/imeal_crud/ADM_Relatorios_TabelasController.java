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
public class ADM_Relatorios_TabelasController implements Initializable {
    
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
        App.setRoot("ViewADM_Relatorios_Cards");
    }
    
    @FXML
    private void OnClick_btnJanta() throws IOException{
        
    }
    
    @FXML
    private void OnClick_btnLanche_Reforcado() throws IOException{
        
    }
}
