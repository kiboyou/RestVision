package com.example.rest_vision.controller.menu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Menu implements Initializable {
    @FXML
    private Button btn_ajouter_menu;

    @FXML
    private Button btn_annuler;

    @FXML
    private Button btn_editer_menu;

    @FXML
    private Button btn_supprimer_menu;

    @FXML
    private TextField id_menu;

    @FXML
    private TableColumn<?, ?> id_menu_col;

    @FXML
    private TableView<?> liste_menu;

    @FXML
    private AnchorPane menu_form;

    @FXML
    private TextField nom_menu;

    @FXML
    private TableColumn<?, ?> nom_menu_col;

    @FXML
    private TableColumn<?, ?> prix_col;

    @FXML
    private TextField prix_menu;

    @FXML
    private TextField recherche_menu;

    @FXML
    private TableColumn<?, ?> status_col;

    @FXML
    private ComboBox<?> status_menu;

    @FXML
    private TableColumn<?, ?> type_col;

    @FXML
    private ComboBox<?> type_menu;
    //menu disponible
    private String status[] = {"Disponible", "Non disponible"};

    public void stautsMenu(){
        List<String> listStatus = new ArrayList<>();
        for (String data: status) {
            listStatus.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listStatus);

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
