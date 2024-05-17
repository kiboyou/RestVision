package com.example.rest_vision.controller.personnel;

import com.example.rest_vision.model.Gestion_Personnel.Employe;
import com.example.rest_vision.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

public class EmployeController {

    @FXML
    private TableView<Employe> employesView;

    @FXML
    private TableColumn<Employe, Integer> idEmployeeCol;

    @FXML
    private TableColumn<Employe, String> nameEmployee;

    @FXML
    private TableColumn<Employe, String> fnameEmployeCol;

    @FXML
    private TableColumn<Employe, String> fadressEmployeCol;

    @FXML
    private TableColumn<Employe, String> femailEmployeCol;

    @FXML
    private TableColumn<Employe, String> fphoneEmployeCol;

    @FXML
    private TableColumn<Employe, String> fpostEmployeCol;

    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField dateNaissanceField;
    @FXML
    private TextField adresseField;
    @FXML
    private TextField phoneIn;
    @FXML
    private TextField emailField;
    @FXML
    private TextField InpostField;

    @FXML
    private TextField horairesTravailField;
    @FXML
    private TextField dateEmbauchestr;

    private ObservableList<Employe> employes = FXCollections.observableArrayList();
    private Connection connection;


    // Méthode pour établir la connexion à la base de données
    private void connecter() throws SQLException {
        connection = Database.connectDd();
    }


