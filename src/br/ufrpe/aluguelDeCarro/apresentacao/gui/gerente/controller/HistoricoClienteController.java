package br.ufrpe.aluguelDeCarro.apresentacao.gui.gerente.controller;

import br.ufrpe.aluguelDeCarro.excecoes.CategoriaNaoEncontradaException;
import br.ufrpe.aluguelDeCarro.excecoes.carro.CarroNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.fachada.FachadaGerente;
import br.ufrpe.aluguelDeCarro.negocio.entidades.*;
import com.jfoenix.controls.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * @author Fernando
 */
public class HistoricoClienteController implements Initializable {
    @FXML
    private TableView<Cliente> tableView;

    @FXML
    private TableColumn<Cliente, String> cpfColumn;

    @FXML
    private JFXComboBox<Carro> carroComboBox;

    @FXML
    private JFXDatePicker retiradaDatePicker;

    @FXML
    private JFXTimePicker retiradaTimePicker;

    @FXML
    private JFXDatePicker devolucaoRealDatePicker;

    @FXML
    private JFXTimePicker devolucaoRealTimePicker;

    @FXML
    private JFXTextField valorEstimadoTextField;

    @FXML
    private JFXTextField custoAdicionalTextField;

    @FXML
    private JFXTextField placaTextField;

    @FXML
    private JFXTextField modeloTextField;

    @FXML
    private JFXTextField marcaTextField;

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
    private JFXComboBox<Categoria> categoriaComboBox;

    @FXML
    private JFXComboBox<Cambio> cambioComboBox;

    @FXML
    private JFXComboBox<Direcao> direcaoComboBox;

    private ObservableList<Cliente> clientes;
    private ObservableList<Aluguel> alugueis;
    private ObservableList<Carro> carros;
    private FachadaGerente fachada = new FachadaGerente();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarTabela();
        configurarComboBox();
    }

    private void configurarComboBox() {
        alugueis = FXCollections.observableArrayList();
        carros = FXCollections.observableArrayList();
        carroComboBox.setItems(carros);
        carroComboBox.setOnAction(event -> mostrarDetalhes());
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
        carroComboBox.setConverter(new StringConverter<Carro>() {
            @Override
            public String toString(Carro object) {
                return object.getPlaca();
            }

            @Override
            public Carro fromString(String string) {
                try {
                    return fachada.consultarCarro(string);
                } catch (CarroNaoEncontradoException e) {
                    return null;
                }
            }
        });
    }

    private void mostrarDetalhes() {
        Aluguel aluguel = alugueis.stream()
                .filter(aluguel1 -> aluguel1.getCarro().equals(carroComboBox.getValue()))
                .findFirst()
                .orElse(null);
        if (aluguel != null) {
            retiradaDatePicker.setValue(aluguel.getRetirada().toLocalDate());
            retiradaTimePicker.setValue(aluguel.getRetirada().toLocalTime());
            if (aluguel.getDevolucaoReal() != null) {
                devolucaoRealDatePicker.setValue(aluguel.getDevolucaoReal().toLocalDate());
                devolucaoRealTimePicker.setValue(aluguel.getDevolucaoReal().toLocalTime());
            }
            cambioComboBox.setValue(aluguel.getCarro().getCambio());
            categoriaComboBox.setValue(aluguel.getCategoria());
            direcaoComboBox.setValue(aluguel.getCarro().getDirecao());
            valorEstimadoTextField.setText(String.valueOf(aluguel.getValorEstimado()));
            custoAdicionalTextField.setText(String.valueOf(aluguel.getCustoAdicional()));
            marcaTextField.setText(aluguel.getCarro().getMarca());
            modeloTextField.setText(aluguel.getCarro().getModelo());
            placaTextField.setText(aluguel.getCarro().getPlaca());
            arCondicionadoCheckBox.setSelected(aluguel.getCarro().isArCondicionado());
            airBagCheckBox.setSelected(aluguel.getCarro().isAirBag());
            freioABSCheckBox.setSelected(aluguel.getCarro().isFreioAbs());
            travaEletricaCheckBox.setSelected(aluguel.getCarro().isTravaEletrica());
            vidroEletricoCheckBox.setSelected(aluguel.getCarro().isVidroEletrico());
        } else {
            retiradaDatePicker.setValue(null);
            retiradaTimePicker.setValue(null);
            devolucaoRealDatePicker.setValue(null);
            devolucaoRealTimePicker.setValue(null);
            cambioComboBox.setValue(null);
            categoriaComboBox.setValue(null);
            direcaoComboBox.setValue(null);
            valorEstimadoTextField.clear();
            custoAdicionalTextField.clear();
            marcaTextField.clear();
            modeloTextField.clear();
            placaTextField.clear();
            arCondicionadoCheckBox.setSelected(false);
            airBagCheckBox.setSelected(false);
            freioABSCheckBox.setSelected(false);
            travaEletricaCheckBox.setSelected(false);
            vidroEletricoCheckBox.setSelected(false);
        }
    }

    private void configurarTabela() {
        clientes = FXCollections.observableArrayList();
        tableView.setItems(clientes);
        clientes.addAll(fachada.consultarClientes());
        cpfColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCpf()));
        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> carregarAlugueis(newValue));
    }

    private void carregarAlugueis(Cliente cliente) {
        alugueis.clear();
        alugueis.addAll(fachada.consultarAlugueis(cliente));
        carros.clear();
        carros.addAll(alugueis.stream().map(Aluguel::getCarro).collect(Collectors.toList()));
    }
}
