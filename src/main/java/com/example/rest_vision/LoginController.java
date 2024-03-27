package com.example.rest_vision;

import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField answer;
    @FXML
    private Label close;
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

//    private Connection connect;


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
        close.setOnMouseClicked(e->{
            System.exit(0);
        });
    }
}
