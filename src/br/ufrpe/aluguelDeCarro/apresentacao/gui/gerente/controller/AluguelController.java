package br.ufrpe.aluguelDeCarro.apresentacao.gui.gerente.controller;

import br.ufrpe.aluguelDeCarro.excecoes.aluguel.*;
import br.ufrpe.aluguelDeCarro.excecoes.carro.CarroInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteInvalidoException;
import br.ufrpe.aluguelDeCarro.fachada.FachadaGerente;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Aluguel;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Carro;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Categoria;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Cliente;
import br.ufrpe.aluguelDeCarro.servicos.ViewUtil;
import com.jfoenix.controls.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * @author Fernando
 */
public class AluguelController implements Initializable {
    @FXML
    private TableView<Aluguel> tableView;

    @FXML
    private TableColumn<Aluguel, String> clienteColumn;

    @FXML
    private TableColumn<Aluguel, String> carroColumn;

    @FXML
    private JFXDatePicker retiradaDatePicker;

    @FXML
    private JFXTimePicker retiradaTimePicker;

    @FXML
    private JFXDatePicker devolucaoEstimadaDatePicker;

    @FXML
    private JFXTimePicker devolucaoEstimadaTimePicker;

    @FXML
    private JFXDatePicker devolucaoRealDatePicker;

    @FXML
    private JFXTimePicker devolucaoRealTimePicker;

    @FXML
    private JFXTextField valorEstimadoTextField;

    @FXML
    private JFXTextField custoAdicionalTextField;

    @FXML
    private JFXComboBox<Cliente> clienteComboBox;

    @FXML
    private JFXComboBox<Categoria> categoriaComboBox;

    @FXML
    private JFXComboBox<Carro> carroComboBox;

    @FXML
    private JFXButton deletarButton;

    @FXML
    private JFXButton novoButton;

    @FXML
    private JFXButton salvarButton;

    private ObservableList<Aluguel> alugueis;
    private ObservableList<Categoria> categorias;
    private ObservableList<Carro> carros;

    @FXML
    void deletar(ActionEvent event) {
    }

    @FXML
    void novo(ActionEvent event) {
        tableView.getSelectionModel().clearSelection();
        mostrarDetalhes(null);
    }

    @FXML
    void salvar(ActionEvent event) {
        Aluguel aluguel = lerInputs();
        try {
            FachadaGerente.getInstance().cadastrarAluguel(aluguel);
        } catch (CustoAdicionalNegativoException e) {
            ViewUtil.mostrarTooltip(custoAdicionalTextField, e.getMessage());
        } catch (DataEstimadaInconsistenteException | DataEstimadaPassado e) {
            ViewUtil.mostrarTooltip(devolucaoEstimadaDatePicker, e.getMessage());
        } catch (DataRetiradaInconsistenteException | DataRetiradaFuturoException | DataRetiradaPassadoException e) {
            ViewUtil.mostrarTooltip(retiradaDatePicker, e.getMessage());
        } catch (CarroInvalidoException e) {
            ViewUtil.mostrarTooltip(carroComboBox, e.getMessage());
        } catch (AluguelInvalidoException e) {
            e.printStackTrace();
        } catch (ClienteInvalidoException e) {
            ViewUtil.mostrarTooltip(clienteComboBox, e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarTabela();
        configurarComboBox();
    }

    private void configurarComboBox() {
        clienteComboBox.getItems().addAll(FachadaGerente.getInstance().consultarClientes());
        categorias = FXCollections.observableArrayList();
        categoriaComboBox.setItems(categorias);
        categoriaComboBox.setOnShowing(event -> carregarCategorias());
        carros = FXCollections.observableArrayList();
        carroComboBox.setItems(carros);
        carroComboBox.setOnShowing(event -> carregarCarros());
    }

    private void carregarCarros() {
        if (!categoriaComboBox.getSelectionModel().isEmpty()) {
            carros.clear();
            carros.addAll(FachadaGerente.getInstance().consultarCarros(categoriaComboBox.getValue()));
        } else {
            ViewUtil.mostrarTooltip(carroComboBox, "Selecione uma categoria");
        }
    }

    private void carregarCategorias() {
        Aluguel aluguel = lerInputs();
        if (aluguel.getRetirada() != null && aluguel.getDevolucaoEstimada() != null) {
            categorias.clear();
            categorias.addAll(FachadaGerente.getInstance().verificarCategoriasDisponiveis(aluguel));
        } else {
            ViewUtil.mostrarTooltip(categoriaComboBox, "Preencha a data de retirada e devolução");
        }
    }

    private void configurarTabela() {
        alugueis = FXCollections.observableArrayList();
        alugueis.addAll(FachadaGerente.getInstance().consultarAlugueis());
        tableView.setItems(alugueis);
        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarDetalhes(newValue));
        clienteColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getCliente().getCpf()));
        carroColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getCarro().getPlaca()));
    }

    private Aluguel lerInputs() {
        Aluguel aluguel = new Aluguel();
        aluguel.setRetirada(LocalDateTime.of(retiradaDatePicker.getValue(), retiradaTimePicker.getValue()));
        aluguel.setDevolucaoEstimada(LocalDateTime.of(devolucaoEstimadaDatePicker.getValue(), devolucaoEstimadaTimePicker.getValue()));
        aluguel.setDevolucaoReal(LocalDateTime.of(devolucaoRealDatePicker.getValue(), devolucaoRealTimePicker.getValue()));
        aluguel.setCustoAdicional(new BigDecimal(custoAdicionalTextField.getText()));
        aluguel.setCliente(clienteComboBox.getValue());
        aluguel.setCategoria(categoriaComboBox.getValue());
        aluguel.setCarro(carroComboBox.getValue());
        return aluguel;
    }

    private void mostrarDetalhes(Aluguel aluguel) {
        if (aluguel != null) {
            retiradaDatePicker.setValue(aluguel.getRetirada().toLocalDate());
            retiradaTimePicker.setValue(aluguel.getRetirada().toLocalTime());
            devolucaoEstimadaDatePicker.setValue(aluguel.getDevolucaoEstimada().toLocalDate());
            devolucaoEstimadaTimePicker.setValue(aluguel.getDevolucaoEstimada().toLocalTime());
            devolucaoRealDatePicker.setValue(aluguel.getDevolucaoReal().toLocalDate());
            devolucaoRealTimePicker.setValue(aluguel.getDevolucaoReal().toLocalTime());
            valorEstimadoTextField.setText(String.valueOf(aluguel.getValorEstimado()));
            custoAdicionalTextField.setText(String.valueOf(aluguel.getCustoAdicional()));
            categoriaComboBox.setValue(aluguel.getCategoria());
            carroComboBox.setValue(aluguel.getCarro());
            clienteComboBox.setValue(aluguel.getCliente());
        } else {
            retiradaDatePicker.setValue(null);
            retiradaTimePicker.setValue(null);
            devolucaoEstimadaDatePicker.setValue(null);
            devolucaoEstimadaTimePicker.setValue(null);
            devolucaoRealDatePicker.setValue(null);
            devolucaoRealTimePicker.setValue(null);
            valorEstimadoTextField.clear();
            custoAdicionalTextField.clear();
            categoriaComboBox.getSelectionModel().clearSelection();
            carroComboBox.getSelectionModel().clearSelection();
            clienteComboBox.getSelectionModel().clearSelection();
        }

    }
}
