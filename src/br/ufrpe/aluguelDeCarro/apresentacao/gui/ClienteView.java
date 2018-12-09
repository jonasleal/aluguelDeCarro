package br.ufrpe.aluguelDeCarro.apresentacao.gui;

import br.ufrpe.aluguelDeCarro.dados.entidades.Cliente;
import br.ufrpe.aluguelDeCarro.excecoes.*;
import br.ufrpe.aluguelDeCarro.servicos.Singleton;
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
public class ClienteView implements Initializable {

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
        Cliente selectedItem = tableView.getSelectionModel().getSelectedItem();
        try {
            if (selectedItem != null) {
                Singleton.getInstance().getClienteNegocio().desativar(selectedItem.getId());
                clientes.remove(selectedItem);
                mostrarDetalhes(null);
            } else {
                mostrarTooltip(deletarButton, "Selecione um cliente para deletar");
            }
        } catch (ClienteNaoEncontradoException e) {
            mostrarTooltip(deletarButton, e.getMessage());
        }
    }

    @FXML
    void novo(ActionEvent event) {
        mostrarDetalhes(new Cliente());
    }

    @FXML
    void salvar(ActionEvent event) {
        Cliente cliente = lerInputs();
        try {
            Singleton.getInstance().getClienteNegocio().cadastrar(cliente);
            clientes.add(cliente);
            mostrarDetalhes(null);
            mostrarTooltip(salvarButton, "Cliente salvo com sucesso");
        } catch (CpfException e) {
            mostrarTooltip(cpfTextField, e.getMessage());
        } catch (IdadeExcetion idadeExcetion) {
            mostrarTooltip(nascimentoDatePicker, idadeExcetion.getMessage());
        } catch (NomeException e) {
            mostrarTooltip(nomeTextField, e.getMessage());
        } catch (HabilitacaoException e) {
            mostrarTooltip(habilitacaoTextField, e.getMessage());
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
        nomeColumn.setCellValueFactory(
                value -> new SimpleStringProperty(value.getValue() != null ? value.getValue().getNome() : ""));
        cpfColumn.setCellValueFactory(
                value -> new SimpleStringProperty(value.getValue() != null ? value.getValue().getCpf() : ""));

        clientes = FXCollections.observableArrayList();
        clientes.addAll(Singleton.getInstance().getClienteNegocio().consultarTodos());
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
