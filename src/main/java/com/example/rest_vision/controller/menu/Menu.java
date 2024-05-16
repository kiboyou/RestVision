package com.example.rest_vision.controller.menu;

import com.example.rest_vision.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    private TableColumn<MenuEntity, String> id_menu_col;

    @FXML
    private TableView<MenuEntity> liste_menu;

    @FXML
    private AnchorPane menu_form;

    @FXML
    private TextField nom_menu;

    @FXML
    private TableColumn<MenuEntity, String> nom_menu_col;

    @FXML
    private TableColumn<MenuEntity, Double> prix_col;

    @FXML
    private TextField prix_menu;

    @FXML
    private TextField recherche_menu;

    @FXML
    private TableColumn<MenuEntity, String> status_col;

    @FXML
    private ComboBox<?> status_menu;

    @FXML
    private TableColumn<MenuEntity, String> type_col;

    @FXML
    private ComboBox<?> type_menu;
    //parametre connexion db
    private Connection connect;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;


    public void ajouterMenu(){
        String sql = "INSERT INTO menu(menu_id, nom, type, prix, status)"
                +" VALUES(?,?,?,?,?)";

        connect = Database.connectDd();
        try{
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, id_menu.getText());
            preparedStatement.setString(2, nom_menu.getText());
            preparedStatement.setString(3, (String) type_menu.getSelectionModel().getSelectedItem());
            preparedStatement.setString(4, prix_menu.getText());
            preparedStatement.setString(5, (String) status_menu.getSelectionModel().getSelectedItem());

            Alert alert;

            if (id_menu.getText().isEmpty()||
                nom_menu.getText().isEmpty()||
                type_menu.getSelectionModel()==null||
                prix_menu.getText().isEmpty()||
                status_menu.getSelectionModel()==null){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs vides");
                alert.showAndWait();
            } else {
                String testMenu = "SELECT menu_id FROM menu WHERE menu_id = '"
                        +id_menu.getText() + "'";

                connect = Database.connectDd();
                assert connect != null;
                statement = connect.createStatement();
                resultSet = statement.executeQuery(testMenu);
                if (resultSet.next()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Menu ID: "+id_menu.getText()+" Est deja cree!");
                    alert.showAndWait();
                }else {
                    preparedStatement.executeUpdate();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information de creation de menu");
                    alert.setHeaderText(null);
                    alert.setContentText("Menu cree avec succes!");
                    alert.showAndWait();

                    affichageMenu();

                    effacerMenu();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<MenuEntity> listeDesMenu(){
        ObservableList listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM menu";

        connect = Database.connectDd();
        try {
            preparedStatement = connect.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            MenuEntity menuEntity;
            while (resultSet.next()){
                menuEntity = new MenuEntity(
                        resultSet.getString("menu_id"),
                        resultSet.getString("nom"),
                        resultSet.getString("type"),
                        resultSet.getDouble("prix"),
                        resultSet.getString("status")
                );
                listData.add(menuEntity);
            }
        }catch (Exception e){e.printStackTrace();}
        return listData;
    }

    private ObservableList<MenuEntity> listMenu;
    public void affichageMenu(){
        listMenu = listeDesMenu();
        id_menu_col.setCellValueFactory(new PropertyValueFactory<>("menuId"));
        nom_menu_col.setCellValueFactory(new PropertyValueFactory<>("nom"));
        type_col.setCellValueFactory(new PropertyValueFactory<>("type"));
        prix_col.setCellValueFactory(new PropertyValueFactory<>("prix"));
        status_col.setCellValueFactory(new PropertyValueFactory<>("status"));

        liste_menu.setItems(listMenu);
    }

    public void editerMenu(){
        String sql = "UPDATE menu SET nom = '"
                +nom_menu.getText()+"', type = '"
                +type_menu.getSelectionModel().getSelectedItem()+"', prix = '"
                +prix_menu.getText()+"', status = '"
                +status_menu.getSelectionModel().getSelectedItem()
                +"' WHERE menu_id = '" +id_menu.getText()+"'";
        connect = Database.connectDd();
        try{
//            statement = connect.createStatement();

            Alert alert;
            if (id_menu.getText().isEmpty()||
                    nom_menu.getText().isEmpty()||
                    type_menu.getSelectionModel()==null||
                    prix_menu.getText().isEmpty()||
                    status_menu.getSelectionModel()==null){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs vides");
                alert.showAndWait();
            }else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message de confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Etes-vous de vouloir mettre a jour le menu d'id: "
                +id_menu.getText()+"?");
//                alert.showAndWait();
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Mise a jour effectuee avec succes!");
                    alert.showAndWait();

                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    affichageMenu();

                    effacerMenu();
                }else{
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Annuler!");
                    alert.showAndWait();
                }
            }
        }catch (Exception e){e.printStackTrace();}
    }
    public void supprimerMenu(){
        String sql = "DELETE FROM menu WHERE menu_id = '"
                +id_menu.getText()+"'";
        connect = Database.connectDd();

        try {
            Alert alert;
            if (id_menu.getText().isEmpty()||
                    nom_menu.getText().isEmpty()||
                    type_menu.getSelectionModel()==null||
                    prix_menu.getText().isEmpty()||
                    status_menu.getSelectionModel()==null){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs vides");
                alert.showAndWait();
            }else {
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Message d'avertissement'");
                alert.setHeaderText(null);
                alert.setContentText("Etes-vous de vouloir supprimer le menu d'id: "
                        +id_menu.getText()+"?");
//                alert.showAndWait();
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Supprission effectuee avec succes!");
                    alert.showAndWait();

                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    affichageMenu();

                    effacerMenu();
                }else{
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Annuler!");
                    alert.showAndWait();
                }
            }
        }catch (Exception e){e.printStackTrace();}
    }
    public void effacerMenu(){
        id_menu.setText("");
        nom_menu.setText("");
        type_menu.getSelectionModel().clearSelection();
        prix_menu.setText("");
        status_menu.getSelectionModel().clearSelection();
    }
    public void rechercherMenu() {
        FilteredList<MenuEntity> filter = new FilteredList<>(listMenu, e -> true);

        recherche_menu.textProperty().addListener((observable, oldValue, newValue) -> {
            String searchKey = newValue.toLowerCase().trim();
            System.out.println("Texte de recherche modifié : " + newValue);
            filter.setPredicate(menuEntity -> {
                if (newValue.isEmpty()) {
                    return true; // Afficher tous les éléments si le champ de recherche est vide
                }

                // Vérifier si le menu correspond au critère de recherche
                return menuEntity.getMenuId().toLowerCase().contains(searchKey)
                        || menuEntity.getNom().toLowerCase().contains(searchKey)
                        || menuEntity.getType().toLowerCase().contains(searchKey)
                        || menuEntity.getStatus().toLowerCase().contains(searchKey)
                        || String.valueOf(menuEntity.getPrix()).contains(searchKey);
            });
        });

        // Créer une liste triée à partir du filtre
        SortedList<MenuEntity> sortedList = new SortedList<>(filter);
        sortedList.comparatorProperty().bind(liste_menu.comparatorProperty());

        // Mettre à jour la liste affichée dans votre contrôle de liste (liste_menu)
        liste_menu.setItems(sortedList);
        liste_menu.refresh();
    }

    public void selectionnerUnMenu(){
        MenuEntity menuEntity = liste_menu.getSelectionModel().getSelectedItem();
        int num = liste_menu.getSelectionModel().getSelectedIndex();

        if ((num-1)< -1){
            return;
        }
        id_menu.setText(menuEntity.getMenuId());
        nom_menu.setText(menuEntity.getNom());
        prix_menu.setText(String.valueOf(menuEntity.getPrix()));

    }

    //menu disponible
    private String status[] = {"Disponible", "Non disponible"};
    private String categories[] = {"Entrée", "Plat Principal", "Accompagnement", "Dessert"};

    public void categorieMenu(){
        List<String> listCategories = new ArrayList<>();

        for (String data: categories) {
            listCategories.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listCategories);
        type_menu.setItems(listData);
    }
    public void statusMenu(){//affiche status du menu
        List<String> listStatus = new ArrayList<>();
        for (String data: status) {
            listStatus.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listStatus);
        status_menu.setItems(listData);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        id_menu_col.setCellValueFactory(new PropertyValueFactory<>("menuId"));
//        nom_menu_col.setCellValueFactory(new PropertyValueFactory<>("nom"));
//        type_col.setCellValueFactory(new PropertyValueFactory<>("type"));
//        prix_col.setCellValueFactory(new PropertyValueFactory<>("prix"));
//        status_col.setCellValueFactory(new PropertyValueFactory<>("status"));
//        listMenu = listeDesMenu();
//        liste_menu.setItems(listMenu);

        statusMenu();
        categorieMenu();
        affichageMenu();
        rechercherMenu();
    }
}
