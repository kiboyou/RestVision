package com.example.rest_vision.controller.personnel;

import com.example.rest_vision.Database;
import com.example.rest_vision.model.Gestion_Personnel.DemandesConge;
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

public class DemandeCongeController {
        @FXML
        private TableView<DemandesConge> DemandesCongesView;
        @FXML
        private TableColumn<DemandesConge, Integer> idDemandesCongeeCol;
        @FXML
        private TableColumn<DemandesConge, Integer> nameDemandesCongee;
        @FXML
        private TableColumn<DemandesConge, String> fnameDemandesCongeCol;
        @FXML
        private TableColumn<DemandesConge, String> fadressDemandesCongeCol;
        @FXML
        private TableColumn<DemandesConge, String> femailDemandesCongeCol;
        @FXML
        private TableColumn<DemandesConge, Integer> fphoneDemandesCongeCol;
        @FXML
        private TableColumn<DemandesConge, String> fpostDemandesCongeCol;

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

        @FXML
        private TextField InpostField1;
        @FXML
        private TextField dateEmbauchestr1;

        private ObservableList<DemandesConge> DemandesConges = FXCollections.observableArrayList();
        private Connection connection;

        // Méthode pour établir la connexion à la base de données
        private void connecter() throws SQLException {
            connection = Database.connectDd();
        }

        // Méthode pour insérer un nouvel employé dans la base de données
        private void insererDemandesConge(DemandesConge DemandesConge) throws SQLException {
            connecter();
            String sql = "INSERT INTO DemandesConge (EmployeID, DatesCongeDebut, DatesCongeFin, TypeConge, DureeConge, MotifConge, SoldeCongeActuel, StatutDemande, DateSoumission, ApprobationsHierarchiques, DocumentsJustificatifs) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, DemandesConge.getEmployeID());
            statement.setString(2, DemandesConge.getDatesCongeDebut());
            statement.setString(3, DemandesConge.getDatesCongeFin());
            statement.setString(4, DemandesConge.getTypeConge());
            statement.setInt(5, DemandesConge.getDureeConge());
            statement.setString(6, DemandesConge.getMotifConge());
            statement.setInt(7, DemandesConge.getSoldeCongeActuel());
            statement.setString(8, DemandesConge.getStatutDemande());
            statement.setString(9, DemandesConge.getDateSoumission());
            statement.setString(10, DemandesConge.getApprobationsHierarchiques());
            statement.setString(11, DemandesConge.getDocumentsJustificatifs());
            statement.executeUpdate();
            statement.close();
            connection.close();
        }

        // Méthode pour mettre à jour les informations d'un employé dans la base de données
        private void mettreAJourDemandesConge(DemandesConge DemandesConge) throws SQLException {
            connecter();
            String sql = "UPDATE DemandesConge SET DemandeID=?, EmployeID=?, DatesCongeDebut=?, DatesCongeFin=?, TypeConge=?, DureeConge=?, MotifConge=?, SoldeCongeActuel=?, StatutDemande=?, ApprobationsHierarchiques=?, DocumentsJustificatifs=? WHERE DemandesCongeID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, DemandesConge.getDemandeID());
            statement.setInt(2, DemandesConge.getEmployeID());
            statement.setString(3, DemandesConge.getDatesCongeDebut());
            statement.setString(4, DemandesConge.getDatesCongeFin());
            statement.setString(5, DemandesConge.getTypeConge());
            statement.setInt(6, DemandesConge.getDureeConge());
            statement.setString(7, DemandesConge.getMotifConge());
            statement.setInt(8, DemandesConge.getSoldeCongeActuel());
            statement.setString(9, DemandesConge.getStatutDemande());
            statement.setString(10, DemandesConge.getDateSoumission());
            statement.setString(11, DemandesConge.getApprobationsHierarchiques());
            statement.setString(12, DemandesConge.getDocumentsJustificatifs());

            statement.executeUpdate();
            statement.close();
            connection.close();
        }

        // Méthode pour supprimer un employé de la base de données
        private void supprimerDemandesConge(DemandesConge DemandesConge) throws SQLException {
            connecter();
            String sql = "DELETE FROM DemandesConge WHERE DemandeID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, DemandesConge.getDemandeID());
            statement.executeUpdate();

            statement.close();
            connection.close();
        }

