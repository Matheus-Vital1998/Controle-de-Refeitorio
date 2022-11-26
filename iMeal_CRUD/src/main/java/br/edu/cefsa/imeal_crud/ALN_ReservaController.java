/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.cefsa.imeal_crud;

import Database.CardapioDAO;
import Database.RefeicaoDAO;
import Database.ReservaDAO;
import Domain.Cardapio;
import Domain.Reserva;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
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
public class ALN_ReservaController implements Initializable {

    private static Cardapio cardapioEscolhido;
    private static Reserva reservaAtual;
    private static Boolean reservaFeita;
    private CardapioDAO cardapioDAO = new CardapioDAO();
    private ReservaDAO reservaDAO = new ReservaDAO();
    @FXML
    private TextArea txtDescricao;
    @FXML
    private Label lblTitulo;
    @FXML
    private Label lblConfirmarIda;
    @FXML
    private Button btnConfirmar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cardapioEscolhido = new Cardapio();
        cardapioEscolhido = cardapioDAO.read(ALN_Cardapio_SemanalController.dataEscolhida,
                ALN_Cardapio_SemanalController.refeicaoEscolhida.getId());
        reservaAtual = reservaDAO.read(LoginController.usuarioAtual, cardapioEscolhido);

        if (!(cardapioEscolhido.getDescricao() == null || cardapioEscolhido.getDescricao().matches(""))) {
            txtDescricao.setText(cardapioEscolhido.getDescricao());
        }

        String tituloTela = "";
        reservaFeita = (reservaAtual == null || reservaAtual.getId() == null);
        if (reservaFeita) {
            tituloTela = "Fazer reserva";
        } else {
            tituloTela = "Editar reserva";
            lblConfirmarIda.setText("Desfazer reserva à refeição?");
            btnConfirmar.setText("Desfazer");
        }

        tituloTela += ": " + ALN_Cardapio_SemanalController.refeicaoEscolhida.getNome() + " de "
                + ALN_Cardapio_SemanalController.diaDaSemanaEscolhido + " - "
                + ALN_Cardapio_SemanalController.dataEscolhida.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        lblTitulo.setText(tituloTela);
    }

    @FXML
    private void OnClick_btnVoltar() throws IOException {
        App.setRoot("ViewALN_Cardapio_Semanal");
    }

    @FXML
    private void OnClick_btnConfirmar() throws IOException {
        try {
            if (reservaFeita) {
                int resp = MsgBox("Confirmação", "Salvar as alterações feitas?");
                if (resp == 0) {
                    if (reservaAtual == null) {
                        reservaAtual = new Reserva();
                    }
                    if (reservaAtual.getId() == null) {
                        reservaAtual.setUsuario(LoginController.usuarioAtual);
                        reservaAtual.setCardapio(cardapioDAO.read(ALN_Cardapio_SemanalController.dataEscolhida,
                                ALN_Cardapio_SemanalController.refeicaoEscolhida.getId()));
                        reservaAtual.setHorario(LocalDateTime.now());
                    } else {
                        MsgBox("Erro", "Você já reservou essa refeição. Tente reiniciar o programa.");
                        return;
                    }

                    //Não permite reservar se não tiver cardapio criado
                    if (reservaAtual.getCardapio().getId() == null) {
                        MsgBox("Inválido", "Cardápio ainda não criado.");
                        App.setRoot("ViewALN_Cardapio_Semanal");
                        return;
                    }

                    reservaDAO.create(reservaAtual);

                    App.setRoot("ViewALN_Cardapio_Semanal");
                    MsgBox("Sucesso", "Salvamento confirmado.");
                }
            } else {
                int resp = MsgBox("Confirmação", "Deseja mesmo desfazer sua reserva?");
                if (resp == 0) {
                    if (reservaAtual.getId() == null) {
                        MsgBox("Erro", "Não há refeição para desfazer. Tente reiniciar o programa.");
                        return;
                    }

                    reservaDAO.delete(reservaAtual.getId());

                    App.setRoot("ViewALN_Cardapio_Semanal");
                    MsgBox("Sucesso", "Salvamento confirmado.");
                }
            }
        } catch (Exception erro) {
            MsgBox("Erro", "Ocorreu algo de errado. Tente reiniciar o programa.");
        }
    }

    @FXML
    private void OnClick_btnCancelar() throws IOException {
        App.setRoot("ViewALN_Cardapio_Semanal");
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
