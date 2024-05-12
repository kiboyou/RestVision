package com.example.rest_vision.controller.reservation;

import com.example.rest_vision.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class Reservation implements Initializable {
    Connection connect = null;
    PreparedStatement prepare = null;
    ResultSet result = null;

    // button for action
    @FXML
    private Button addBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button endBtn;

    //RESERVATION COLUMN

    @FXML
    private TableColumn<ReservationModel, String> fnameReservationCol;
    @FXML
    private TableColumn<ReservationModel, Integer> idReservationCol;

    @FXML
    private TableColumn<ReservationModel, Date> inReservationCol;

    @FXML
    private TableColumn<ReservationModel, String> lnameReservationCol;

    @FXML
    private TableColumn<ReservationModel, String> timeInReservationCol;

    @FXML
    private TableColumn<ReservationModel, String> timeOutReservationCol;

    @FXML
    private TableColumn<ReservationModel, String> phoneReservationCol;

    @FXML
    private TableColumn<ReservationModel, Integer> placeReservationCol;

    @FXML
    private TableColumn<ReservationModel, String> statutReservationCol;
    // END RESERVATION COL

    //PLACE COL
    @FXML
    private TableColumn<PlaceModel, Integer> idPlaceCol;
    @FXML
    private TableColumn<PlaceModel, Integer> numPlaceCol;

    @FXML
    private TableColumn<PlaceModel, String> statutPlaceCol;
    // END PLACE COL


    // FORM REGISTRATION
    @FXML
    private TextField lastnameIn;
    @FXML
    private ComboBox<Integer> peopleIn;

    @FXML
    private TextField phoneIn;
    @FXML
    private DatePicker datecheckin;

    @FXML
    private TextField timecheckin;

    @FXML
    private TextField timecheckout;

    @FXML
    private TextField firstnameIn;
    // END

    int id = 0;

    @FXML
    private TableView<PlaceModel> placeView;

    @FXML
    private TableView<ReservationModel> reservationView;

    //USEFUL FUNCTION
    public boolean isValidHourFormat(String input) {
        // Vérifier si la longueur de la chaîne est de 6 caractères
        if (input.length() != 6) {
            return false;
        }

        // Vérifier si chaque caractère est un chiffre
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        // Extraire les heures et les minutes
        int hours = Integer.parseInt(input.substring(0, 2));
        int minutes = Integer.parseInt(input.substring(2, 4));

        // Vérifier si les heures sont comprises entre 0 et 24 et les minutes entre 0 et 60
        return hours >= 0 && hours <= 24 && minutes >= 0 && minutes < 60;
    }

    //ADD A NEW RESERVATION
    public void addReservation(ActionEvent event) {
        String insert = "INSERT INTO reservations(firstname, lastname, phone, datecheckin, timecheckin, timecheckout, numperson) VALUES(?,?,?,?,?,?,?)";
        connect = Database.connectDd();

        //get data

        try {
            String firstname = firstnameIn.getText();
            String lastname = lastnameIn.getText();
            String phone = phoneIn.getText();
            LocalDate checkinDate = datecheckin.getValue();
            Integer numPeople = peopleIn.getValue();
            String timeIn = timecheckin.getText();
            String timeOut = timecheckout.getText();

            //convertion pour comparaison
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime localTimeIn = LocalTime.parse(timeIn, formatter);
            LocalTime localTimeOut = LocalTime.parse(timeOut, formatter);

            if (firstname.isEmpty() || lastname.isEmpty() || phone.isEmpty() || checkinDate == null || timeIn.isEmpty() || timeOut.isEmpty() || numPeople == null) {
                 Alert exceptionAlert = new Alert(Alert.AlertType.ERROR);
                exceptionAlert.setTitle("Empty Data");
                exceptionAlert.setHeaderText(null);
                exceptionAlert.setContentText("Aucun champ ne doit etre vide");
                exceptionAlert.showAndWait();
            } else if (!timeIn.matches("([01]?[0-9]|2[0-3])[0-5][0-9]|([01]?[0-9]|2[0-3]):[0-5][0-9]") || !timeOut.matches("([01]?[0-9]|2[0-3])[0-5][0-9]|([01]?[0-9]|2[0-3]):[0-5][0-9]")) {
                Alert invalidTimeAlert = new Alert(Alert.AlertType.ERROR);
                invalidTimeAlert.setTitle("Format d'heure incorrect");
                invalidTimeAlert.setHeaderText(null);
                invalidTimeAlert.setContentText("Le format de l'heure est incorrect. Utilisez le format hh:mm.");
                invalidTimeAlert.showAndWait();
            } else if (localTimeOut.isBefore(localTimeIn) || localTimeOut.equals(localTimeIn)) {
                Alert invalidTimeAlert = new Alert(Alert.AlertType.ERROR);
                invalidTimeAlert.setTitle("Heure de sortie invalide");
                invalidTimeAlert.setHeaderText(null);
                invalidTimeAlert.setContentText("L'heure de sortie doit être supérieure à l'heure d'entrée.");
                invalidTimeAlert.showAndWait();
            } else{
                prepare = connect.prepareStatement(insert);
                prepare.setString(1, firstname);
                prepare.setString(2, lastname);
                prepare.setString(3, phone);
                prepare.setString(4, checkinDate.toString());
                prepare.setString(5, timeIn);
                prepare.setString(6, timeOut);
                prepare.setInt(7, numPeople);
                prepare.executeUpdate();

                //ajouter une alert de succes
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Reservation effectuée");
                alert.setHeaderText(null);
                alert.setContentText("La réservation a été effectuée avec succès");
                alert.showAndWait();

                clear();
                showReservation();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //fill table of place
    public ObservableList<PlaceModel> getPlace() {
        ObservableList<PlaceModel> place = FXCollections.observableArrayList();

        String req = "SELECT * FROM places";
        connect = Database.connectDd();
        try {
            prepare = connect.prepareStatement(req);
            result = prepare.executeQuery();
            while(result.next()){
                PlaceModel places = new PlaceModel();
                places.setIdplace(Integer.parseInt(result.getString("idplace")));
                places.setNumperson(Integer.parseInt(result.getString("numperson")));
                places.setStatutplace(result.getString("statutplace"));

                place.add(places);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return place;
    }
    //fill table of reservation
    public ObservableList<ReservationModel> getAllReservation() {
        ObservableList<ReservationModel> reservation = FXCollections.observableArrayList();

        String req = "SELECT * FROM reservations";
        connect = Database.connectDd();
        try {
            prepare = connect.prepareStatement(req);
            result = prepare.executeQuery();
            while(result.next()){
                ReservationModel reservations = new ReservationModel();
                reservations.setIdreservation(Integer.parseInt(result.getString("idreservation")));
                reservations.setLastname(result.getString("lastname"));
                reservations.setFirstname(result.getString("firstname"));
                reservations.setPhone(result.getString("phone"));
                reservations.setNumperson(Integer.parseInt(result.getString("numperson")));
                reservations.setDatecheckin(result.getDate("datecheckin"));
                reservations.setTimecheckin(result.getString("timecheckin"));
                reservations.setTimecheckout(result.getString("timecheckout"));
                reservations.setStatutreservation(result.getString("statutreservation"));

                reservation.add(reservations);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservation;
    }
    @FXML
    public void getReservationData(MouseEvent event) {
        ReservationModel reservation = reservationView.getSelectionModel().getSelectedItem();
        id = reservation.getIdreservation();
        lastnameIn.setText(reservation.getLastname());
        firstnameIn.setText(reservation.getFirstname());
        phoneIn.setText(reservation.getPhone());
        datecheckin.setDisable(true);
        timecheckin.setDisable(true);
        timecheckout.setDisable(true);

        peopleIn.setDisable(true);
        phoneIn.setDisable(true);

        String statut = reservation.getStatutreservation();

        if(statut.equals("Annulée")){
            addBtn.setDisable(true);
            endBtn.setDisable(true);
            cancelBtn.setDisable(true);
        }else if (statut.equals("Terminé")) {
            endBtn.setDisable(true);
            cancelBtn.setDisable(true);
            addBtn.setDisable(true);
        }else{
            addBtn.setDisable(true);
            endBtn.setDisable(false);
            cancelBtn.setDisable(false);
        }
    }
    @FXML
    public void cancelReservation(ActionEvent event) {
        String sqlCancel = "UPDATE reservations SET statutreservation = ? WHERE idreservation = ?";
        connect = Database.connectDd();

        // Créer une boîte de dialogue de confirmation
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Annulation de réservation");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Êtes-vous sûr de vouloir annuler cette réservation ?");

        // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
        ButtonType result = confirmationAlert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {
            try {
                prepare = connect.prepareStatement(sqlCancel);
                prepare.setString(1, "Annulée");
                prepare.setInt(2, id);

                if (prepare.executeUpdate() > 0) {
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Annulation de réservation");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("La réservation a été annulée avec succès.");
                    successAlert.showAndWait();
                }
                showReservation();
                clear();
            } catch (SQLException e) {
                /* throw new RuntimeException(e); */
                e.printStackTrace();
                Alert exceptionAlert = new Alert(Alert.AlertType.ERROR);
                exceptionAlert.setTitle("Erreur SQL");
                exceptionAlert.setHeaderText(null);
                exceptionAlert.setContentText("Une erreur s'est produite lors de l'annulation de la réservation.");
                exceptionAlert.showAndWait();
            }
        }
    }
    @FXML
    void endReservation(ActionEvent event) {
        String sqlConfirm = "UPDATE reservations SET statutreservation = ? WHERE idreservation = ?";
        connect = Database.connectDd();
        try {
            prepare = connect.prepareStatement(sqlConfirm);
            prepare.setString(1, "Terminer");
            prepare.setInt(2, id);

            if (prepare.executeUpdate() >0 ){
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Close reservation");
                successAlert.setHeaderText(null);
                successAlert.setContentText("La reservation a été clôturé avec success");
                successAlert.showAndWait();
            }
            showReservation();
            clear();
        } catch (SQLException e) {
            /*throw new RuntimeException(e);*/
            e.printStackTrace();
            Alert exceptionAlert = new Alert(Alert.AlertType.ERROR);
            exceptionAlert.setTitle("Erreur SQL");
            exceptionAlert.setHeaderText(null);
            exceptionAlert.setContentText("Une erreur s'est produite lors de la confirmation de la réservation.");
            exceptionAlert.showAndWait();
        }
    }
    @FXML
    void resetAll(ActionEvent event) {
        /*clear();*/
        /*System.out.println("Clear");*/
    }
    public void fillComboNumplace(){
        String reqSelect = "SELECT numperson FROM places WHERE statutplace='disponible'";
        connect = Database.connectDd();
        try {
            prepare = connect.prepareStatement(reqSelect);
            result = prepare.executeQuery();

            ObservableList listPlace = FXCollections.observableArrayList();

            while(result.next()){
                Integer item = result.getInt("numperson");
                listPlace.add(item);
            }
            peopleIn.setItems(listPlace);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //fill table of reservation
    public void showPlace(){
        ObservableList<PlaceModel> listplace = getPlace();
        placeView.setItems(listplace);
        idPlaceCol.setCellValueFactory(new PropertyValueFactory<PlaceModel, Integer>("idplace"));
        numPlaceCol.setCellValueFactory(new PropertyValueFactory<PlaceModel, Integer>("numperson"));
        statutPlaceCol.setCellValueFactory(new PropertyValueFactory<PlaceModel, String>("statutplace"));
    }
    public void showReservation(){
        ObservableList<ReservationModel> listReservation = getAllReservation();
        reservationView.setItems(listReservation);
        idReservationCol.setCellValueFactory(new PropertyValueFactory<ReservationModel, Integer>("idreservation"));
        lnameReservationCol.setCellValueFactory(new PropertyValueFactory<ReservationModel, String>("lastname"));
        fnameReservationCol.setCellValueFactory(new PropertyValueFactory<ReservationModel, String>("firstname"));
        phoneReservationCol.setCellValueFactory(new PropertyValueFactory<ReservationModel, String>("phone"));
        placeReservationCol.setCellValueFactory(new PropertyValueFactory<ReservationModel, Integer>("numperson"));
        inReservationCol.setCellValueFactory(new PropertyValueFactory<ReservationModel, Date>("datecheckin"));
        timeInReservationCol.setCellValueFactory(new PropertyValueFactory<ReservationModel, String>("timecheckin"));
        timeOutReservationCol.setCellValueFactory(new PropertyValueFactory<ReservationModel, String>("timecheckout"));
        statutReservationCol.setCellValueFactory(new PropertyValueFactory<ReservationModel, String>("statutreservation"));
    }
    public void clear(){
        lastnameIn.setText(null);
        firstnameIn.setText(null);
        datecheckin.setValue(null);

        phoneIn.setText(null);
        peopleIn.setValue(null);
        datecheckin.setDisable(false);
        timecheckin.setDisable(false);
        timecheckout.setDisable(false);

        peopleIn.setDisable(false);
        phoneIn.setDisable(false);
        //button
        endBtn.setDisable(true);
        cancelBtn.setDisable(true);
        addBtn.setDisable(false);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //desativer
        endBtn.setDisable(true);
        cancelBtn.setDisable(true);
        showPlace();
        fillComboNumplace();
        showReservation();
    }

}
