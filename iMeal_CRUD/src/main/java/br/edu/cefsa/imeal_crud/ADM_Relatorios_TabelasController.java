/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.cefsa.imeal_crud;

import Domain.Cardapio;
import Domain.HistoricoConsumo;
import Domain.HistoricoConsumoLimitado;
import Domain.Refeicao;
import Domain.TipoUsuario;
import Domain.Usuario;
import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Matheus
 */
public class ADM_Relatorios_TabelasController implements Initializable {

    @FXML
    private Label lblDia;
    @FXML
    private Label lblTitulo;
    @FXML
    private CheckBox cbJanta;
    @FXML
    private CheckBox cbLancheReforcado;
    @FXML
    private TableView<HistoricoConsumoLimitado> tableViewInfos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            //att filtros checkbox
            cbJanta.setSelected(ADM_Relatorios_CardsController.filtroJanta);
            cbLancheReforcado.setSelected(ADM_Relatorios_CardsController.filtroLancheReforcado);
            lblTitulo.setText(ADM_Relatorios_CardsController.cardClicado);
            SetData();
            SetTabela();
        } catch (Exception erro) {
            MsgBox("Erro", "Ocorreu algo de errado. Tente reiniciar o programa.");
        }
    }

    @FXML
    private void OnClick_btnVoltar() throws IOException {
        App.setRoot("ViewADM_Relatorios_Cards");
    }

    private void SetTabela() {
        List<HistoricoConsumoLimitado> dados = new LinkedList<HistoricoConsumoLimitado>();
        dados.add(new HistoricoConsumoLimitado(
                "081200024",
                "Matheus Vinicius Miranda Brito",
                "Janta",
                LocalDate.of(2022, 10, 21),
                "Feijoada",
                LocalTime.of(18, 00),
                Boolean.FALSE,
                "Tentou entrar mais de uma vez na mesma refeição"));

        TableColumn[] colunas = new TableColumn[8];
        Integer i = 0;

        colunas[0] = new TableColumn<HistoricoConsumoLimitado, String>("RA");
        colunas[0].setCellValueFactory(new PropertyValueFactory<HistoricoConsumoLimitado, String>("ra"));
        
        colunas[1] = new TableColumn<HistoricoConsumoLimitado, String>("Nome");
        colunas[1].setCellValueFactory(new PropertyValueFactory<HistoricoConsumoLimitado, String>("nome"));

        colunas[2] = new TableColumn<HistoricoConsumoLimitado, String>("Refeicao");
        colunas[2].setCellValueFactory(new PropertyValueFactory<HistoricoConsumoLimitado, String>("refeicao"));

        colunas[3] = new TableColumn<HistoricoConsumoLimitado, LocalDate>("Data");
        colunas[3].setCellValueFactory(new PropertyValueFactory<HistoricoConsumoLimitado, LocalDate>("data"));

        colunas[4] = new TableColumn<HistoricoConsumoLimitado, String>("Cardapio");
        colunas[4].setCellValueFactory(new PropertyValueFactory<HistoricoConsumoLimitado, String>("descricao"));

        colunas[5] = new TableColumn<HistoricoConsumoLimitado, LocalTime>("Horário de chegada");
        colunas[5].setCellValueFactory(new PropertyValueFactory<HistoricoConsumoLimitado, LocalTime>("horarioChegada"));

        colunas[6] = new TableColumn<HistoricoConsumoLimitado, Boolean>("Entrada autorizada?");
        colunas[6].setCellValueFactory(new PropertyValueFactory<HistoricoConsumoLimitado, Boolean>("entradaAutorizada"));

        colunas[7] = new TableColumn<HistoricoConsumoLimitado, String>("Motivo");
        colunas[7].setCellValueFactory(new PropertyValueFactory<HistoricoConsumoLimitado, String>("motivo")); 

        for (TableColumn coluna : colunas) {
            tableViewInfos.getColumns().add(coluna);
        }

        tableViewInfos.getItems().setAll(dados);
    }

    private String FormataDia(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private LocalDate[] getDiasDaSemana(LocalDate dataAtual) {
        //0 - Seg, 1 - Ter, ..., 6 - Dom
        Integer diaDaSemanaAtual = DayOfWeek.from(dataAtual).getValue() - 1;

        LocalDate[] diasDaSemana = new LocalDate[7];

        if (diaDaSemanaAtual < 5) { //normal
            diasDaSemana[0] = dataAtual.minusDays(diaDaSemanaAtual);

        } else { //sábado ou domingo, vai pra próxima semana
            diasDaSemana[0] = dataAtual.minusDays(diaDaSemanaAtual + 7);
            diaDaSemanaAtual = DayOfWeek.from(dataAtual).getValue() - 1;
        }

        for (int i = 1; i < 7; i++) {
            diasDaSemana[i] = diasDaSemana[0].plusDays(i);
        }

        return diasDaSemana;
    }

    private String FormataSemana(LocalDate date) {
        LocalDate[] diasDaSemana = getDiasDaSemana(date);
        return diasDaSemana[0].format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + " - "
                + diasDaSemana[4].format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private void SetData() {
        if (ADM_RelatoriosController.tipoRelatorio == "Diario") {
            lblDia.setText(FormataDia(ADM_RelatoriosController.dataEscolhida[0]));
        } else if (ADM_RelatoriosController.tipoRelatorio == "Semanal") {
            lblDia.setText(FormataSemana(ADM_RelatoriosController.dataEscolhida[0]));
        } else {
            MsgBox("Erro", "Tipo de relatório inválido. Tente reiniciar o programa.");
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
