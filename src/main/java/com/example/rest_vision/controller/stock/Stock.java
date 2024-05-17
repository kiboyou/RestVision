package com.example.rest_vision.controller.stock;
import com.example.rest_vision.model.stock.Stockmodel;


import java.sql.*;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Stock {

    @FXML
    private TextField txtProduit_Nom;

    @FXML
    private TextField txtPrix;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtQuantite;

    @FXML
    private TextField txtFournisseur;

    @FXML
    private TextField txtAdresse;

    @FXML
    private TextField txtVille;

    @FXML
    private TextField txtTelephone;

    @FXML
    private TableView<Stockmodel> table;

    @FXML
    private TableColumn<Stockmodel, String> produit_nom;

    @FXML
    private TableColumn<Stockmodel, String> description;

    @FXML
    private TableColumn<Stockmodel, Double> prix;

    @FXML
    private TableColumn<Stockmodel, Integer> quantite;

    @FXML
    private TableColumn<Stockmodel, String> fournisseur_nom;

    @FXML
    private TableColumn<Stockmodel, String> adresse;

    @FXML
    private TableColumn<Stockmodel, String> ville;

    @FXML
    private TableColumn<Stockmodel, String> telephone;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    private ObservableList<Stockmodel> stocks = FXCollections.observableArrayList();



    private void table() throws SQLException {
        stocks.clear();
        Connect();
        String sql = "SELECT * FROM stock";
        PreparedStatement statement = con.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String produitNom = resultSet.getString("produit_nom");
            String description = resultSet.getString("description");
            double prix = resultSet.getDouble("prix");

            int quantite = resultSet.getInt("quantite");

            String fournisseurNom = resultSet.getString("fournisseur_nom");

            String adresse = resultSet.getString("adresse");

            String ville= resultSet.getString("ville");
            String telephone = resultSet.getString("telephone");


            Stockmodel stock = new Stockmodel(produitNom, description,prix,quantite,fournisseurNom, adresse, ville,telephone);
            stocks.add(stock);
        }
        resultSet.close();
        statement.close();
        con.close();
    }






    // Méthode pour initialiser le contrôleur
    @FXML
    private void initialize() {
        // Associer les propriétés des employés aux colonnes
        produit_nom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProduitNom()));
        description.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));

        prix.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrix()).asObject());
        quantite.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantite()).asObject());
        fournisseur_nom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFournisseurNom()));
        adresse.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdresse()));
        ville.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVille()));
        telephone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelephone()));


        // Ajouter les colonnes à la TableView
        table.getColumns().addAll(produit_nom, description, prix,
                quantite, fournisseur_nom, adresse,
                ville,telephone);

        // Charger les employés depuis la base de données
        try {
            table();
            System.out.println("Données chargées depuis la base de données");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        table.setItems(stocks);

    }




    public void getStock(MouseEvent event) {
        Stockmodel stockSelectionne = table.getSelectionModel().getSelectedItem();
        if (stockSelectionne != null) {
            txtProduit_Nom.setText(stockSelectionne.getProduitNom());
            txtDescription.setText(stockSelectionne.getDescription());
            txtPrix.setText(String.valueOf(stockSelectionne.getPrix()));
            txtQuantite.setText(String.valueOf(stockSelectionne.getQuantite()));
            txtFournisseur.setText(stockSelectionne.getFournisseurNom());
            txtAdresse.setText(stockSelectionne.getAdresse());
            txtVille.setText(stockSelectionne.getVille());
            txtTelephone.setText(stockSelectionne.getTelephone());

        } else {
            afficherAlerteErreur("Aucun stock sélectionné", "Veuillez sélectionner un element à afficher.");
        }
    }
    private void afficherAlerteErreur(String titre, String message) {
        Alert alerte = new Alert(Alert.AlertType.ERROR);
        alerte.setTitle(titre);
        alerte.setHeaderText(null);
        alerte.setContentText(message);
        alerte.showAndWait();
    }




    // Méthode pour ajouter un nouvel employé
    @FXML
    private void ajouterStockAction() {
        String produitNom = txtProduit_Nom.getText();
        String description =txtDescription.getText();
        double prix = Double.parseDouble(txtPrix.getText());
        int quantite = Integer.parseInt(txtQuantite.getText());

        String fournisseurNom = txtFournisseur.getText();
        String adresse = txtAdresse.getText();
        String ville = txtVille.getText();
        String telephone = txtTelephone.getText();

        if (produitNom != null) {
            Stockmodel nouvelStock = new Stockmodel(produitNom, description, prix,quantite, fournisseurNom, adresse,ville, telephone);
            try {
                insererStock(nouvelStock);
                stocks.add(nouvelStock);
                viderChamps();
            } catch (SQLException e) {
                afficherAlerteErreur("Erreur lors de l'ajout", String.valueOf(e));
            }
        } else {
            afficherAlerteErreur("Format de date incorrect", "Veuillez saisir la date au format jj/mm/aaaa.");
        }
    }




    private void viderChamps() {
        txtProduit_Nom.clear();
        txtDescription.clear();
        txtPrix.clear();
        txtQuantite.clear();
        txtFournisseur.clear();
        txtAdresse.clear();
        txtVille.clear();
        txtTelephone.clear();

    }






    private void insererStock(Stockmodel stock) throws SQLException {
        Connect();
        String sql = "INSERT INTO stock (produit_nom,description, prix, quantite, fournisseur_nom, adresse, ville, telephone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, stock.getProduitNom());
        statement.setString(2, stock.getDescription());
        statement.setDouble(3, stock.getPrix());
        statement.setInt(4, stock.getQuantite());
        statement.setString(5, stock.getFournisseurNom());
        statement.setString(6, stock.getAdresse());
        statement.setString(7, stock.getVille());
        statement.setString(8, stock.getTelephone());
        statement.executeUpdate();
        statement.close();
        con.close();
    }











    // Méthode pour supprimer un employé sélectionné
    @FXML
    private void supprimerStockAction() {
        Stockmodel stockSelectionne = table.getSelectionModel().getSelectedItem();
        if (stockSelectionne != null) {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Confirmation de suppression");
            confirmation.setHeaderText("Voulez-vous vraiment supprimer cet stock ?");
            confirmation.setContentText("Cette action est irréversible.");
            confirmation.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        supprimerStock(stockSelectionne);
                        stocks.remove(stockSelectionne);
                        viderChamps();
                    } catch (SQLException e) {
                        afficherAlerteErreur("Erreur lors de la suppression", "Une erreur s'est produite lors de la suppression du stock.");
                    }
                }
            });
        } else {
            afficherAlerteErreur("Aucun employé sélectionné", "Veuillez sélectionner un stock à supprimer.");
        }
    }



    private void supprimerStock(Stockmodel stock) throws SQLException {
        Connect();
        String sql = "DELETE FROM stock WHERE produit_nom=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, stock.getProduitNom());
        statement.executeUpdate();


        statement.close();
        con.close();
    }









    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;

    public void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/rest_vision", "root", "Ouattar@59");
        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }


}




