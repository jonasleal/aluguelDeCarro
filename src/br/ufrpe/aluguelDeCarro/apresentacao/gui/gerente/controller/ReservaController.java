package br.ufrpe.aluguelDeCarro.apresentacao.gui.gerente.controller;

import br.ufrpe.aluguelDeCarro.excecoes.CategoriaNaoEncontradaException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.reserva.*;
import br.ufrpe.aluguelDeCarro.fachada.FachadaGerente;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Aluguel;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Categoria;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Cliente;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Reserva;
import br.ufrpe.aluguelDeCarro.servicos.ViewUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * @author Fernando
 */
public class ReservaController implements Initializable {

    @FXML
    private AnchorPane rootReserva;

    @FXML
    private TableView<Reserva> tableView;

    @FXML
    private TableColumn<Reserva, String> clienteColumn;

    @FXML
    private TableColumn<Reserva, String> categoriaColumn;

    @FXML
    private JFXDatePicker retiradaDatePicker;

    @FXML
    private JFXTimePicker retiradaTimePicker;

    @FXML
    private JFXDatePicker devolucaoDatePicker;

    @FXML
    private JFXTimePicker devolucaoTimePicker;

    @FXML
    private JFXComboBox<Cliente> clienteComboBox;

    @FXML
    private JFXComboBox<Categoria> categoriaComboBox;

    @FXML
    private JFXButton deletarButton;

    @FXML
    private JFXButton novoButton;

    @FXML
    private JFXButton salvarButton;

    @FXML
    private JFXButton iniciarAluguelButton;

    private ObservableList<Categoria> categorias;
    private ObservableList<Reserva> reservas;
    private ObservableList<Cliente> clientes;
    private FachadaGerente fachada = new FachadaGerente();

    @FXML
    void deletar(ActionEvent event) {
        Reserva reserva = tableView.getSelectionModel().getSelectedItem();
        if (reserva != null) {
            try {
                fachada.desativarReserva(reserva.getId());
                reservas.remove(reserva);
                tableView.getSelectionModel().clearSelection();
                ViewUtil.mostrarTooltip(deletarButton, "Reserva deletada com sucesso");
            } catch (ReservaInvalidaException e) {
                ViewUtil.mostrarTooltip(deletarButton, e.getMessage());
            }
        } else {
            tableView.requestFocus();
            ViewUtil.mostrarTooltip(deletarButton, "Selecione uma reserva para deletar");
        }
    }

    @FXML
    void novo(ActionEvent event) {
        limparSelecao();
    }

    private void limparSelecao() {
        tableView.getSelectionModel().clearSelection();
        mostrarDetalhes(null);
    }

    private void mostrarDetalhes(Reserva reserva) {
        if (reserva != null) {
            retiradaDatePicker.setValue(reserva.getRetiradaPrevista().toLocalDate());
            retiradaTimePicker.setValue(reserva.getRetiradaPrevista().toLocalTime());
            devolucaoDatePicker.setValue(reserva.getDevolucaoPrevista().toLocalDate());
            devolucaoTimePicker.setValue(reserva.getDevolucaoPrevista().toLocalTime());
            clienteComboBox.setValue(reserva.getCliente());
            carregarCategorias();
            categoriaComboBox.setValue(reserva.getCategoria());
        } else {
            retiradaDatePicker.setValue(null);
            retiradaTimePicker.setValue(null);
            devolucaoDatePicker.setValue(null);
            devolucaoTimePicker.setValue(null);
            clienteComboBox.getSelectionModel().clearSelection();
            categoriaComboBox.getSelectionModel().clearSelection();
        }
    }

    @FXML
    void salvar(ActionEvent event) {
        Reserva reserva = tableView.getSelectionModel().getSelectedItem();
        if (reserva == null) cadastrar();
        else alterar(reserva);
    }

    private void cadastrar() {
        try {
            Reserva reserva = new Reserva();
            lerInputs(reserva);
            fachada.cadastrarReserva(reserva);
            limparSelecao();
            reservas.add(reserva);
            ViewUtil.mostrarTooltip(salvarButton, "Reserva salva com sucesso");
        } catch (DataRetiradaObrigatoriaException | DataRetiradaPassadoException e) {
            retiradaDatePicker.requestFocus();
            ViewUtil.mostrarTooltip(retiradaDatePicker, e.getMessage());
        } catch (DataDevolucaoObrigatoriaException | DataDevolucaoPassadoException e) {
            devolucaoDatePicker.requestFocus();
            ViewUtil.mostrarTooltip(devolucaoDatePicker, e.getMessage());
        } catch (ClienteObrigatorioException e) {
            clienteComboBox.requestFocus();
            ViewUtil.mostrarTooltip(clienteComboBox, e.getMessage());
        } catch (CategoriaObrigatoriaException e) {
            categoriaComboBox.requestFocus();
            ViewUtil.mostrarTooltip(categoriaComboBox, e.getMessage());
        } catch (ReservaInvalidaException e) {
            ViewUtil.mostrarTooltip(salvarButton, e.getMessage());
        }
    }

