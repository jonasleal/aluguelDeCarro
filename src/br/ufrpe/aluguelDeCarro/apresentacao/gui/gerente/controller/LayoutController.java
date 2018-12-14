package br.ufrpe.aluguelDeCarro.apresentacao.gui.gerente.controller;

import br.ufrpe.aluguelDeCarro.fachada.FachadaGerente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Fernando
 */
public class LayoutController implements Initializable {

    @FXML
    private AnchorPane rootLayoutGerente;

    @FXML
    private AnchorPane contentLayoutGerente;

    @FXML
    private ToolBar toolBarLayoutGerente;

    private FXMLLoader loader;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    void irParaAluguel(ActionEvent event) {
        carregarConteudo("../fxml/Aluguel.fxml");
    }

    @FXML
    void irParaCarro(ActionEvent event) {
        carregarConteudo("../fxml/Carro.fxml");
    }

    @FXML
    void irParaCategoria(ActionEvent event) {
        carregarConteudo("../fxml/Categoria.fxml");
    }

    @FXML
    void irParaCliente(ActionEvent event) {
        carregarConteudo("../fxml/Cliente.fxml");
    }

    @FXML
    void irParaManutencao(ActionEvent event) {
        carregarConteudo("../fxml/Manutencao.fxml");
    }

    @FXML
    void irParaReserva(ActionEvent event) {
        carregarConteudo("../fxml/Reserva.fxml");
    }

    @FXML
    void irParaUsuario(ActionEvent event) {
        carregarConteudo("../fxml/Usuario.fxml");
    }

    @FXML
    void irParaLogin(ActionEvent event) {
        new FachadaGerente().logout();
        try {
            loader = new FXMLLoader(getClass().getResource("../../Login.fxml"));
            AnchorPane conteudo = loader.load();
            AnchorPane contentAnchorPaneLayoutGeral = (AnchorPane) rootLayoutGerente.getParent();
            contentAnchorPaneLayoutGeral.getChildren().clear();
            contentAnchorPaneLayoutGeral.getChildren().add(conteudo);
            setConstrains(conteudo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarConteudo(String s) {
        try {
            loader = new FXMLLoader(getClass().getResource(s));
            AnchorPane conteudo = loader.load();
            contentLayoutGerente.getChildren().clear();
            setConstrains(conteudo);
            contentLayoutGerente.getChildren().add(conteudo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setConstrains(AnchorPane conteudo) {
        AnchorPane.setBottomAnchor(conteudo, 0.0);
        AnchorPane.setRightAnchor(conteudo, 0.0);
        AnchorPane.setLeftAnchor(conteudo, 0.0);
        AnchorPane.setTopAnchor(conteudo, 0.0);
    }
}
