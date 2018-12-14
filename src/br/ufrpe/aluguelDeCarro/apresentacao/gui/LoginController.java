package br.ufrpe.aluguelDeCarro.apresentacao.gui;

import br.ufrpe.aluguelDeCarro.excecoes.usuario.SenhaIncorretaException;
import br.ufrpe.aluguelDeCarro.excecoes.usuario.UsuarioInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.usuario.UsuarioNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.fachada.FachadaGerente;
import br.ufrpe.aluguelDeCarro.servicos.Criptografia;
import br.ufrpe.aluguelDeCarro.servicos.ViewUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
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
public class LoginController implements Initializable {

    @FXML
    private AnchorPane rootLogin;

    @FXML
    private JFXTextField cpfTextField;

    @FXML
    private JFXPasswordField senhaPasswordField;

    @FXML
    private JFXButton loginButton;

    private FachadaGerente fachada = new FachadaGerente();

    @FXML
    void login(ActionEvent event) {
        String cpf = cpfTextField.getText();
        String senha = Criptografia.criptografarSenha(senhaPasswordField.getText());
        try {
            fachada.login(cpf, senha);
            direcionarTelaAdequada();
        } catch (UsuarioNaoEncontradoException e) {
            cpfTextField.requestFocus();
            ViewUtil.mostrarTooltip(cpfTextField, e.getMessage());
        } catch (SenhaIncorretaException e) {
            senhaPasswordField.requestFocus();
            ViewUtil.mostrarTooltip(senhaPasswordField, e.getMessage());
        } catch (UsuarioInvalidoException e) {
            ViewUtil.mostrarTooltip(loginButton, e.getMessage());
        }
    }

    private void direcionarTelaAdequada() {
        if (fachada.getUsuarioLogado().isGerente()) {
            try {
                AnchorPane contentAnchorPaneLayoutGeral = (AnchorPane) rootLogin.getParent();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("gerente/fxml/Layout.fxml"));
                AnchorPane layout = loader.load();
                contentAnchorPaneLayoutGeral.getChildren().clear();
                setConstrains(layout);
                contentAnchorPaneLayoutGeral.getChildren().add(layout);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                AnchorPane contentAnchorPaneLayoutGeral = (AnchorPane) rootLogin.getParent();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("usuario/fxml/Layout.fxml"));
                AnchorPane layout = loader.load();
                contentAnchorPaneLayoutGeral.getChildren().clear();
                setConstrains(layout);
                contentAnchorPaneLayoutGeral.getChildren().add(layout);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setConstrains(AnchorPane conteudo) {
        AnchorPane.setBottomAnchor(conteudo, 0.0);
        AnchorPane.setRightAnchor(conteudo, 0.0);
        AnchorPane.setLeftAnchor(conteudo, 0.0);
        AnchorPane.setTopAnchor(conteudo, 0.0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
