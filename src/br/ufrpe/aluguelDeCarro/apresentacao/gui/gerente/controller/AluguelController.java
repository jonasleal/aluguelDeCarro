package br.ufrpe.aluguelDeCarro.apresentacao.gui.gerente.controller;

import br.ufrpe.aluguelDeCarro.excecoes.CategoriaNaoEncontradaException;
import br.ufrpe.aluguelDeCarro.excecoes.aluguel.*;
import br.ufrpe.aluguelDeCarro.excecoes.carro.CarroInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.carro.CarroNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.categoria.CategoriaInvalidaException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.usuario.UsuarioInvalidoException;
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
import javafx.util.StringConverter;

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
            custoAdicionalTextField.requestFocus();
            ViewUtil.mostrarTooltip(custoAdicionalTextField, e.getMessage());
        } catch (DataEstimadaInconsistenteException | DataEstimadaPassado | DataDevolucacaoEstimadaObrigatoriaException e) {
            devolucaoEstimadaTimePicker.requestFocus();
            ViewUtil.mostrarTooltip(devolucaoEstimadaDatePicker, e.getMessage());
        } catch (DataRetiradaInconsistenteException | DataRetiradaFuturoException | DataRetiradaPassadoException | DataRetiradaObrigatoriaException e) {
            retiradaDatePicker.requestFocus();
            ViewUtil.mostrarTooltip(retiradaDatePicker, e.getMessage());
        } catch (CarroInvalidoException e) {
            carroComboBox.requestFocus();
            ViewUtil.mostrarTooltip(carroComboBox, e.getMessage());
        } catch (CategoriaInvalidaException e) {
            categoriaComboBox.requestFocus();
            ViewUtil.mostrarTooltip(categoriaComboBox, e.getMessage());
        } catch (AluguelInvalidoException e) {
            ViewUtil.mostrarTooltip(salvarButton, e.getMessage());
        } catch (ClienteInvalidoException e) {
            clienteComboBox.requestFocus();
            ViewUtil.mostrarTooltip(clienteComboBox, e.getMessage());
        } catch (UsuarioInvalidoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarTabela();
        configurarComboBox();
        configurarTimePicker();
        configurarOnActionDatas();
    }

    private void configurarOnActionDatas() {
        retiradaDatePicker.setOnAction(event -> limparComboBoxes());
        retiradaTimePicker.setOnAction(event -> limparComboBoxes());
        devolucaoRealDatePicker.setOnAction(event -> limparComboBoxes());
        devolucaoRealTimePicker.setOnAction(event -> limparComboBoxes());
        devolucaoEstimadaDatePicker.setOnAction(event -> limparComboBoxes());
        devolucaoEstimadaTimePicker.setOnAction(event -> limparComboBoxes());
    }

    private void limparComboBoxes() {
        categorias.clear();
        carros.clear();
    }

    private void configurarTimePicker() {
        retiradaTimePicker.setOverLay(false);
        devolucaoRealTimePicker.setOverLay(false);
        devolucaoEstimadaTimePicker.setOverLay(false);
    }

    private void configurarComboBox() {
        configurarConverter();
        clienteComboBox.getItems().addAll(FachadaGerente.getInstance().consultarClientes());
        categorias = FXCollections.observableArrayList();
        categoriaComboBox.setItems(categorias);
        categoriaComboBox.setOnMouseClicked(event -> carregarCategorias());
        carros = FXCollections.observableArrayList();
        carroComboBox.setItems(carros);
        carroComboBox.setOnMouseClicked(event -> carregarCarros());
    }

    private void configurarConverter() {
        clienteComboBox.setConverter(new StringConverter<Cliente>() {
            @Override
            public String toString(Cliente cliente) {
                return cliente != null ? cliente.getCpf() : "";
            }

            @Override
            public Cliente fromString(String cpf) {
                try {
                    return FachadaGerente.getInstance().consultarCliente(cpf);
                } catch (ClienteNaoEncontradoException e) {
                    return null;
                }
            }
        });
        categoriaComboBox.setConverter(new StringConverter<Categoria>() {
            @Override
            public String toString(Categoria categoria) {
                return categoria != null ? categoria.getNome() : "";
            }

            @Override
            public Categoria fromString(String nome) {
                try {
                    return FachadaGerente.getInstance().consultarCategoria(nome);
                } catch (CategoriaNaoEncontradaException e) {
                    return null;
                }
            }
        });
        carroComboBox.setConverter(new StringConverter<Carro>() {
            @Override
            public String toString(Carro carro) {
                return carro != null ? carro.getPlaca() : "";
            }

            @Override
            public Carro fromString(String placa) {
                try {
                    return FachadaGerente.getInstance().consultarCarro(placa);
                } catch (CarroNaoEncontradoException e) {
                    return null;
                }
            }
        });
    }

    private void carregarCarros() {
        if (!categoriaComboBox.getSelectionModel().isEmpty()) {
            carros.clear();
            carros.addAll(FachadaGerente.getInstance().consultarCarros(categoriaComboBox.getValue()));
        } else {
            carroComboBox.hide();
            categoriaComboBox.requestFocus();
            ViewUtil.mostrarTooltip(carroComboBox, "Selecione uma categoria");
        }
    }

    private void carregarCategorias() {
        Aluguel aluguel = lerInputs();
        if (aluguel.getRetirada() != null && aluguel.getDevolucaoEstimada() != null) {
            categorias.clear();
            categorias.addAll(FachadaGerente.getInstance().verificarCategoriasDisponiveis(aluguel));
        } else {
            categoriaComboBox.hide();
            retiradaDatePicker.requestFocus();
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
        if (!custoAdicionalTextField.getText().isEmpty()) {
            aluguel.setCustoAdicional(new BigDecimal(custoAdicionalTextField.getText()));
        } else {
            aluguel.setCustoAdicional(BigDecimal.ZERO);
        }
        if (retiradaDatePicker.getValue() != null && retiradaTimePicker.getValue() != null) {
            aluguel.setRetirada(LocalDateTime.of(retiradaDatePicker.getValue(), retiradaTimePicker.getValue()));
        } else {
            aluguel.setRetirada(null);
        }
        if (devolucaoEstimadaDatePicker.getValue() != null && devolucaoEstimadaTimePicker.getValue() != null) {
            aluguel.setDevolucaoEstimada(LocalDateTime.of(devolucaoEstimadaDatePicker.getValue(), devolucaoEstimadaTimePicker.getValue()));
        } else {
            aluguel.setDevolucaoEstimada(null);
        }
        if (devolucaoRealDatePicker.getValue() != null && devolucaoRealTimePicker.getValue() != null) {
            aluguel.setDevolucaoReal(LocalDateTime.of(devolucaoRealDatePicker.getValue(), devolucaoRealTimePicker.getValue()));
        } else {
            aluguel.setDevolucaoReal(null);
        }
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