        // Méthode pour charger tous les employés depuis la base de données
        private void chargerDemandesConges() throws SQLException {
            DemandesConges.clear();
            connecter();
            String sql = "SELECT * FROM DemandesConge";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int demandeID = resultSet.getInt("DemandeID");
                int employeID = resultSet.getInt("EmployeID");
                String datesCongeDebut = resultSet.getString("DatesCongeDebut");
                String datesCongeFin = resultSet.getString("DatesCongeFin");
                String typeConge = resultSet.getString("TypeConge");

                int dureeConge = resultSet.getInt("DureeConge");
                String motifConge = resultSet.getString("MotifConge");
                int soldeCongeActuel = resultSet.getInt("SoldeCongeActuel");
                String statutDemande = resultSet.getString("StatutDemande");
                String dateSoumission = resultSet.getString("DateSoumission");
                String approbationsHierarchiques = resultSet.getString("ApprobationsHierarchiques");
                String documentsJustificatifs = resultSet.getString("DocumentsJustificatifs");
                DemandesConge DemandesConge = new DemandesConge(demandeID, employeID, datesCongeDebut, datesCongeFin, typeConge, dureeConge, motifConge, soldeCongeActuel, statutDemande, dateSoumission, approbationsHierarchiques, documentsJustificatifs);
                DemandesConges.add(DemandesConge);
            }
            resultSet.close();
            statement.close();
            connection.close();
        }

        // Méthode pour initialiser le contrôleur
        @FXML
        private void initialize() {
            // Associer les propriétés des employés aux colonnes
            idDemandesCongeeCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getDemandeID()).asObject());
            nameDemandesCongee.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getEmployeID()).asObject());
            fnameDemandesCongeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDatesCongeDebut()));
            fadressDemandesCongeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDatesCongeFin()));
            femailDemandesCongeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMotifConge()));
            fphoneDemandesCongeCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getDureeConge()).asObject());
            fpostDemandesCongeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatutDemande()));


            // Ajouter les colonnes à la TableView
            DemandesCongesView.getColumns().addAll(idDemandesCongeeCol, nameDemandesCongee, fnameDemandesCongeCol,
                    fadressDemandesCongeCol, femailDemandesCongeCol, fphoneDemandesCongeCol,
                    fpostDemandesCongeCol);

            // Charger les employés depuis la base de données
            try {
                chargerDemandesConges();
                System.out.println("Données chargées depuis la base de données");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DemandesCongesView.setItems(DemandesConges);

        }


        // Méthode pour ajouter un nouvel employé
        @FXML
        private void ajouterDemandesCongeAction() {
            int demandeID = generateDefaultId();
            int employeID = Integer.parseInt(prenomField.getText());
            String datesCongeDebut = adresseField.getText();
            String datesCongeFin = phoneIn.getText();
            String typeConge = InpostField.getText();

            int dureeConge = Integer.parseInt(nomField.getText());
            String motifConge = dateNaissanceField.getText();
            int soldeCongeActuel = Integer.parseInt(emailField.getText());
            String statutDemande = InpostField1.getText();
            String dateSoumission = dateEmbauchestr.getText();
            String approbationsHierarchiques = horairesTravailField.getText();
            String documentsJustificatifs = dateEmbauchestr1.getText();

            if (datesCongeDebut != null) {
                DemandesConge nouvelDemandesConge = new DemandesConge(demandeID, employeID, datesCongeDebut, datesCongeFin, typeConge, dureeConge, motifConge, soldeCongeActuel, statutDemande, dateSoumission, approbationsHierarchiques, documentsJustificatifs);
                try {
                    insererDemandesConge(nouvelDemandesConge);
                    DemandesConges.add(nouvelDemandesConge);
                    viderChamps();
                } catch (SQLException e) {
                    afficherAlerteErreur("Erreur lors de l'ajout", "Une erreur s'est produite lors de l'ajout de la demande.");
                }
            } else {
                afficherAlerteErreur("Format de date incorrect", "Veuillez saisir la date au format jj/mm/aaaa.");
            }
        }

        // Méthode pour modifier un employé sélectionné
        @FXML
        private void modifierDemandesCongeAction() throws SQLException {
            DemandesConge DemandesCongeSelectionne = DemandesCongesView.getSelectionModel().getSelectedItem();
            if (DemandesCongeSelectionne != null) {
//                DemandesCongeSelectionne.setNom(nomField.getText());
//                DemandesCongeSelectionne.setPrenom(prenomField.getText());
//                DemandesCongeSelectionne.setDateNaissance(dateNaissanceField.getText());
//                DemandesCongeSelectionne.setAdresse(adresseField.getText());
//                DemandesCongeSelectionne.setNumeroTelephone(phoneIn.getText());
//                DemandesCongeSelectionne.setEmail(emailField.getText());
//                DemandesCongeSelectionne.setPoste(InpostField.getText());
//                DemandesCongeSelectionne.setDateEmbauche(dateEmbauchestr.getText());
//                DemandesCongeSelectionne.setHorairesTravail(horairesTravailField.getText());

                try {
                    mettreAJourDemandesConge(DemandesCongeSelectionne);
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
        private void supprimerDemandesCongeAction() {
            DemandesConge DemandesCongeSelectionne = DemandesCongesView.getSelectionModel().getSelectedItem();
            if (DemandesCongeSelectionne != null) {
                Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                confirmation.setTitle("Confirmation de suppression");
                confirmation.setHeaderText("Voulez-vous vraiment supprimer cette demande ?");
                confirmation.setContentText("Cette action est irréversible.");
                confirmation.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        try {
                            supprimerDemandesConge(DemandesCongeSelectionne);
                            DemandesConges.remove(DemandesCongeSelectionne);
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

        public void getDemandesConge(MouseEvent event) {
            DemandesConge DemandesCongeSelectionne = DemandesCongesView.getSelectionModel().getSelectedItem();
            if (DemandesCongeSelectionne != null) {
                prenomField.setText(Integer.toString(DemandesCongeSelectionne.getEmployeID()));
                nomField.setText(Integer.toString(DemandesCongeSelectionne.getDureeConge()));
                dateNaissanceField.setText(DemandesCongeSelectionne.getMotifConge());
                adresseField.setText(DemandesCongeSelectionne.getDatesCongeDebut());
                phoneIn.setText(DemandesCongeSelectionne.getDatesCongeFin());
                InpostField.setText(DemandesCongeSelectionne.getTypeConge());
                dateEmbauchestr.setText(DemandesCongeSelectionne.getDateSoumission());
                emailField.setText(Integer.toString(DemandesCongeSelectionne.getSoldeCongeActuel()));
                horairesTravailField.setText(DemandesCongeSelectionne.getApprobationsHierarchiques());

                InpostField1.setText(DemandesCongeSelectionne.getStatutDemande());
                dateEmbauchestr1.setText(DemandesCongeSelectionne.getDocumentsJustificatifs());
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
            dateEmbauchestr1.clear();
            InpostField1.clear();
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