package br.ufrpe.aluguelDeCarro.apresentacao.gui.usuario.controller;

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

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Fernando
 */
public class ClienteController implements Initializable {

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
    private JFXTextField ruaTextField;

    @FXML
    private JFXTextField complementoTextField;

    @FXML
    private JFXTextField numeroTextField;

    @FXML
    private JFXTextField cidadeTextField;

    @FXML
    private JFXTextField estadoTextField;

    @FXML
    private JFXTextField emailTextField;

    @FXML
    private JFXTextField telefoneTextField;


    @FXML
    private JFXButton novoButton;

    @FXML
    private JFXButton salvarButton;

    private ObservableList<Cliente> clientes;
    private FachadaGerente fachada = new FachadaGerente();

    @FXML
    void novo(ActionEvent event) {
        limparSelecaoTabela();
    }

    @FXML
    void salvar(ActionEvent event) {
        if (validarCampo()) {
            Cliente cliente = tableView.getSelectionModel().getSelectedItem();
            if (cliente == null) cadastrar();
            else alterar(cliente);
        }
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
        } catch (RuaObrigatorioException e) {
            ruaTextField.requestFocus();
            ViewUtil.mostrarTooltip(ruaTextField, e.getMessage());
        } catch (CidadeObrigatorioException e) {
            cidadeTextField.requestFocus();
            ViewUtil.mostrarTooltip(cidadeTextField, e.getMessage());
        } catch (EstadoObrigatorioException e) {
            estadoTextField.requestFocus();
            ViewUtil.mostrarTooltip(estadoTextField, e.getMessage());
        } catch (EmailObrigatorioException e) {
            emailTextField.requestFocus();
            ViewUtil.mostrarTooltip(emailTextField, e.getMessage());
        } catch (TelefoneObrigatorioException e) {
            telefoneTextField.requestFocus();
            ViewUtil.mostrarTooltip(telefoneTextField, e.getMessage());
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
        } catch (RuaObrigatorioException e) {
            ruaTextField.requestFocus();
            ViewUtil.mostrarTooltip(ruaTextField, e.getMessage());
        } catch (CidadeObrigatorioException e) {
            cidadeTextField.requestFocus();
            ViewUtil.mostrarTooltip(cidadeTextField, e.getMessage());
        } catch (EstadoObrigatorioException e) {
            estadoTextField.requestFocus();
            ViewUtil.mostrarTooltip(estadoTextField, e.getMessage());
        } catch (EmailObrigatorioException e) {
            emailTextField.requestFocus();
            ViewUtil.mostrarTooltip(emailTextField, e.getMessage());
        } catch (TelefoneObrigatorioException e) {
            telefoneTextField.requestFocus();
            ViewUtil.mostrarTooltip(telefoneTextField, e.getMessage());
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
        cliente.setEmail(emailTextField.getText());
        cliente.setTelefone(telefoneTextField.getText());
        cliente.setRua(ruaTextField.getText());
        cliente.setComplemento(complementoTextField.getText());
        if (numeroTextField.getText() != null && !numeroTextField.getText().isEmpty())
            cliente.setNumero(Integer.parseInt(numeroTextField.getText().trim()));
        cliente.setCidade(cidadeTextField.getText());
        cliente.setEstado(estadoTextField.getText());
    }

    private boolean validarCampo() {
        if (numeroTextField.getText() != null && !numeroTextField.getText().isEmpty()) {
            if (!numeroTextField.getText().matches("\\d+")) {
                numeroTextField.requestFocus();
                ViewUtil.mostrarTooltip(numeroTextField, "Valor inválido");
                return false;
            } else {
                return true;
            }
        }
        return true;
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
            ruaTextField.setText(cliente.getRua());
            complementoTextField.setText(cliente.getComplemento());
            numeroTextField.setText(String.valueOf(cliente.getNumero()));
            cidadeTextField.setText(cliente.getCidade());
            estadoTextField.setText(cliente.getEstado());
            emailTextField.setText(cliente.getEmail());
            telefoneTextField.setText(cliente.getTelefone());
        } else {
            cpfTextField.clear();
            nomeTextField.clear();
            habilitacaoTextField.clear();
            nascimentoDatePicker.setValue(null);
            ruaTextField.clear();
            complementoTextField.clear();
            numeroTextField.clear();
            cidadeTextField.clear();
            estadoTextField.clear();
            emailTextField.clear();
            telefoneTextField.clear();
        }
    }
}
