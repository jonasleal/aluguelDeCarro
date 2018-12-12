package br.ufrpe.aluguelDeCarro.apresentacao.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Fernando
 */
public class ManutencaoView implements Initializable {
    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<?, ?> carroColumn;

    @FXML
    private TableColumn<?, ?> dataColumn;

    @FXML
    private JFXDatePicker dataDatePicker;

    @FXML
    private JFXTextField orcamentoTextField;

    @FXML
    private ChoiceBox<?> clienteChoiceBox;

    @FXML
    private ChoiceBox<?> carroChoiceBox;

    @FXML
    private JFXTextField itemTextField;

    @FXML
    private JFXButton deletarButton;

    @FXML
    private JFXButton novoButton;

    @FXML
    private JFXButton salvarButton;

    @FXML
    void deletar(ActionEvent event) {

    }

    @FXML
    void novo(ActionEvent event) {

    }

    @FXML
    void salvar(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
