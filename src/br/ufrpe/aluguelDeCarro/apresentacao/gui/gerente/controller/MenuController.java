package br.ufrpe.aluguelDeCarro.apresentacao.gui.gerente.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Fernando
 */
public class MenuController implements Initializable {

    private AnchorPane content;
    private FXMLLoader loader;

    @FXML
    void irParaAluguel(ActionEvent event) {
        carregarConteudo("../fxml/Aluguel.fxml");
    }

    private void setConstrains(AnchorPane conteudo) {
        AnchorPane.setBottomAnchor(conteudo, 0.0);
        AnchorPane.setRightAnchor(conteudo, 0.0);
        AnchorPane.setLeftAnchor(conteudo, 0.0);
        AnchorPane.setTopAnchor(conteudo, 0.0);
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

    private void carregarConteudo(String s) {
        try {
            loader = new FXMLLoader(getClass().getResource(s));
            content.getChildren().clear();
            AnchorPane conteudo = loader.load();
            setConstrains(conteudo);
            content.getChildren().add(conteudo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setContent(AnchorPane content) {
        this.content = content;
    }
}
