package br.ufrpe.aluguelDeCarro.apresentacao.gui.gerente.controller;

import br.ufrpe.aluguelDeCarro.excecoes.CategoriaNaoEncontradaException;
import br.ufrpe.aluguelDeCarro.excecoes.categoria.CategoriaInvalidaException;
import br.ufrpe.aluguelDeCarro.excecoes.categoria.CategoriaJaCadastradaException;
import br.ufrpe.aluguelDeCarro.excecoes.categoria.NomeCategoriaObrigatorioException;
import br.ufrpe.aluguelDeCarro.excecoes.categoria.PrecoNegativoException;
import br.ufrpe.aluguelDeCarro.fachada.FachadaGerente;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Categoria;
import br.ufrpe.aluguelDeCarro.servicos.ViewUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import java.util.ResourceBundle;

/**
 * @author Fernando
 */
public class CategoriaController implements Initializable {
    @FXML
    private TableView<Categoria> tableView;

    @FXML
    private TableColumn<Categoria, String> nomeColumn;

    @FXML
    private TableColumn<Categoria, String> precoColumn;

    @FXML
    private JFXTextField nomeTextField;

    @FXML
    private JFXTextField precoTextField;

    @FXML
    private JFXButton deletarButton;

    @FXML
    private JFXButton novoButton;

    @FXML
    private JFXButton salvarButton;

    private ObservableList<Categoria> categorias;
    private FachadaGerente fachada = new FachadaGerente();

    @FXML
    void deletar(ActionEvent event) {
        Categoria categoria = tableView.getSelectionModel().getSelectedItem();
        if (categoria != null) {
            try {
                fachada.desativarCategoria(categoria.getId());
                categorias.remove(categoria);
                mostrarDetalhes(null);
                ViewUtil.mostrarTooltip(deletarButton, "Categoria deletada com sucesso");
            } catch (CategoriaNaoEncontradaException e) {
                ViewUtil.mostrarTooltip(deletarButton, "Categoria não encontrada");
            }
        }
    }

    @FXML
    void novo(ActionEvent event) {
        tableView.getSelectionModel().clearSelection();
        mostrarDetalhes(null);
    }

    @FXML
    void salvar(ActionEvent event) {
        if (validarCampo()) {
            Categoria categoria = lerInputs();
            try {
                fachada.cadastrarCategoria(categoria);
                categorias.add(categoria);
                mostrarDetalhes(null);
                ViewUtil.mostrarTooltip(salvarButton, "Categoria salva com sucesso");
            } catch (NomeCategoriaObrigatorioException | CategoriaJaCadastradaException e) {
                nomeTextField.requestFocus();
                ViewUtil.mostrarTooltip(nomeTextField, e.getMessage());
            } catch (PrecoNegativoException e) {
                precoTextField.requestFocus();
                ViewUtil.mostrarTooltip(precoTextField, e.getMessage());
            } catch (CategoriaInvalidaException e) {
                ViewUtil.mostrarTooltip(salvarButton, e.getMessage());
            }
        }
    }

    private boolean validarCampo() {
        if (!precoTextField.getText().matches("^\\d+(\\.\\d+)?")) {
            precoTextField.requestFocus();
            ViewUtil.mostrarTooltip(precoTextField, "Valor inválido");
            return false;
        } else{
            return true;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nomeColumn.setCellValueFactory(param -> new SimpleStringProperty(
                param.getValue() != null ? param.getValue().getNome() : ""));
        precoColumn.setCellValueFactory(param -> new SimpleStringProperty(
                param.getValue() != null ? String.valueOf(param.getValue().getDiaria()) : ""));
        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarDetalhes(newValue));

        categorias = FXCollections.observableArrayList();
        categorias.addAll(fachada.consultarCategorias());
        tableView.setItems(categorias);
    }

    private void mostrarDetalhes(Categoria categoria) {
        if (categoria != null) {
            nomeTextField.setText(categoria.getNome());
            precoTextField.setText(String.valueOf(categoria.getDiaria()));
        } else {
            nomeTextField.clear();
            precoTextField.clear();
        }
    }

    private Categoria lerInputs() {
        Categoria categoria = new Categoria();
        categoria.setNome(nomeTextField.getText());
        categoria.setDiaria(new BigDecimal(precoTextField.getText()));
        return categoria;
    }
}
