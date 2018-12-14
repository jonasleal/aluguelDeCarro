package br.ufrpe.aluguelDeCarro.apresentacao.gui.usuario.controller;

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
        try {
            loader = new FXMLLoader(getClass().getResource("../fxml/Aluguel.fxml"));
            content.getChildren().clear();
            AnchorPane conteudo = loader.load();
            setConstrains(conteudo);
            content.getChildren().add(conteudo);
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

    @FXML
    void irParaCarro(ActionEvent event) {
        try {
            loader = new FXMLLoader(getClass().getResource("../fxml/Carro.fxml"));
            content.getChildren().clear();
            AnchorPane conteudo = loader.load();
            setConstrains(conteudo);
            content.getChildren().add(conteudo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irParaCliente(ActionEvent event) {
        try {
            loader = new FXMLLoader(getClass().getResource("../fxml/Cliente.fxml"));
            content.getChildren().clear();
            AnchorPane conteudo = loader.load();
            setConstrains(conteudo);
            content.getChildren().add(conteudo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irParaReserva(ActionEvent event) {
        try {
            loader = new FXMLLoader(getClass().getResource("../fxml/Reserva.fxml"));
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
