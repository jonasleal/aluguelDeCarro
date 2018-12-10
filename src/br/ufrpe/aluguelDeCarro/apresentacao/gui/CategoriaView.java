package br.ufrpe.aluguelDeCarro.apresentacao.gui;

import br.ufrpe.aluguelDeCarro.dados.entidades.Categoria;
import br.ufrpe.aluguelDeCarro.excecoes.CategoriaNaoEncontradaException;
import br.ufrpe.aluguelDeCarro.servicos.Singleton;
import com.jfoenix.controls.JFXButton;
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

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Fernando
 */
public class CategoriaView implements Initializable {
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

    @FXML
    void deletar(ActionEvent event) {
        Categoria categoria = tableView.getSelectionModel().getSelectedItem();
        if (categoria != null) {
            try {
                Singleton.getInstance().getCategoriaNegocio().desativar(categoria.getId());
                categorias.remove(categoria);
                mostrarDetalhes(null);
                mostrarTooltip(deletarButton, "Categoria deletada com sucesso");
            } catch (CategoriaNaoEncontradaException e) {
                mostrarTooltip(deletarButton, "Categoria não encontrada");
            }
        }
    }

    @FXML
    void novo(ActionEvent event) {
        mostrarDetalhes(null);
    }

    @FXML
    void salvar(ActionEvent event) {
        if (validarCampo()) {
            Categoria categoria = lerInputs();
            Singleton.getInstance().getCategoriaNegocio().cadastrar(categoria);
            categorias.add(categoria);
            mostrarDetalhes(null);
            mostrarTooltip(salvarButton, "Categoria salva com sucesso");
        }
    }

    private boolean validarCampo() {
        if (!precoTextField.getText().matches("^\\d+(\\.\\d+)?")) {
            mostrarTooltip(precoTextField, "Valor inválido");
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
        categorias.addAll(Singleton.getInstance().getCategoriaNegocio().consultarTodos());
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

    private void mostrarTooltip(Node node, String message) {
        Tooltip tooltip = new Tooltip(message);
        tooltip.setAutoHide(true);
        Bounds bounds = node.localToScreen(node.getBoundsInLocal());
        tooltip.show(node, bounds.getMinX(), bounds.getMaxY());
    }
}