    private void lerInputs(Reserva reserva) {
        if (retiradaTimePicker.getValue() != null && retiradaDatePicker.getValue() != null)
            reserva.setRetiradaPrevista(LocalDateTime.of(retiradaDatePicker.getValue(), retiradaTimePicker.getValue()));
        else
            reserva.setRetiradaPrevista(null);
        if (devolucaoDatePicker.getValue() != null && devolucaoTimePicker.getValue() != null)
            reserva.setDevolucaoPrevista(LocalDateTime.of(devolucaoDatePicker.getValue(), devolucaoTimePicker.getValue()));
        else
            reserva.setDevolucaoPrevista(null);
        reserva.setCliente(clienteComboBox.getValue());
        reserva.setCategoria(categoriaComboBox.getValue());
    }

    private void alterar(Reserva reserva) {
        try {
            lerInputs(reserva);
            fachada.alterarReserva(reserva);
            mostrarDetalhes(null);
            reservas.set(reservas.indexOf(reserva), reserva);
            ViewUtil.mostrarTooltip(salvarButton, "Reserva alterada com sucesso");
        } catch (DataRetiradaObrigatoriaException e) {
            retiradaDatePicker.requestFocus();
            ViewUtil.mostrarTooltip(retiradaDatePicker, e.getMessage());
        } catch (DataDevolucaoObrigatoriaException | DataDevolucaoPassadoException e) {
            devolucaoDatePicker.requestFocus();
            ViewUtil.mostrarTooltip(devolucaoDatePicker, e.getMessage());
        } catch (ClienteObrigatorioException e) {
            clienteComboBox.requestFocus();
            ViewUtil.mostrarTooltip(clienteComboBox, e.getMessage());
        } catch (CategoriaObrigatoriaException e) {
            categoriaComboBox.requestFocus();
            ViewUtil.mostrarTooltip(categoriaComboBox, e.getMessage());
        } catch (ReservaInvalidaException e) {
            ViewUtil.mostrarTooltip(salvarButton, e.getMessage());
        }
    }

    @FXML
    public void iniciarAluguel(ActionEvent event) {
        try {
            Reserva reserva = tableView.getSelectionModel().getSelectedItem();
            Aluguel aluguel = fachada.iniciarAluguel(reserva);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/Aluguel.fxml"));
            AnchorPane root = loader.load();
            setConstrains(root);
            AluguelController controller = loader.getController();
            controller.setAluguel(aluguel);
            AnchorPane parent = (AnchorPane) rootReserva.getParent();
            parent.getChildren().clear();
            parent.getChildren().add(root);
        } catch (ReservaObrigatoriaException e) {
            ViewUtil.mostrarTooltip(iniciarAluguelButton, "Selecione uma reserva para iniciar o aluguel");
        } catch (ReservaInvalidaException | IOException e) {
            ViewUtil.mostrarTooltip(iniciarAluguelButton, e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarTabela();
        configurarComboBox();
        configurarOnActionDatas();
    }

    private void configurarTabela() {
        reservas = FXCollections.observableArrayList(fachada.consultarReservas());
        tableView.setItems(reservas);
        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarDetalhes(newValue));
        clienteColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCliente().getCpf()));
        categoriaColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCategoria().getNome()));
    }

    private void configurarComboBox() {
        configurarConverter();
        categorias = FXCollections.observableArrayList();
        clientes = FXCollections.observableArrayList(fachada.consultarClientes());
        clienteComboBox.setItems(clientes);
        categoriaComboBox.setItems(categorias);
        categoriaComboBox.setOnMouseClicked(event -> carregarCategorias());
    }

    private void configurarConverter() {
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
        clienteComboBox.setConverter(new StringConverter<Cliente>() {
            @Override
            public String toString(Cliente object) {
                return object.getCpf();
            }

            @Override
            public Cliente fromString(String string) {
                try {
                    return fachada.consultarCliente(string);
                } catch (ClienteNaoEncontradoException e) {
                    return null;
                }
            }
        });
    }

    private void carregarCategorias() {
        Reserva reserva = new Reserva();
        lerInputs(reserva);
        if (reserva.getRetiradaPrevista() != null) {
            if (reserva.getDevolucaoPrevista() != null) {
                categorias.clear();
                categorias.addAll(fachada.consultarCategoriasDisponiveisParaReserva(reserva));
            } else {
                categoriaComboBox.hide();
                devolucaoDatePicker.requestFocus();
                ViewUtil.mostrarTooltip(categoriaComboBox, "Preencha a data de devolução");
            }
        } else {
            categoriaComboBox.hide();
            retiradaDatePicker.requestFocus();
            ViewUtil.mostrarTooltip(categoriaComboBox, "Preencha a data de devolução");
        }

    }

    private void configurarOnActionDatas() {
        retiradaDatePicker.setOnAction(event -> limparCategorias());
        retiradaTimePicker.setOnAction(event -> limparCategorias());
        devolucaoDatePicker.setOnAction(event -> limparCategorias());
        devolucaoTimePicker.setOnAction(event -> limparCategorias());
    }

    private void limparCategorias() {
        this.categoriaComboBox.getSelectionModel().clearSelection();
        this.categorias.clear();
    }

    private void setConstrains(AnchorPane conteudo) {
        AnchorPane.setBottomAnchor(conteudo, 0.0);
        AnchorPane.setRightAnchor(conteudo, 0.0);
        AnchorPane.setLeftAnchor(conteudo, 0.0);
        AnchorPane.setTopAnchor(conteudo, 0.0);
    }
}
