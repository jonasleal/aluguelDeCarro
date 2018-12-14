package br.ufrpe.aluguelDeCarro.apresentacao.gui.gerente.controller;

import br.ufrpe.aluguelDeCarro.excecoes.cliente.*;
import br.ufrpe.aluguelDeCarro.excecoes.pessoa.*;
import br.ufrpe.aluguelDeCarro.fachada.FachadaGerente;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Cliente;
import br.ufrpe.aluguelDeCarro.servicos.ViewUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private FachadaGerente fachada = new FachadaGerente();

    @FXML
    void deletar(ActionEvent event) {
        Cliente cliente = tableView.getSelectionModel().getSelectedItem();
        try {
            if (cliente != null) {
                fachada.desativarCliente(cliente.getId());
                clientes.remove(cliente);
                limparSelecaoTabela();
                ViewUtil.mostrarTooltip(deletarButton, "Cliente deletado com sucesso");
            } else {
                tableView.requestFocus();
                ViewUtil.mostrarTooltip(deletarButton, "Selecione um cliente para deletar");
            }
        } catch (ClienteNaoEncontradoException e) {
            ViewUtil.mostrarTooltip(deletarButton, e.getMessage());
        }
    }

    @FXML
    void novo(ActionEvent event) {
        limparSelecaoTabela();
    }

    @FXML
    void salvar(ActionEvent event) {
        Cliente cliente = tableView.getSelectionModel().getSelectedItem();
        if (cliente == null) cadastrar();
        else alterar(cliente);
    }

    private void alterar(Cliente cliente) {
        try {
            lerInputs(cliente);
            fachada.alterarCliente(cliente);
            limparSelecaoTabela();
            clientes.set(clientes.indexOf(cliente), cliente);
            ViewUtil.mostrarTooltip(salvarButton, "Cliente alterado com sucesso");
        } catch (CpfInvalidoException | CpfObrigatorioException e) {
            cpfTextField.requestFocus();
            ViewUtil.mostrarTooltip(cpfTextField, e.getMessage());
        } catch (DataNascimentoObrigatorioException | MenorDeIdadeException e) {
            nascimentoDatePicker.requestFocus();
            ViewUtil.mostrarTooltip(nascimentoDatePicker, e.getMessage());
        } catch (NomeObrigatorioException | FormatoNomeException e) {
            nomeTextField.requestFocus();
            ViewUtil.mostrarTooltip(nomeTextField, e.getMessage());
        } catch (FormatoHabilitacaoException | HabilitacaoObrigatoriException e) {
            habilitacaoTextField.requestFocus();
            ViewUtil.mostrarTooltip(habilitacaoTextField, e.getMessage());
        } catch (PessoaInvalidaException | ClienteInvalidoException e) {
            ViewUtil.mostrarTooltip(salvarButton, e.getMessage());
        }
    }

    private void cadastrar() {
        try {
            Cliente cliente = new Cliente();
            lerInputs(cliente);
            fachada.cadastrarCliente(cliente);
            clientes.add(cliente);
            limparSelecaoTabela();
            ViewUtil.mostrarTooltip(salvarButton, "Cliente salvo com sucesso");
        } catch (CpfInvalidoException | CpfObrigatorioException | ClienteJaCadastradoException e) {
            cpfTextField.requestFocus();
            ViewUtil.mostrarTooltip(cpfTextField, e.getMessage());
        } catch (DataNascimentoObrigatorioException | MenorDeIdadeException e) {
            nascimentoDatePicker.requestFocus();
            ViewUtil.mostrarTooltip(nascimentoDatePicker, e.getMessage());
        } catch (NomeObrigatorioException | FormatoNomeException e) {
            nomeTextField.requestFocus();
            ViewUtil.mostrarTooltip(nomeTextField, e.getMessage());
        } catch (FormatoHabilitacaoException | HabilitacaoObrigatoriException e) {
            habilitacaoTextField.requestFocus();
            ViewUtil.mostrarTooltip(habilitacaoTextField, e.getMessage());
        } catch (PessoaInvalidaException | ClienteInvalidoException e) {
            ViewUtil.mostrarTooltip(salvarButton, e.getMessage());
        }
    }

    private void limparSelecaoTabela() {
        tableView.getSelectionModel().clearSelection();
        mostrarDetalhes(null);
    }

    private void lerInputs(Cliente cliente) {
        cliente.setCpf(cpfTextField.getText());
        cliente.setNome(nomeTextField.getText());
        cliente.setHabilitacao(habilitacaoTextField.getText());
        cliente.setNascimento(nascimentoDatePicker.getValue());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nomeColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getNome()));
        cpfColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getCpf()));

        clientes = FXCollections.observableArrayList();
        clientes.addAll(fachada.consultarClientes());
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
