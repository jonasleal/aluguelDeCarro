package br.ufrpe.aluguelDeCarro.apresentacao.gui;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Fernando
 */
public class LayoutView implements Initializable {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private AnchorPane content;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Menu.fxml"));
            AnchorPane menu = loader.load();
            MenuView controller = loader.getController();
            controller.setContent(content);
            drawer.setSidePane(menu);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HamburgerBasicCloseTransition basicCloseTransition = new HamburgerBasicCloseTransition(hamburger);
        basicCloseTransition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            basicCloseTransition.setRate(basicCloseTransition.getRate() * -1);
            basicCloseTransition.play();
            if (drawer.isOpened()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });
        drawer.setOnDrawerClosed(event -> drawer.toBack());
        drawer.setOnDrawerOpened(event -> {
            drawer.toFront();
            hamburger.toFront();
        });
    }
}
