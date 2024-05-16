package com.example.rest_vision.controller.personnel;

import com.example.rest_vision.Database;
import com.example.rest_vision.model.Gestion_Personnel.Roles;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class RoleController {

    @FXML
    private TableView<Roles> RolessView;

    @FXML
    private TableColumn<Roles, Integer> idRoleseCol;

    @FXML
    private TableColumn<Roles, String> nameRolese;


    @FXML
    private TextField nomField;

    private ObservableList<Roles> Roless = FXCollections.observableArrayList();
    private Connection connection;


    // Méthode pour établir la connexion à la base de données
    private void connecter() throws SQLException {
        connection = Database.connectDd();
    }


    // Méthode pour insérer un nouvel employé dans la base de données
    private void insererRoles(Roles Role) throws SQLException {
        connecter();
        String sql = "INSERT INTO Roles (NomRole) VALUES (?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, Role.getNomRole());

        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    // Méthode pour mettre à jour les informations d'un employé dans la base de données
    private void mettreAJourRoles(Roles Role) throws SQLException {
        connecter();
        String sql = "UPDATE Roles SET NomRole=?, WHERE RoleID=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, Role.getRoleID());
        statement.setString(2, Role.getNomRole());

        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    // Méthode pour supprimer un employé de la base de données
    private void supprimerRoles(Roles Role) throws SQLException {
        connecter();

        String sql1 = "DELETE FROM ControleAcces WHERE RoleID=?";
        PreparedStatement statement1 = connection.prepareStatement(sql1);
        statement1.setInt(1, Role.getRoleID());
        statement1.executeUpdate();

        String sql2 = "DELETE FROM EmployesRoles WHERE RoleID=?";
        PreparedStatement statement2 = connection.prepareStatement(sql2);
        statement2.setInt(1,Role.getRoleID());
        statement2.executeUpdate();

        String sql3 = "DELETE FROM FonctionsSpecifiques WHERE RoleID=?";
        PreparedStatement statement3 = connection.prepareStatement(sql3);
        statement3.setInt(1, Role.getRoleID());
        statement3.executeUpdate();

        String sql4 = "DELETE FROM GestionAutorisations WHERE RoleID=?";
        PreparedStatement statement4 = connection.prepareStatement(sql4);
        statement3.setInt(1, Role.getRoleID());
        statement3.executeUpdate();


        String sql = "DELETE FROM Roles WHERE RoleID=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, Role.getRoleID());
        statement.executeUpdate();

        statement1.close();
        statement2.close();
        statement3.close();
        statement4.close();
        statement.close();
        connection.close();
    }

    // Méthode pour charger tous les employés depuis la base de données
    private void chargerRoless() throws SQLException {
        Roless.clear();
        connecter();
        String sql = "SELECT * FROM Roles";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("RoleID");
            String nom = resultSet.getString("NomRole");

            Roles Role = new Roles(id, nom);
            Roless.add(Role);
        }
        resultSet.close();
        statement.close();
        connection.close();
    }

    // Méthode pour initialiser le contrôleur
    @FXML
    private void initialize() {
        // Associer les propriétés des employés aux colonnes
        idRoleseCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getRoleID()).asObject());
        nameRolese.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomRole()));


        // Ajouter les colonnes à la TableView
        RolessView.getColumns().addAll(idRoleseCol, nameRolese);

        // Charger les employés depuis la base de données
        try {
            chargerRoless();
            System.out.println("Données chargées depuis la base de données");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        RolessView.setItems(Roless);

    }


    // Méthode pour ajouter un nouvel employé
    @FXML
    private void ajouterRolesAction() {
        int id = generateDefaultId();
        String nom = nomField.getText();

        if (nom != null) {
            Roles nouvelRoles = new Roles(id, nom);
            try {
                insererRoles(nouvelRoles);
                Roless.add(nouvelRoles);
                viderChamps();
            } catch (SQLException e) {
                afficherAlerteErreur("Erreur lors de l'ajout", "Une erreur s'est produite lors de l'ajout du role.");
            }
        } else {
            afficherAlerteErreur("Format de date incorrect", "Veuillez saisir la date au format jj/mm/aaaa.");
        }
    }

    // Méthode pour modifier un employé sélectionné
    @FXML
    private void modifierRolesAction() throws SQLException {
        Roles RolesSelectionne = RolessView.getSelectionModel().getSelectedItem();
        if (RolesSelectionne != null) {
            RolesSelectionne.setNomRole(nomField.getText());
            try {
                mettreAJourRoles(RolesSelectionne);
                viderChamps();
            }
            catch (SQLException e) {
                afficherAlerteErreur("Erreur lors de la modification", "Une erreur s'est produite lors de la modification du role.");
//                afficherAlerteErreur(String.valueOf(e), String.valueOf(e));
            }
        } else {
            afficherAlerteErreur("Aucun employé sélectionné", "Veuillez sélectionner un employé à modifier.");
        }
    }


    // Méthode pour supprimer un employé sélectionné
    @FXML
    private void supprimerRolesAction() {
        Roles RolesSelectionne = RolessView.getSelectionModel().getSelectedItem();
        if (RolesSelectionne != null) {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Confirmation de suppression");
            confirmation.setHeaderText("Voulez-vous vraiment supprimer ce role ?");
            confirmation.setContentText("Cette action est irréversible.");
            confirmation.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        supprimerRoles(RolesSelectionne);
                        Roless.remove(RolesSelectionne);
                        viderChamps();
                    } catch (SQLException e) {
                        afficherAlerteErreur("Erreur lors de la suppression", "Une erreur s'est produite lors de la suppression du role.");
                    }
                }
            });
        } else {
            afficherAlerteErreur("Aucun employé sélectionné", "Veuillez sélectionner un employé à supprimer.");
        }
    }

    public void getRoles(MouseEvent event) {
        Roles RolesSelectionne = RolessView.getSelectionModel().getSelectedItem();
        if (RolesSelectionne != null) {
            nomField.setText(RolesSelectionne.getNomRole());
        } else {
            afficherAlerteErreur("Aucun employé sélectionné", "Veuillez sélectionner un employé à modifier.");
        }
    }

    // Méthode pour vider les champs de saisie
    private void viderChamps() {
        nomField.clear();
    }

    // Méthode utilitaire pour afficher une alerte d'erreur
    private void afficherAlerteErreur(String titre, String message) {
        Alert alerte = new Alert(Alert.AlertType.ERROR);
        alerte.setTitle(titre);
        alerte.setHeaderText(null);
        alerte.setContentText(message);
        alerte.showAndWait();
    }

    // Méthode utilitaire pour convertir une chaîne de caractères en objet Date
    private Date convertirEnDate(String dateStr) {
        DateFormat format = new SimpleDateFormat("/MM/yyyy");
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private int generateDefaultId() {
        return new Random().nextInt(100); // Génération d'un nombre aléatoire entre 0 et 999
    }

}
