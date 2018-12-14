package br.ufrpe.aluguelDeCarro.apresentacao.gui.usuario.controller;

import br.ufrpe.aluguelDeCarro.apresentacao.gui.gerente.controller.MenuController;
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
    private ToolBar toolBar;
    @FXML
    private AnchorPane content;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/Menu.fxml"));
            AnchorPane menu = loader.load();
            MenuController controller = loader.getController();
            controller.setContent(content);
            toolBar.getItems().addAll(menu.getChildren());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
