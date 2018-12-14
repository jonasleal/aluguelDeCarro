package br.ufrpe.aluguelDeCarro.apresentacao.gui.usuario.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
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
public class ReservaController implements Initializable {
    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<?, ?> clienteColumn;

    @FXML
    private TableColumn<?, ?> carroColumn;

    @FXML
    private JFXDatePicker dataDatePicker;

    @FXML
    private ChoiceBox<?> clienteChoiceBox;

    @FXML
    private ChoiceBox<?> carroChoiceBox;

    @FXML
    private JFXButton novoButton;

    @FXML
    private JFXButton salvarButton;

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
