package com.example.rest_vision;

import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {
    @FXML
    private TextField answer;

    @FXML
    private JFXButton createBtn;

    @FXML
    private AnchorPane createForm;

    @FXML
    private Hyperlink forgotPwd;

    @FXML
    private TextField login;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private TextField loginCreate;

    @FXML
    private AnchorPane loginForm;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField passwordCreate;

    @FXML
    private ComboBox<?> question;

    @FXML
    private JFXButton sideCreateBtn;

    @FXML
    private AnchorPane sideForm;

    @FXML
    private JFXButton switchBtn;

    public void switchForm(ActionEvent actionEvent){
        TranslateTransition slide = new TranslateTransition();
        if (actionEvent.getSource() == sideCreateBtn){
            slide.setNode(sideForm);
            slide.setToX(300);
            slide.setDuration(Duration.seconds(.5));

            slide.setOnFinished((ActionEvent e) ->{
                switchBtn.setVisible(true);
                sideCreateBtn.setVisible(false);
            });

            slide.play();
        } else if (actionEvent.getSource() == switchBtn) {
            slide.setNode(sideForm);
            slide.setToX(0);
            slide.setDuration(Duration.seconds(.5));

            slide.setOnFinished((ActionEvent e) ->{
                switchBtn.setVisible(false);
                sideCreateBtn.setVisible(true);
            });

            slide.play();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
