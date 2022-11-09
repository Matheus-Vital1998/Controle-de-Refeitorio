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
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Matheus
 */
public class ADM_CRUD_CardapioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label lblTitulo;
    @FXML
    private Button btnExcluir;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        String tituloTela = "";
        
        //Se já possui descrição - edição; se não - criação
        if (true){
            tituloTela = "Editar cardápio";
        }else{
            tituloTela = "Criar cardápio";
            btnExcluir.setVisible(false);
        }
        
        tituloTela += ": " + ADM_Cardapio_SemanalController.refeicaoEscolhida + " de " + ADM_Cardapio_SemanalController.diaDaSemanaEscolhido + " - " + ADM_Cardapio_SemanalController.dataEscolhida;
        
        lblTitulo.setText(tituloTela);
    }    
    
    @FXML
    private void OnClick_btnVoltar() throws IOException {
        App.setRoot("ViewADM_Cardapio_Semanal");
    }
    
    @FXML
    private void OnClick_btnSalvar() throws IOException {
        App.setRoot("ViewADM_Inicial");
    }
    @FXML
    private void OnClick_btnCancelar() throws IOException {
        App.setRoot("ViewADM_Inicial");
    }
    @FXML
    private void OnClick_btnExcluir() throws IOException {
        App.setRoot("ViewADM_Inicial");
    }
}
