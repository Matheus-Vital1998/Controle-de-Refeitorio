/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.cefsa.imeal_crud;

import Database.CardapioDAO;
import Domain.Cardapio;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Matheus
 */
public class ADM_CRUD_CardapioController implements Initializable {

    private static boolean descricaoCriada;
    private static Cardapio cardapioEscolhido;
    private CardapioDAO cardapioDAO = new CardapioDAO();
    @FXML
    private Label lblTitulo;
    @FXML
    private TextArea txtDescricao;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnCancelar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cardapioEscolhido = new Cardapio();
        cardapioEscolhido = cardapioDAO.read(ADM_Cardapio_SemanalController.dataEscolhida,
                ADM_Cardapio_SemanalController.refeicaoEscolhida.id);
              
        
        //Se descrição vazia - criação; se não - edição
        String tituloTela = "";
        descricaoCriada = (cardapioEscolhido.descricao == null || cardapioEscolhido.descricao.matches(""));
        if (descricaoCriada) {
            tituloTela = "Criar cardápio";
            btnExcluir.setVisible(false);
        } else {
            tituloTela = "Editar cardápio";
            txtDescricao.setText(cardapioEscolhido.descricao);
        }
        
        tituloTela += ": " + ADM_Cardapio_SemanalController.refeicaoEscolhida.nome + " de "
                + ADM_Cardapio_SemanalController.diaDaSemanaEscolhido + " - "
                + ADM_Cardapio_SemanalController.dataEscolhida.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        lblTitulo.setText(tituloTela);
    }

    @FXML
    private void OnClick_btnVoltar() throws IOException {
        App.setRoot("ViewADM_Cardapio_Semanal");
    }

    @FXML
    private void OnClick_btnSalvar() throws IOException {
        try {
            String descricaoNova = txtDescricao.getText().trim();
            if (descricaoNova.matches("")) {
                txtDescricao.setText("");
                MsgBox("Inválido", "Descrição precisa conter conteúdo!");
                return;
            }
            
            if (cardapioEscolhido.descricao != null && descricaoNova.matches(cardapioEscolhido.descricao)){
                MsgBox("Inválido", "Nenhuma mudança feita ...");
                return;
            }

            int resp = MsgBox("Confirmação", "Salvar as alterações feitas?");
            if (resp == 0) { //Se foi OK
                cardapioEscolhido.descricao = descricaoNova;
                //Se não achou, preenche com o escolhido
                if (cardapioEscolhido.id == null){
                    cardapioEscolhido.data = ADM_Cardapio_SemanalController.dataEscolhida;
                    cardapioEscolhido.refeicaoID = ADM_Cardapio_SemanalController.refeicaoEscolhida.id;
                }
        
                if (descricaoCriada) {
                    cardapioDAO.create(cardapioEscolhido);
                } else {
                    cardapioDAO.update(cardapioEscolhido);
                }
                App.setRoot("ViewADM_Cardapio_Semanal");
                MsgBox("Sucesso", "Salvamento confirmado.");
            }
        } catch (Exception erro) {
            MsgBox("Erro", "Ocorreu algo de errado. Tente reiniciar o programa.");
        }
    }

    @FXML
    private void OnClick_btnCancelar() throws IOException {
        try {
            if (txtDescricao.getText().trim().matches("") ||
                    txtDescricao.getText().trim().matches(cardapioEscolhido.descricao)) {
                App.setRoot("ViewADM_Cardapio_Semanal");
            } else {
                int resp = MsgBox("Atenção", "Deseja descartar as alterações feitas?");
                if (resp == 0) { //Se foi OK
                    App.setRoot("ViewADM_Cardapio_Semanal");
                }
            }
        } catch (Exception erro) {
            MsgBox("Erro", "Ocorreu algo de errado. Tente reiniciar o programa.");
        }
    }

    @FXML
    private void OnClick_btnExcluir() throws IOException {
        int resp = MsgBox("CUIDADO", "Deseja mesmo EXCLUIR a descrição desse cardápio?");
        if (resp == 0) { //Se foi OK

            App.setRoot("ViewADM_Cardapio_Semanal");
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
}
