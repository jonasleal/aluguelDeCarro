package br.ufrpe.aluguelDeCarro.apresentacao.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Fernando
 */
public class LayoutController implements Initializable {
    @FXML
    private StackPane rootStackPaneLayoutGeral;

    @FXML
    private AnchorPane contentAnchorPaneLayoutGeral;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            contentAnchorPaneLayoutGeral.getChildren().clear();
            AnchorPane cont = loader.load();
            contentAnchorPaneLayoutGeral.getChildren().add(cont);
            setConstrains(cont);

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
