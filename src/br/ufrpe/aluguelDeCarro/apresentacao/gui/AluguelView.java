package br.ufrpe.aluguelDeCarro.apresentacao.gui;

import br.ufrpe.aluguelDeCarro.negocio.entidades.Aluguel;
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
public class AluguelView implements Initializable {
    @FXML
    private TableView<Aluguel> tableView;

    @FXML
    private TableColumn<Aluguel, String> clienteColumn;

    @FXML
    private TableColumn<Aluguel, String> carroColumn;

    @FXML
    private JFXDatePicker retiradaDatePicker;

    @FXML
    private JFXDatePicker devolucaoEstimadaDatePicker;

    @FXML
    private JFXDatePicker devolucaoRealDatePicker;

    @FXML
    private JFXTextField valorEstimadoTextField;

    @FXML
    private JFXTextField custoAdicionalTextField;

    @FXML
    private ChoiceBox<?> clienteChoiceBox;

    @FXML
    private ChoiceBox<?> CarroChoiceBox;

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
