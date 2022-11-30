/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.cefsa.imeal_crud;

import Database.UsuarioDAO;
import Domain.TipoUsuario;
import Domain.Usuario;

import java.io.IOException;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Matheus
 */
public class LoginController implements Initializable {
    
    public static Usuario usuarioAtual;
    @FXML private TextField txtLogin;
    @FXML private TextField txtSenha;
    @FXML private Label lblValidacao;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuarioAtual = new Usuario();
    }    
    
    @FXML
    private void OnClick_btnEntrar() throws IOException {
        lblValidacao.setText("");
        
        //Validar Login e Senha
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioAtual = usuarioDAO.authenticate(txtLogin.getText(), txtSenha.getText());
                                
        if(usuarioAtual == null){
            txtLogin.setText("");
            txtSenha.setText("");
            lblValidacao.setText("Usuário e/ou senha inválidos.");
            return;
        }
        
        //Verificar se é usuário ADMIN ou ALUNO        
        if (usuarioAtual.getTipo() == TipoUsuario.ADMIN){
            App.setRoot("ViewADM_Inicial");
        } else if (usuarioAtual.getTipo() == TipoUsuario.ALUNO){
            App.setRoot("ViewALN_Cardapio_Semanal");
        } else{
            lblValidacao.setText("Algo errado ocorreu ... reinicie o programa.");
        }    
    }
}
