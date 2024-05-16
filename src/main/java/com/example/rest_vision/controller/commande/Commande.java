package com.example.rest_vision.controller.commande;

import com.example.rest_vision.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class Commande implements Initializable {
    @FXML
    private Button btn_ajouter_commande;

    @FXML
    private Button btn_annuler;

    @FXML
    private Button btn_imprimer_recu;

    @FXML
    private Button btn_payer_commande;

    @FXML
    private TableColumn<CommandeEntity, String> id_commande_menu_col;

    @FXML
    private ComboBox<?> id_menu_commande;

    @FXML
    private TableView<CommandeEntity> liste_commande;

    @FXML
    private TextField montant_commande;

    @FXML
    private TableColumn<CommandeEntity, String> nom_menu_col;

    @FXML
    private ComboBox<?> nom_menu_commande;

    @FXML
    private TableColumn<CommandeEntity, String> prix_col;

    @FXML
    private TableColumn<CommandeEntity, String> qte_col;

    @FXML
    private Spinner<Integer> qte_commande;

    @FXML
    private Label total_commande;

    @FXML
    private Label reste;

    @FXML
    private TableColumn<CommandeEntity, String> type_col;

    //parametre connexion db
    private Connection connect;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;

    public ObservableList<CommandeEntity> listCommande(){
        commandeClientId();
        ObservableList<CommandeEntity> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM commande WHERE client_id = "+ clientId;

        connect = Database.connectDd();

        try{
            preparedStatement = connect.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            CommandeEntity commandeEntity;
            while (resultSet.next()){
                commandeEntity = new CommandeEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("menu_id"),
                        resultSet.getString("nom"),
                        resultSet.getString("type"),
                        resultSet.getDouble("prix"),
                        resultSet.getInt("quantite")
                );
                listData.add(commandeEntity);
            }
        }catch (Exception e){e.printStackTrace();}

        return listData;
    }

    private double montant = 0, resteCommande = 0 ;
    public void montantCommande(){
        totalCommande();

        Alert alert;

        if (montant_commande.getText().isEmpty() || montant_commande.getText() == null){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Entrer le montant svp!");
            alert.showAndWait();
        }else {
            montant = Double.parseDouble(montant_commande.getText());
            if (montant < totalP){
//                System.out.println("+totalCommande");
                montant_commande.setText("");
            }else{
                resteCommande = montant - totalP;
                System.out.println("+totalCommande");
                reste.setText(String.valueOf(resteCommande));
            }
        }

    }
    public void affichageTotalCommande(){
        totalCommande();
        total_commande.setText(String.valueOf(totalP));
    }
    private int clientId;
    public void commandeClientId(){
        String sql = "SELECT client_id FROM commande";
        connect = Database.connectDd();

        try {
            preparedStatement = connect.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                clientId = resultSet.getInt("client_id");
            }

            String checkData = "SELECT client_id FROM recu";

            statement = connect.createStatement();
            resultSet = statement.executeQuery(checkData);

            int clientRecuId = 0;

            while (resultSet.next()){
                clientRecuId = resultSet.getInt("client_id");
            }

            if (clientId==0){clientId +=1;} else if (clientId==clientRecuId) {
                clientId += 1;
            }

        }catch (Exception e){e.printStackTrace();}
    }
    public void ajouterCommande(){
        commandeClientId();
        totalCommande();
        String sql = "INSERT INTO commande "
                +"(client_id, menu_id, nom, type, prix, quantite, date) "
                +"VALUES(?,?,?,?,?,?,?)";

        connect = Database.connectDd();
        try{

            String typeCommande = "";
            double prixCommande = 0;

            String checkData = "SELECT * FROM menu WHERE menu_id = '"
                    +id_menu_commande.getSelectionModel().getSelectedItem()+"'";

            statement = connect.createStatement();
            resultSet = statement.executeQuery(checkData);

            if (resultSet.next()){
                typeCommande = resultSet.getString("type");
                prixCommande = resultSet.getDouble("prix");
            }

            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1,String.valueOf(clientId));
            preparedStatement.setString(2, (String) id_menu_commande.getSelectionModel().getSelectedItem() );
            preparedStatement.setString(3, (String) nom_menu_commande.getSelectionModel().getSelectedItem() );

            double prixTotal = prixCommande * quantite;

            preparedStatement.setString(4, typeCommande);
            preparedStatement.setString(5, String.valueOf(prixTotal));
            preparedStatement.setString(6, String.valueOf(quantite));
            preparedStatement.setString(7, String.valueOf(LocalDateTime.now()));

            preparedStatement.executeUpdate();

            affichageTotalCommande();
            affichageCommandes();
        }catch (Exception e){e.printStackTrace();}
    }

    //creation du recu debut

    //creation du recu fin
    public void payerCommande(){
        commandeClientId();
        totalCommande();

        String sql = "INSERT INTO recu (client_id, total, date) "
                    +"VALUES(?,?,?)";
        connect = Database.connectDd();

        try {

            Alert alert;
            if (resteCommande < 0 || totalP <= 0 ){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("Invalide");
                alert.showAndWait();
            }else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message de Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Etes-vous sure?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)){
                    preparedStatement = connect.prepareStatement(sql);
                    preparedStatement.setString(1, String.valueOf(clientId));
                    preparedStatement.setString(2, String.valueOf(totalP));
                    preparedStatement.setString(3, String.valueOf(LocalDateTime.now()));

                    preparedStatement.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message d'information");
                    alert.setHeaderText(null);
                    alert.setContentText("Succes!");
                    alert.showAndWait();

                    total_commande.setText("");
                    montant_commande.setText("");
                    reste.setText("");
                }else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Message d'erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Annuler!");
                    alert.showAndWait();
                }


            }


        }catch (Exception e){e.printStackTrace();}
    }

    public void supprimerCommande(){
        String sql = "DELETE FROM commande WHERE id = "+item;

        connect = Database.connectDd();
        try {
            Alert alert;
            if (item == 0 || String.valueOf(item).equals("")){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("Selectionner la commande a supprimer");
                alert.showAndWait();
            }else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message de Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Etes-vous sure de vouloir supprimer la commande : "+ item+"?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)){
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message d'information");
                    alert.setHeaderText(null);
                    alert.setContentText("Succes!");
                    alert.showAndWait();
                    affichageCommandes();
                    affichageTotalCommande();
                }else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Message d'erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Annuler!");
                    alert.showAndWait();
                }
            }
        }catch (Exception e){e.printStackTrace();}
    }

    private int item = 0;
    public void selectionDonneCommande(){
        CommandeEntity commande = liste_commande.getSelectionModel().getSelectedItem();
        int num = liste_commande.getSelectionModel().getSelectedIndex();

        if((num - 1)< -1) return;

        item = commande.getId();
    }

    private ObservableList<CommandeEntity> listCommandes;
    public void affichageCommandes(){
        listCommandes = listCommande();

        id_commande_menu_col.setCellValueFactory(new PropertyValueFactory<>("menuId"));
        nom_menu_col.setCellValueFactory(new PropertyValueFactory<>("nom"));
        type_col.setCellValueFactory(new PropertyValueFactory<>("type"));
        prix_col.setCellValueFactory(new PropertyValueFactory<>("prix"));
        qte_col.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        liste_commande.setItems(listCommandes);
    }

    private double totalP = 0;
    public void totalCommande(){
        commandeClientId();

        String sql = "SELECT SUM(prix) FROM commande WHERE client_id = "+clientId;

        connect = Database.connectDd();

        try {
            preparedStatement = connect.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                totalP = resultSet.getDouble("SUM(prix)");
            }
//            affichageTotalCommande();
        }catch (Exception e){e.printStackTrace();}
    }
    public void idMenuCommande(){
        String sql = "SELECT menu_id FROM menu WHERE status = 'Disponible'";
        connect = Database.connectDd();

        try{
            preparedStatement = connect.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (resultSet.next()){
                listData.add(resultSet.getString("menu_id"));
            }
            id_menu_commande.setItems(listData);
            nomMenuCommande();
        }catch (Exception e){e.printStackTrace();}
    }

    public void nomMenuCommande(){
        String sql = "SELECT nom FROM menu WHERE menu_id = '"
                +id_menu_commande.getSelectionModel().getSelectedItem()
                +"'";
        connect = Database.connectDd();
        try{
            preparedStatement = connect.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (resultSet.next()){
                listData.add(resultSet.getString("nom"));
            }
            nom_menu_commande.setItems(listData);
        }catch (Exception e){e.printStackTrace();}
    }

    public void quantiteSpinner(){
        SpinnerValueFactory<Integer> spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        qte_commande.setValueFactory(spinner);
    }
    private int quantite;
    public void quantiteCommande(){
        quantite = qte_commande.getValue();
        System.out.println(quantite);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idMenuCommande();
        nomMenuCommande();
        quantiteSpinner();
        listCommande();
//        ajouterCommande();
        affichageCommandes();
        affichageTotalCommande();
    }
}
