/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.cefsa.imeal_crud;

import Database.CardapioDAO;
import Database.HistoricoConsumoDAO;
import Database.RefeicaoDAO;
import Database.ReservaDAO;
import Domain.Cardapio;
import Domain.HistoricoConsumo;
import Domain.HistoricoConsumoLimitado;
import Domain.Refeicao;
import Domain.Reserva;
import Domain.TipoUsuario;
import Domain.Usuario;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
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
    private static HistoricoConsumo histConsAtual;
    private static LocalTime horarioEscolhido;
    private static LocalDate diaEscolhido;
    private CardapioDAO cardapioDAO = new CardapioDAO();
    private ReservaDAO reservaDAO = new ReservaDAO();
    private RefeicaoDAO refeicaoDAO = new RefeicaoDAO();
    private HistoricoConsumoDAO historicoConsumoDAO = new HistoricoConsumoDAO();
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
            diaEscolhido = txtData.getValue();

            String txtHorarioEscolhido = txtHorario.getText().trim();
            horarioEscolhido = LocalTime.of(
                    Integer.parseInt(txtHorarioEscolhido.substring(0, 2)),
                    Integer.parseInt(txtHorarioEscolhido.substring(3, 5)));

            if (horarioEscolhido != null) {
                if (diaEscolhido != null) {
                    cardapioEscolhido = findCardapio(horarioEscolhido, diaEscolhido);
                    reservaAtual = findReserva();

                } else {
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

        if (valid) {
            lblRefeicao.setText("Refeição: " + cardapioEscolhido.getRefeicao().getNome()
                    + " de " + cardapioEscolhido.getData()
                    + "\n" + cardapioEscolhido.getRefeicao().getHorarioInicio()
                    + " - " + cardapioEscolhido.getRefeicao().getHorarioFim());
        } else {
            lblRefeicao.setText("Refeição: ");
        }

        return valid;
    }

    @FXML
    private void OnChange_txtDataHora() {
        ValidaEntradas();
    }

    @FXML
    private void OnClick_btnEntrar() throws Exception {
        if (ValidaEntradas()) {

            String msg = ValidaReserva();
            if (msg == "") {    //Pode entrar
                EntrarRefeitorio();

            } else {
                NaoEntrouRefeitorio(msg);
            }
        } else {
            changeVisor("Visor_Nao_Permite");
            MsgBoxErro("Inválido", "Entrada de horário e/ou data inválidos.");
        }

        changeVisor("Visor_Aguardando");
    }

    private String ValidaReserva() {
        try {
            String msg = "";

            if (reservaAtual == null || reservaAtual.getId() == null) {
                return msg = "Reserva não encontrada";
            }

            List<HistoricoConsumoLimitado> historicoConsumo = historicoConsumoDAO.read(LoginController.usuarioAtual, cardapioEscolhido);

            if (historicoConsumo.size() != 0) {
                for (HistoricoConsumoLimitado linha : historicoConsumo) {
                    msg += linha.getMotivo() + "\n";
                }
            }

            if (msg.replace("\n", "").trim() == "") {
                msg = "";
            }
            
            histConsAtual = new HistoricoConsumo();
            histConsAtual.setCardapio(cardapioEscolhido);
            histConsAtual.setEntradaAutorizada(msg == "");
            histConsAtual.setHorarioChegada(horarioEscolhido);
            histConsAtual.setMotivo(msg);
            histConsAtual.setUsuario(LoginController.usuarioAtual);
            
            return msg;
        } catch (Exception erro) {
            return "ERRO";
        }
    }

    private void EntrarRefeitorio() throws Exception {
        changeVisor("Visor_Permite");
        histConsAtual.setMotivo("Entrou");
        historicoConsumoDAO.create(histConsAtual);
        MsgBox("Sucesso", "Você entrou no refeitório.\n... Por fim, saiu do refeitório.");
    }

    private void NaoEntrouRefeitorio(String strMotivo) throws Exception {
        changeVisor("Visor_Nao_Permite");
        historicoConsumoDAO.create(histConsAtual);
        MsgBox("Inválido", "Você não entrou no refeitório.\nMotivo: " + strMotivo);
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
