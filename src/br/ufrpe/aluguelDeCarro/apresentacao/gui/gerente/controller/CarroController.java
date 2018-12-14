package br.ufrpe.aluguelDeCarro.apresentacao.gui.gerente.controller;

import br.ufrpe.aluguelDeCarro.excecoes.CategoriaNaoEncontradaException;
import br.ufrpe.aluguelDeCarro.excecoes.bancoDeDados.IdNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.carro.*;
import br.ufrpe.aluguelDeCarro.fachada.FachadaGerente;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Cambio;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Carro;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Categoria;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Direcao;
import br.ufrpe.aluguelDeCarro.servicos.ViewUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.Arrays;
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

    @FXML
    private JFXButton deletarButton;

    @FXML
    private JFXButton novoButton;

    @FXML
    private JFXButton salvarButton;

    private ObservableList<Carro> carros;
    private FachadaGerente fachada = new FachadaGerente();
    
    @FXML
    void deletar(ActionEvent event) {
        Carro carro = tableView.getSelectionModel().getSelectedItem();
        if (carro != null) {
            try {
                fachada.desativarCarro(carro.getId());
                carros.remove(carro);
                mostrarDetalhes(null);
                tableView.getSelectionModel().clearSelection();
                ViewUtil.mostrarTooltip(deletarButton, "Carro deletado com sucesso");
            } catch (IdNaoEncontradoException e) {
                ViewUtil.mostrarTooltip(deletarButton, e.getMessage());
            }
        } else {
            tableView.requestFocus();
            ViewUtil.mostrarTooltip(deletarButton, "Selecione um carro para deletar");
        }
    }

    @FXML
    void novo(ActionEvent event) {
        mostrarDetalhes(null);
        tableView.getSelectionModel().clearSelection();
    }

    @FXML
    void salvar(ActionEvent event) {
        if (validarInputs()) {
            Carro carro = lerInputs();
            try {
                fachada.cadastrarCarro(carro);
                carros.add(carro);
                mostrarDetalhes(null);
                tableView.getSelectionModel().clearSelection();
                ViewUtil.mostrarTooltip(salvarButton, "Carro salvo com sucesso");
            } catch (PlacaObrigatorioException | FormatoPlacaInvalidoException | CarroJaCadastradoException e) {
                placaTextField.requestFocus();
                ViewUtil.mostrarTooltip(placaTextField, e.getMessage());
            } catch (MarcaObrigatorioException | FormatoMarcaException e) {
                marcaTextField.requestFocus();
                ViewUtil.mostrarTooltip(marcaTextField, e.getMessage());
            } catch (ModeloObrigatorioException | FormatoModeloException e) {
                modeloTextField.requestFocus();
                ViewUtil.mostrarTooltip(modeloTextField, e.getMessage());
            } catch (CarroInvalidoException e) {
                ViewUtil.mostrarTooltip(salvarButton, e.getMessage());
            }
        }
    }

    private boolean validarInputs() {
        if (!portasTextField.getText().matches("^\\d+")) {
            portasTextField.requestFocus();
            ViewUtil.mostrarTooltip(portasTextField, "Valor inválido");
            return false;
        } else if (!ocupantesTextField.getText().matches("^\\d+")) {
            ocupantesTextField.requestFocus();
            ViewUtil.mostrarTooltip(ocupantesTextField, "Valor inválido");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarTabela();
        configurarComboBox();
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

    private void configurarComboBox() {
        cambioComboBox.setItems(FXCollections.observableArrayList(Arrays.asList(Cambio.values())));
        direcaoComboBox.setItems(FXCollections.observableArrayList(Arrays.asList(Direcao.values())));
        categoriaComboBox.setItems(FXCollections.observableArrayList(
                fachada.consultarCategorias()));
        categoriaComboBox.setConverter(new StringConverter<Categoria>() {
            @Override
            public String toString(Categoria object) {
                return object.getNome();
            }

            @Override
            public Categoria fromString(String string) {
                try {
                    return fachada.consultarCategoria(string);
                } catch (CategoriaNaoEncontradaException e) {
                    return null;
                }
            }
        });
    }

    private Carro lerInputs() {
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
        carro.setCategoria(categoriaComboBox.getValue());
        carro.setDirecao(direcaoComboBox.getValue());
        carro.setCambio(cambioComboBox.getValue());
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
