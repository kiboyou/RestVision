package com.example.rest_vision.controller.login;

import com.example.rest_vision.Database;
import com.example.rest_vision.model.Data;
import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    //parametre connection db
    private Connection connect;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public void login(){
        String query = "SELECT * FROM users WHERE login = ? AND password = ?";
        connect = Database.connectDd();
        try{
            assert connect != null;
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, login.getText());
            preparedStatement.setString(2, password.getText());

            resultSet = preparedStatement.executeQuery();

            Alert alert;

            if (login.getText().isEmpty() || password.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de connexion");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs vides");
                alert.showAndWait();
            }else {
                if (resultSet.next()){
                    Data.userName = login.getText();
//                    alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Information de connexion");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Connexion reussie!");
//                    alert.show();

                    //cacher le login
                    loginBtn.getScene().getWindow().hide();
                    //liaison du dashboard
                    Parent root = FXMLLoader.load(getClass().getResource("/com/example/rest_vision/view/dashboard.fxml"));

                    Image image = new Image("file:///F:/projet javaFX/rest_vision/src/main/resources/com/example/rest_vision/public/img/utils/icon-1.png");

                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    stage.getIcons().add(image);
                    stage.setTitle("Dashboard");
                    stage.setScene(scene);
                    stage.show();
                }else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur de connexion");
                    alert.setHeaderText(null);
                    alert.setContentText("Login ou Mot de passe incorrect");
                    alert.showAndWait();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

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
