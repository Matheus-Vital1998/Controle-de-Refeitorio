/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.cefsa.imeal_crud;

import java.io.IOException;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Matheus
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML private TextField txtLogin;
    @FXML private TextField txtSenha;
    
    @FXML
    private void OnClick_btnEntrar() throws IOException {
        //Validar Login e Senha
        
        //Verificar se é usuário ADMIN ou ALUNO        
        if (true){
            App.setRoot("ViewADM_Inicial");
        } else{
            App.setRoot("ViewALN_Cardapio_Semanal");
        }        
    }
}
