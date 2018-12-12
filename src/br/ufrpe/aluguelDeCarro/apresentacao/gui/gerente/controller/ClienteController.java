package br.ufrpe.aluguelDeCarro.apresentacao.gui.gerente.controller;

import br.ufrpe.aluguelDeCarro.excecoes.bancoDeDados.IdNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.FormatoHabilitacaoException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.HabilitacaoObrigatoriException;
import br.ufrpe.aluguelDeCarro.excecoes.pessoa.*;
import br.ufrpe.aluguelDeCarro.fachada.FachadaGerente;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Cliente;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Fernando
 */
public class ClienteController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableView<Cliente> tableView;

    @FXML
    private TableColumn<Cliente, String> cpfColumn;

    @FXML
    private TableColumn<Cliente, String> nomeColumn;

    @FXML
    private JFXTextField cpfTextField;

    @FXML
    private JFXTextField nomeTextField;

    @FXML
    private JFXTextField habilitacaoTextField;

    @FXML
    private JFXDatePicker nascimentoDatePicker;

    @FXML
    private JFXButton deletarButton;

    @FXML
    private JFXButton novoButton;

    @FXML
    private JFXButton salvarButton;

    private ObservableList<Cliente> clientes;

    @FXML
    void deletar(ActionEvent event) {
        Cliente cliente = tableView.getSelectionModel().getSelectedItem();
        try {
            if (cliente != null) {
                FachadaGerente.getInstance().desativarCliente(cliente.getId());
                clientes.remove(cliente);
                mostrarDetalhes(null);
                mostrarTooltip(deletarButton, "Cliente deletado com sucesso");
            } else {
                mostrarTooltip(deletarButton, "Selecione um cliente para deletar");
            }
        } catch (ClienteNaoEncontradoException | IdNaoEncontradoException e) {
            mostrarTooltip(deletarButton, e.getMessage());
        }
    }

    @FXML
    void novo(ActionEvent event) {
        mostrarDetalhes(null);
    }

    @FXML
    void salvar(ActionEvent event) {
        Cliente cliente = lerInputs();
        try {
            FachadaGerente.getInstance().cadastrarCliente(cliente);
            clientes.add(cliente);
            mostrarDetalhes(null);
            mostrarTooltip(salvarButton, "Cliente salvo com sucesso");
        } catch (CpfInvalidoException | CpfObrigatorioException e) {
            mostrarTooltip(cpfTextField, e.getMessage());
        } catch (MenorDeIdadeException idadeExcetion) {
            mostrarTooltip(nascimentoDatePicker, idadeExcetion.getMessage());
        } catch (NomeObrigatorioException e) {
            mostrarTooltip(nomeTextField, e.getMessage());
        } catch (FormatoHabilitacaoException | HabilitacaoObrigatoriException e) {
            mostrarTooltip(habilitacaoTextField, e.getMessage());
        } catch (PessoaInvalidaException | ClienteInvalidoException e) {
            mostrarTooltip(salvarButton, e.getMessage());
        }
    }

    private void mostrarTooltip(Node node, String message) {
        Tooltip tooltip = new Tooltip(message);
        tooltip.setAutoHide(true);
        Bounds bounds = node.localToScreen(node.getBoundsInLocal());
        tooltip.show(node, bounds.getMinX(), bounds.getMaxY());
    }

    private Cliente lerInputs() {
        Cliente cliente = new Cliente();
        cliente.setCpf(cpfTextField.getText());
        cliente.setNome(nomeTextField.getText());
        cliente.setHabilitacao(habilitacaoTextField.getText());
        cliente.setNascimento(nascimentoDatePicker.getValue());
        return cliente;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nomeColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getNome()));
        cpfColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getCpf()));

        clientes = FXCollections.observableArrayList();
        clientes.addAll(FachadaGerente.getInstance().consultarClientes());
        tableView.setItems(clientes);

        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarDetalhes(newValue));
    }

    private void mostrarDetalhes(Cliente cliente) {
        if (cliente != null) {
            cpfTextField.setText(cliente.getCpf());
            nomeTextField.setText(cliente.getNome());
            habilitacaoTextField.setText(cliente.getHabilitacao());
            nascimentoDatePicker.setValue(cliente.getNascimento());
        } else {
            cpfTextField.clear();
            nomeTextField.clear();
            habilitacaoTextField.clear();
            nascimentoDatePicker.setValue(null);
        }
    }
}
