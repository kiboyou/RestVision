package com.example.rest_vision.controller.dashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class DashboardController implements Initializable {
    @FXML
    private StackPane contentArea;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Parent fxml = null;
        try {
            fxml = FXMLLoader.load(getClass().getResource("report.fxml"));
            contentArea.getChildren().clear();
            contentArea.getChildren().add(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    public void dashboard(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("report.fxml"));
        contentArea.getChildren().clear();
        contentArea.getChildren().add(fxml);
    }
    @FXML
    public void commande(ActionEvent event) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("view/commande/commande.fxml"));
        contentArea.getChildren().clear();
        contentArea.getChildren().add(fxml);
    }

    @FXML
    public void menu(ActionEvent event) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("view/menu/menu.fxml"));
        contentArea.getChildren().clear();
        contentArea.getChildren().add(fxml);
    }

    @FXML
    public void personnel(ActionEvent event) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("view/personnel/personnel.fxml"));
        contentArea.getChildren().clear();
        contentArea.getChildren().add(fxml);
    }

    @FXML
    public void reservation(ActionEvent event) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("view/reservation/reservation.fxml"));
        contentArea.getChildren().clear();
        contentArea.getChildren().add(fxml);
    }

    @FXML
    public void stock(ActionEvent event) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("view/stock/stock.fxml"));
        contentArea.getChildren().clear();
        contentArea.getChildren().add(fxml);
    }

    @FXML
    public void logout(ActionEvent event) throws IOException {
        // Code pour gérer la déconnexion
    }
}