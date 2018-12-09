package br.ufrpe.aluguelDeCarro.apresentacao.gui;

import br.ufrpe.aluguelDeCarro.dados.entidades.Cambio;
import br.ufrpe.aluguelDeCarro.dados.entidades.Carro;
import br.ufrpe.aluguelDeCarro.dados.entidades.Categoria;
import br.ufrpe.aluguelDeCarro.dados.entidades.Direcao;
import br.ufrpe.aluguelDeCarro.excecoes.*;
import br.ufrpe.aluguelDeCarro.servicos.Singleton;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * @author Fernando
 */
public class CarroView implements Initializable {
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
    private ChoiceBox<Categoria> categoriaChoiceBox;

    @FXML
    private ChoiceBox<Cambio> cambioChoiceBox;

    @FXML
    private ChoiceBox<Direcao> direcaoChoiceBox;

    @FXML
    private JFXButton deletarButton;

    @FXML
    private JFXButton novoButton;

    @FXML
    private JFXButton salvarButton;

    private ObservableList<Carro> carros;

    @FXML
    void deletar(ActionEvent event) {
        Carro carro = tableView.getSelectionModel().getSelectedItem();
        if (carro != null) {
            try {
                Singleton.getInstance().getCarroNegocio().desativar(carro.getId());
            } catch (CarroNaoEncontradoException e) {
                mostrarTooltip(deletarButton, e.getMessage());
            }
        } else {
            mostrarTooltip(deletarButton, "Selecione um carro para deletar");
        }
    }

    @FXML
    void novo(ActionEvent event) {
        mostrarDetalhes(new Carro());
    }

    @FXML
    void salvar(ActionEvent event) {
        if (inputValido()) {
            Carro carro = lerInputs();
            try {
                Singleton.getInstance().getCarroNegocio().cadastrar(carro);
            } catch (PlacaException e) {
                mostrarTooltip(placaTextField, e.getMessage());
            } catch (MarcaException e) {
                mostrarTooltip(marcaTextField, e.getMessage());
            } catch (ModeloException e) {
                mostrarTooltip(modeloTextField, e.getMessage());
            } catch (CarroException e) {
                mostrarTooltip(salvarButton, e.getMessage());
            }
        }
    }

    private boolean inputValido() {
        return portasTextField.getText().matches("\\d+") && ocupantesTextField.getText().matches("\\d+");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        placaColumn.setCellValueFactory(value -> new SimpleStringProperty(
                value.getValue() != null ? value.getValue().getPlaca() : ""));
        modeloColumn.setCellValueFactory(value -> new SimpleStringProperty(
                value.getValue() != null ? value.getValue().getModelo() : ""));

        carros = FXCollections.observableArrayList();
        carros.addAll(Singleton.getInstance().getCarroNegocio().consultarTodos());

        tableView.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> mostrarDetalhes(newValue)));

        categoriaChoiceBox.setItems(FXCollections.observableArrayList(Arrays.asList(Categoria.values())));
        cambioChoiceBox.setItems(FXCollections.observableArrayList(Arrays.asList(Cambio.values())));
        direcaoChoiceBox.setItems(FXCollections.observableArrayList(Arrays.asList(Direcao.values())));

        ocupantesTextField.textProperty().addListener(
                (observable, oldValue, newValue) -> ocupantesTextField.setText(newValue.replaceAll("[^\\d]", "")));
        portasTextField.textProperty().addListener(
                (observable, oldValue, newValue) -> portasTextField.setText(newValue.replaceAll("[^\\d]", "")));
    }

    public Carro lerInputs() {
        Carro carro = new Carro();
        carro.setPlaca(placaTextField.getText());
        carro.setModelo(modeloTextField.getText());
        carro.setMarca(marcaTextField.getText());
        carro.setPortas(Integer.parseInt(portasTextField.getText()));
        carro.setOcupantes(Integer.parseInt(ocupantesTextField.getText()));
        carro.setVidroEletrico(vidroEletricoCheckBox.isSelected());
        carro.setArCondicionado(arCondicionadoCheckBox.isSelected());
        carro.setAirBag(airBagCheckBox.isSelected());
        carro.setTravaEletrica(travaEletricaCheckBox.isSelected());
        carro.setFreioAbs(freioABSCheckBox.isSelected());
        carro.setCategoria(categoriaChoiceBox.getValue());
        carro.setDirecao(direcaoChoiceBox.getValue());
        carro.setCambio(cambioChoiceBox.getValue());
        return carro;
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
            categoriaChoiceBox.setValue(carro.getCategoria());
            direcaoChoiceBox.setValue(carro.getDirecao());
            cambioChoiceBox.setValue(carro.getCambio());
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
            categoriaChoiceBox.setValue(null);
            direcaoChoiceBox.setValue(null);
            cambioChoiceBox.setValue(null);
        }
    }

    private void mostrarTooltip(Node node, String message) {
        Tooltip tooltip = new Tooltip(message);
        tooltip.setAutoHide(true);
        Bounds bounds = node.localToScreen(node.getBoundsInLocal());
        tooltip.show(node, bounds.getMinX(), bounds.getMaxY());
    }
}
