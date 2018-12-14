package br.ufrpe.aluguelDeCarro.apresentacao.gui.usuario.controller;

import br.ufrpe.aluguelDeCarro.fachada.FachadaGerente;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Cambio;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Carro;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Categoria;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Direcao;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Fernando
 */
public class CarroController implements Initializable {
    @FXML
    private TableView<Carro> tableView;

    @FXML
    private TableColumn<Carro, String> placaColumn;

    @FXML
    private TableColumn<Carro, String> modeloColumn;

    @FXML
    private JFXTextField placaTextField;

    @FXML
    private JFXTextField modeloTextField;

    @FXML
    private JFXTextField marcaTextField;

    @FXML
    private JFXTextField portasTextField;

    @FXML
    private JFXTextField ocupantesTextField;

    @FXML
    private JFXCheckBox vidroEletricoCheckBox;

    @FXML
    private JFXCheckBox arCondicionadoCheckBox;

    @FXML
    private JFXCheckBox airBagCheckBox;

    @FXML
    private JFXCheckBox travaEletricaCheckBox;

    @FXML
    private JFXCheckBox freioABSCheckBox;

    @FXML
    private ComboBox<Categoria> categoriaComboBox;
    @FXML
    private ComboBox<Cambio> cambioComboBox;
    @FXML
    private ComboBox<Direcao> direcaoComboBox;

    private ObservableList<Carro> carros;
    private FachadaGerente fachada = new FachadaGerente();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarTabela();
        mostrarDetalhes(null);
    }

    private void configurarTabela() {
        placaColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getPlaca()));
        modeloColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getModelo()));

        carros = FXCollections.observableArrayList();
        carros.addAll(fachada.consultarCarros());

        tableView.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> mostrarDetalhes(newValue)));
        tableView.setItems(carros);
    }

    private void mostrarDetalhes(Carro carro) {
        if (carro != null) {
            placaTextField.setText(carro.getPlaca());
            modeloTextField.setText(carro.getModelo());
            marcaTextField.setText(carro.getMarca());
            portasTextField.setText(String.valueOf(carro.getPortas()));
            ocupantesTextField.setText(String.valueOf(carro.getOcupantes()));
            vidroEletricoCheckBox.setSelected(carro.isVidroEletrico());
            arCondicionadoCheckBox.setSelected(carro.isArCondicionado());
            airBagCheckBox.setSelected(carro.isAirBag());
            travaEletricaCheckBox.setSelected(carro.isTravaEletrica());
            freioABSCheckBox.setSelected(carro.isFreioAbs());
            categoriaComboBox.setValue(carro.getCategoria());
            direcaoComboBox.setValue(carro.getDirecao());
            cambioComboBox.setValue(carro.getCambio());
        } else {
            placaTextField.clear();
            modeloTextField.clear();
            marcaTextField.clear();
            portasTextField.clear();
            ocupantesTextField.clear();
            vidroEletricoCheckBox.setSelected(false);
            arCondicionadoCheckBox.setSelected(false);
            airBagCheckBox.setSelected(false);
            travaEletricaCheckBox.setSelected(false);
            freioABSCheckBox.setSelected(false);
            categoriaComboBox.setValue(null);
            direcaoComboBox.setValue(null);
            cambioComboBox.setValue(null);
        }
    }
}