    // Méthode pour insérer un nouvel employé dans la base de données
    private void insererEmploye(Employe employe) throws SQLException {
        connecter();
        String sql = "INSERT INTO Employes (Nom, Prenom, DateNaissance, Adresse, numeroTelephone, email, poste, dateEmbauche, horairesTravail) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, employe.getNom());
        statement.setString(2, employe.getPrenom());
        statement.setString(3, employe.getDateNaissance());
        statement.setString(4, employe.getAdresse());
        statement.setString(5, employe.getNumeroTelephone());
        statement.setString(6, employe.getEmail());
        statement.setString(7, employe.getPoste());
        statement.setString(8, employe.getDateEmbauche());
        statement.setString(9, employe.getHorairesTravail());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    // Méthode pour mettre à jour les informations d'un employé dans la base de données
    private void mettreAJourEmploye(Employe employe) throws SQLException {
        connecter();
        String sql = "UPDATE Employes SET Nom=?, Prenom=?, DateNaissance=?, Adresse=?, numeroTelephone=?, email=?, poste=?, dateEmbauche=?, horairesTravail=? WHERE EmployeID=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, employe.getId());
        statement.setString(2, employe.getNom());
        statement.setString(3, employe.getPrenom());
        statement.setString(4, employe.getDateNaissance());
        statement.setString(5, employe.getAdresse());
        statement.setString(6, employe.getNumeroTelephone());
        statement.setString(7, employe.getEmail());
        statement.setString(8, employe.getPoste());
        statement.setString(9, employe.getDateEmbauche());
        statement.setString(10, employe.getHorairesTravail());

        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    // Méthode pour supprimer un employé de la base de données
    private void supprimerEmploye(Employe employe) throws SQLException {
        connecter();
        String sql = "DELETE FROM DemandesConge WHERE EmployeID=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, employe.getId());
        statement.executeUpdate();

        String sql1 = "DELETE FROM EmployesRoles WHERE EmployeID=?";
        PreparedStatement statement1 = connection.prepareStatement(sql1);
        statement1.setInt(1, employe.getId());
        statement1.executeUpdate();

        String sql2 = "DELETE FROM DocumentsImportants WHERE EmployeID=?";
        PreparedStatement statement2 = connection.prepareStatement(sql2);
        statement2.setInt(1, employe.getId());
        statement2.executeUpdate();

        String sql3 = "DELETE FROM Employes WHERE EmployeID=?";
        PreparedStatement statement3 = connection.prepareStatement(sql3);
        statement3.setInt(1, employe.getId());
        statement3.executeUpdate();

        statement.close();
        statement1.close();
        statement2.close();
        statement3.close();
        connection.close();
    }

    // Méthode pour charger tous les employés depuis la base de données
    private void chargerEmployes() throws SQLException {
        employes.clear();
        connecter();
        String sql = "SELECT * FROM Employes";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("EmployeID");
            String nom = resultSet.getString("Nom");
            String prenom = resultSet.getString("Prenom");
            String dateNaissance = resultSet.getString("DateNaissance");
            String adresse = resultSet.getString("Adresse");

            String numeroTelephone = resultSet.getString("numeroTelephone");
            String email = resultSet.getString("email");
            String poste = resultSet.getString("poste");
            String dateEmbauche = resultSet.getString("dateEmbauche");
            String horairesTravail = resultSet.getString("horairesTravail");
            Employe employe = new Employe(id, nom, prenom, dateNaissance, adresse, numeroTelephone, email, poste, dateEmbauche, horairesTravail);
            employes.add(employe);
        }
        resultSet.close();
        statement.close();
        connection.close();
    }

    // Méthode pour initialiser le contrôleur
    @FXML
    private void initialize() {
        // Associer les propriétés des employés aux colonnes
        idEmployeeCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        nameEmployee.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
        fnameEmployeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrenom()));
        fadressEmployeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdresse()));
        femailEmployeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        fphoneEmployeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNumeroTelephone()));
        fpostEmployeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPoste()));


        // Ajouter les colonnes à la TableView
        employesView.getColumns().addAll(idEmployeeCol, nameEmployee, fnameEmployeCol,
                fadressEmployeCol, femailEmployeCol, fphoneEmployeCol,
                fpostEmployeCol);

        // Charger les employés depuis la base de données
        try {
            chargerEmployes();
            System.out.println("Données chargées depuis la base de données");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        employesView.setItems(employes);

    }


    // Méthode pour ajouter un nouvel employé
    @FXML
    private void ajouterEmployeAction() {
        int id = generateDefaultId();
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String dateNaissance = dateNaissanceField.getText();
        String adresse = adresseField.getText();

        String numeroTelephone = phoneIn.getText();
        String email = emailField.getText();
        String poste = InpostField.getText();
        String dateEmbauche = dateEmbauchestr.getText();

        String horairesTravail = horairesTravailField.getText();

        if (dateNaissance != null) {
            Employe nouvelEmploye = new Employe(id, nom, prenom, dateNaissance, adresse, numeroTelephone, email, poste, dateEmbauche, horairesTravail);
            try {
                insererEmploye(nouvelEmploye);
                employes.add(nouvelEmploye);
                viderChamps();
            } catch (SQLException e) {
                afficherAlerteErreur("Erreur lors de l'ajout", "Une erreur s'est produite lors de l'ajout de l'employé.");
            }
        } else {
            afficherAlerteErreur("Format de date incorrect", "Veuillez saisir la date au format jj/mm/aaaa.");
        }
    }

    // Méthode pour modifier un employé sélectionné
    @FXML
    private void modifierEmployeAction() throws SQLException {
        Employe employeSelectionne = employesView.getSelectionModel().getSelectedItem();
        if (employeSelectionne != null) {
            employeSelectionne.setNom(nomField.getText());
            employeSelectionne.setPrenom(prenomField.getText());
            employeSelectionne.setDateNaissance(dateNaissanceField.getText());
            employeSelectionne.setAdresse(adresseField.getText());
            employeSelectionne.setNumeroTelephone(phoneIn.getText());
            employeSelectionne.setEmail(emailField.getText());
            employeSelectionne.setPoste(InpostField.getText());
            employeSelectionne.setDateEmbauche(dateEmbauchestr.getText());
            employeSelectionne.setHorairesTravail(horairesTravailField.getText());

            try {
                mettreAJourEmploye(employeSelectionne);
                viderChamps();
            }
            catch (SQLException e) {
                afficherAlerteErreur("Erreur lors de la modification", "Une erreur s'est produite lors de la modification de l'employé.");
//                afficherAlerteErreur(String.valueOf(e), String.valueOf(e));
            }
        } else {
            afficherAlerteErreur("Aucun employé sélectionné", "Veuillez sélectionner un employé à modifier.");
        }
    }


    // Méthode pour supprimer un employé sélectionné
    @FXML
    private void supprimerEmployeAction() {
        Employe employeSelectionne = employesView.getSelectionModel().getSelectedItem();
        if (employeSelectionne != null) {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Confirmation de suppression");
            confirmation.setHeaderText("Voulez-vous vraiment supprimer cet employé ?");
            confirmation.setContentText("Cette action est irréversible.");
            confirmation.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        supprimerEmploye(employeSelectionne);
                        employes.remove(employeSelectionne);
                        viderChamps();
                    } catch (SQLException e) {
                        afficherAlerteErreur("Erreur lors de la suppression", "Une erreur s'est produite lors de la suppression de l'employé.");
                    }
                }
            });
        } else {
            afficherAlerteErreur("Aucun employé sélectionné", "Veuillez sélectionner un employé à supprimer.");
        }
    }

    public void getEmploye(MouseEvent event) {
        Employe employeSelectionne = employesView.getSelectionModel().getSelectedItem();
        if (employeSelectionne != null) {
            prenomField.setText(employeSelectionne.getPrenom());
            nomField.setText(employeSelectionne.getNom());
            dateNaissanceField.setText(employeSelectionne.getDateNaissance());
            adresseField.setText(employeSelectionne.getAdresse());
            phoneIn.setText(employeSelectionne.getNumeroTelephone());
            InpostField.setText(employeSelectionne.getPoste());
            dateEmbauchestr.setText(employeSelectionne.getDateEmbauche());
            emailField.setText(employeSelectionne.getEmail());
            horairesTravailField.setText(employeSelectionne.getHorairesTravail());
        } else {
            afficherAlerteErreur("Aucun employé sélectionné", "Veuillez sélectionner un employé à modifier.");
        }
    }

    // Méthode pour vider les champs de saisie
    private void viderChamps() {
        nomField.clear();
        prenomField.clear();
        dateNaissanceField.clear();
        adresseField.clear();
        horairesTravailField.clear();
        emailField.clear();
        dateEmbauchestr.clear();
        phoneIn.clear();
        phoneIn.clear();
        InpostField.clear();
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
