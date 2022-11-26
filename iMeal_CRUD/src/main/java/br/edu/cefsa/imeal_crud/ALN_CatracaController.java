/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.cefsa.imeal_crud;

import Database.CardapioDAO;
import Database.RefeicaoDAO;
import Database.ReservaDAO;
import Domain.Cardapio;
import Domain.Refeicao;
import Domain.Reserva;
import Domain.TipoUsuario;
import Domain.Usuario;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Matheus
 */
public class ALN_CatracaController implements Initializable {

    private static Cardapio cardapioEscolhido;
    private static Reserva reservaAtual;
    private static Boolean reservaFeita;
    private CardapioDAO cardapioDAO = new CardapioDAO();
    private ReservaDAO reservaDAO = new ReservaDAO();
    private RefeicaoDAO refeicaoDAO = new RefeicaoDAO();
    @FXML
    private ImageView imgVisor;
    @FXML
    private Label lblRefeicao;
    @FXML
    private DatePicker txtData;
    @FXML
    private TextField txtHorario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        App.stageIni.setHeight(610);
        changeVisor("Visor_Aguardando");
        REMOVER();
    }

    private void changeVisor(String nameImgVisor) {
        imgVisor.setImage(new Image(getClass().getResourceAsStream("/imgs/" + nameImgVisor + ".png")));
    }

    private void resizeWindowBack() {
        App.stageIni.setHeight(540);
    }

    @FXML
    private void OnClick_btnVoltar() throws IOException {
        resizeWindowBack();
        App.setRoot("ViewALN_Cardapio_Semanal");
    }

    private Boolean ValidaEntradas() {
        Boolean valid = true;

        try {
            txtHorario.setStyle("-fx-text-fill: black");
            LocalDate diaEscolhido = txtData.getValue();

            String txtHorarioEscolhido = txtHorario.getText().trim();
            LocalTime horarioEscolhido = LocalTime.of(
                    Integer.parseInt(txtHorarioEscolhido.substring(0, 2)),
                    Integer.parseInt(txtHorarioEscolhido.substring(3, 5)));

            if (horarioEscolhido != null) {
                if (diaEscolhido != null) {
                    REMOVER();
//                  cardapioEscolhido = findCardapio(horarioEscolhido, diaEscolhido);
//                  reservaAtual = findReserva();   
                    lblRefeicao.setText("Refeição: " + cardapioEscolhido.getRefeicao().getNome() +
                            " de " + cardapioEscolhido.getData() + 
                            "\n" + cardapioEscolhido.getRefeicao().getHorarioInicio() +
                            " - " + cardapioEscolhido.getRefeicao().getHorarioFim());
                } else{
                    valid = false;
                }
            } else {
                txtHorario.setStyle("-fx-text-fill: red");
                valid = false;
            }
        } catch (Exception erro) {
            //ignora, usuario ta digitando, não atrapalhe
            txtHorario.setStyle("-fx-text-fill: red");
            valid = false;
        }

        return valid;
    }

    @FXML
    private void OnChange_txtDataHora() {
        ValidaEntradas();
    }

    @FXML
    private void OnClick_btnEntrar() {
        if (ValidaEntradas()) {
            if (true) {
                EntrarRefeitorio();
            } else {
                NaoEntrouRefeitorio("Tentou entrar duas vezes");
            }
        } else{
            MsgBoxErro("Inválido", "Entrada de horário e/ou data inválidos.");
        }

        changeVisor("Visor_Aguardando");
    }

    private void EntrarRefeitorio() {
        changeVisor("Visor_Permite");
        MsgBox("Sucesso", "Você entrou no refeitório.\n... Por fim, saiu do refeitório.");
    }

    private void NaoEntrouRefeitorio(String strMotivo) {
        changeVisor("Visor_Nao_Permite");
        MsgBox("Sucesso", "Você não entrou no refeitório.\nMotivo: " + strMotivo);
    }

    private Integer MsgBox(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
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

    private Integer MsgBoxErro(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
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

    private void REMOVER() {
        LoginController.usuarioAtual = new Usuario(1, "081200024", "Matheus Vinicius Miranda Brito", "aluno@aluno", "aluno1234", TipoUsuario.ALUNO);
        cardapioEscolhido = new Cardapio(1,
                new Refeicao(1,
                        "Janta",
                        LocalTime.MIDNIGHT,
                        LocalTime.NOON,
                        LocalTime.MIDNIGHT),
                LocalDate.MAX,
                "Feijoada");

        cardapioEscolhido = new Cardapio(1,
                new Refeicao(1, "Janta", LocalTime.of(18, 15), LocalTime.of(19, 05), LocalTime.of(17, 00)),
                LocalDate.of(2022, 11, 25),
                "Feijoada");

        reservaAtual = new Reserva(1,
                LoginController.usuarioAtual,
                cardapioEscolhido,
                LocalDateTime.of(2022, 11, 25, 13, 01, 59));
    }

    private Cardapio findCardapio(LocalTime horarioTentativa, LocalDate diaTentativa) {
        Cardapio cardapioAux = new Cardapio();
        Refeicao refeicaoAux = refeicaoDAO.read(horarioTentativa);
        cardapioAux = cardapioDAO.read(diaTentativa, refeicaoAux.getId());
        return cardapioAux;
    }

    private Reserva findReserva() {
        return reservaDAO.read(LoginController.usuarioAtual, cardapioEscolhido);
    }
}
