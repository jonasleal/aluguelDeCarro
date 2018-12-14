package br.ufrpe.aluguelDeCarro.apresentacao.gui.gerente.controller;

import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.pessoa.*;
import br.ufrpe.aluguelDeCarro.excecoes.usuario.SenhaObrigatoriaException;
import br.ufrpe.aluguelDeCarro.excecoes.usuario.UsuarioInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.usuario.UsuarioJaCadastradoException;
import br.ufrpe.aluguelDeCarro.fachada.FachadaGerente;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Usuario;
import br.ufrpe.aluguelDeCarro.servicos.Criptografia;
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

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Fernando
 */
public class UsuarioController implements Initializable {
    @FXML
    private TableView<Usuario> tableView;

    @FXML
    private TableColumn<Usuario, String> cpfColumn;

    @FXML
    private TableColumn<Usuario, String> nomeColumn;

    @FXML
    private JFXTextField cpfTextField;

    @FXML
    private JFXTextField nomeTextField;

    @FXML
    private JFXPasswordField senhaPasswordField;

    @FXML
    private JFXDatePicker nascimentoDatePicker;

    @FXML
    private JFXCheckBox gerenteCheckBox;

    @FXML
    private JFXButton novoButton;

    @FXML
    private JFXButton salvarButton;

    private ObservableList<Usuario> usuarios;
    private FachadaGerente fachada = new FachadaGerente();

    @FXML
    void novo(ActionEvent event) {
        limparSelecaoTabela();
    }

    @FXML
    void salvar(ActionEvent event) {
        Usuario usuario = tableView.getSelectionModel().getSelectedItem();
        if (usuario == null) cadastrar();
        else alterar(usuario);
    }

    private void alterar(Usuario usuario) {
        try {
            lerInputs(usuario);
            fachada.alterarUsuario(usuario);
            limparSelecaoTabela();
            usuarios.set(usuarios.indexOf(usuario), usuario);
            ViewUtil.mostrarTooltip(salvarButton, "Usuário alterado com sucesso");
        } catch (CpfInvalidoException | CpfObrigatorioException e) {
            cpfTextField.requestFocus();
            ViewUtil.mostrarTooltip(cpfTextField, e.getMessage());
        } catch (DataNascimentoObrigatorioException | MenorDeIdadeException e) {
            nascimentoDatePicker.requestFocus();
            ViewUtil.mostrarTooltip(nascimentoDatePicker, e.getMessage());
        } catch (NomeObrigatorioException | FormatoNomeException e) {
            nomeTextField.requestFocus();
            ViewUtil.mostrarTooltip(nomeTextField, e.getMessage());
        } catch (SenhaObrigatoriaException e) {
            senhaPasswordField.requestFocus();
            ViewUtil.mostrarTooltip(senhaPasswordField, e.getMessage());
        } catch (PessoaInvalidaException | UsuarioInvalidoException | ClienteInvalidoException e) {
            salvarButton.requestFocus();
            ViewUtil.mostrarTooltip(salvarButton, e.getMessage());
        }
    }

    private void cadastrar() {
        try {
            Usuario usuario = new Usuario();
            lerInputs(usuario);
            fachada.cadastrarUsuario(usuario);
            usuarios.add(usuario);
            limparSelecaoTabela();
            ViewUtil.mostrarTooltip(salvarButton, "Usuário salvo com sucesso");
        } catch (CpfInvalidoException | CpfObrigatorioException | UsuarioJaCadastradoException e) {
            cpfTextField.requestFocus();
            ViewUtil.mostrarTooltip(cpfTextField, e.getMessage());
        } catch (DataNascimentoObrigatorioException | MenorDeIdadeException e) {
            nascimentoDatePicker.requestFocus();
            ViewUtil.mostrarTooltip(nascimentoDatePicker, e.getMessage());
        } catch (NomeObrigatorioException | FormatoNomeException e) {
            nomeTextField.requestFocus();
            ViewUtil.mostrarTooltip(nomeTextField, e.getMessage());
        } catch (SenhaObrigatoriaException e) {
            senhaPasswordField.requestFocus();
            ViewUtil.mostrarTooltip(senhaPasswordField, e.getMessage());
        } catch (PessoaInvalidaException | UsuarioInvalidoException | ClienteInvalidoException e) {
            ViewUtil.mostrarTooltip(salvarButton, e.getMessage());
        }
    }

    private void limparSelecaoTabela() {
        tableView.getSelectionModel().clearSelection();
        mostrarDetalhes(null);
    }

    private void lerInputs(Usuario usuario) {
        usuario.setCpf(cpfTextField.getText());
        usuario.setNome(nomeTextField.getText());
        usuario.setSenha(Criptografia.criptografarSenha(senhaPasswordField.getText()));
        usuario.setGerente(gerenteCheckBox.isSelected());
        usuario.setNascimento(nascimentoDatePicker.getValue());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nomeColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getNome()));
        cpfColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getCpf()));

        usuarios = FXCollections.observableArrayList();
        usuarios.addAll(fachada.consultarUsuarios());
        tableView.setItems(usuarios);

        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarDetalhes(newValue));
    }

    private void mostrarDetalhes(Usuario usuario) {
        if (usuario != null) {
            cpfTextField.setText(usuario.getCpf());
            nomeTextField.setText(usuario.getNome());
            gerenteCheckBox.setSelected(usuario.isGerente());
            senhaPasswordField.clear();
            nascimentoDatePicker.setValue(usuario.getNascimento());
        } else {
            cpfTextField.clear();
            nomeTextField.clear();
            gerenteCheckBox.setSelected(false);
            senhaPasswordField.clear();
            nascimentoDatePicker.setValue(null);
        }
    }
}
