package com.example.rest_vision.controller.dashboard;

import com.example.rest_vision.controller.commande.Commande;
import com.example.rest_vision.controller.menu.Menu;
import com.example.rest_vision.model.Data;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
public class DashboardController implements Initializable {
    @FXML
    private JFXButton commande;

    @FXML
    private JFXButton logout;

    @FXML
    private JFXButton menu;

    @FXML
    private JFXButton personnel;

    @FXML
    private JFXButton reservation;

    @FXML
    private JFXButton stock;

    @FXML
    private Label user_connected;
    @FXML
    private StackPane contentArea;

    private double x, y=0;
    public void displayUserName(){
        String user = Data.userName;
        user = user.substring(0,1).toUpperCase() + user.substring(1);

        user_connected.setText(user);
    }

    public void logout(){
        try{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deconnexion");
            alert.setHeaderText(null);
            alert.setContentText("Etes-vous sure de vouloir vous deconnecter?");

            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)){
                logout.getScene().getWindow().hide();
                //on rentre sur le login
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/rest_vision/view/login/login.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                Image image = new Image("file:///F:/projet javaFX/rest_vision/src/main/resources/com/example/rest_vision/public/img/utils/icon-1.png");
                stage.getIcons().add(image);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setTitle("Login");

                // ces deux evenements permettent de deplacer l'ihm puisse
                // que la decoration par defaut est desactivee
                scene.setOnMousePressed(event ->{
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                scene.setOnMouseDragged(event ->{
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);
                });

                stage.setScene(scene);
                stage.show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Parent fxml = null;
        try {
            fxml = FXMLLoader.load(getClass().getResource("/com/example/rest_vision/view/report.fxml"));
            contentArea.getChildren().clear();
            contentArea.getChildren().add(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        displayUserName();
//        Commande commande1 = new Commande();
//        commande1.quantiteSpinner();
//        logout();
    }
    @FXML
    public void dashboard(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/com/example/rest_vision/view/report.fxml"));
        contentArea.getChildren().clear();
        contentArea.getChildren().add(fxml);
    }
    @FXML
    public void commande(ActionEvent event) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("/com/example/rest_vision/view/commande/commande.fxml"));
        contentArea.getChildren().clear();
        contentArea.getChildren().add(fxml);
//        Commande commande1 = new Commande();
//        commande1.quantiteSpinner();
    }
    @FXML
    public void menu(ActionEvent event) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("/com/example/rest_vision/view/menu/menu.fxml"));
        contentArea.getChildren().clear();
        contentArea.getChildren().add(fxml);
//        Menu menu1 = new Menu();
////        menu1.affichageMenu();
//        menu1.rechercherMenu();
    }
    @FXML
    public void personnel(ActionEvent event) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("/com/example/rest_vision/view/personnel/personnel.fxml"));
        contentArea.getChildren().clear();
        contentArea.getChildren().add(fxml);
    }
    @FXML
    public void reservation(ActionEvent event) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("/com/example/rest_vision/view/reservation/reservation.fxml"));
        contentArea.getChildren().clear();
        contentArea.getChildren().add(fxml);
    }

    @FXML
    public void stock(ActionEvent event) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("/com/example/rest_vision/view/stock/stock.fxml"));
        contentArea.getChildren().clear();
        contentArea.getChildren().add(fxml);
    }

}