package com.example.rest_vision.model.Gestion_Personnel;
import java.time.LocalDate;
import java.util.Date;

public class Employe {
    private int id;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String adresse;
    private String numeroTelephone;
    private String email;
    private String poste;
    private String dateEmbauche;
    private String horairesTravail;

    // Constructeur avec tous les champs
    public Employe(int id, String nom, String prenom, String dateNaissance, String adresse, String numeroTelephone, String email, String poste, String dateEmbauche, String horairesTravail) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.numeroTelephone = numeroTelephone;
        this.email = email;
        this.poste = poste;
        this.dateEmbauche = dateEmbauche;
        this.horairesTravail = horairesTravail;
    }

    // Getters et setters pour chaque champ
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(String dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public String getHorairesTravail() {
        return horairesTravail;
    }

    public void setHorairesTravail(String horairesTravail) {
        this.horairesTravail = horairesTravail;
    }
}