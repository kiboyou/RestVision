package com.example.rest_vision.model.Gestion_Personnel;

import java.util.Date;

public class DemandesConge {
    private int demandeID;
    private int employeID;
    private String datesCongeDebut;
    private String datesCongeFin;
    private String typeConge;
    private int dureeConge;
    private String motifConge;
    private int soldeCongeActuel;
    private String statutDemande;
    private String dateSoumission;
    private String approbationsHierarchiques;
    private String documentsJustificatifs;

    // Constructeur
    public DemandesConge(int demandeID, int employeID, String datesCongeDebut, String datesCongeFin, String typeConge, int dureeConge, String motifConge, int soldeCongeActuel, String statutDemande, String dateSoumission, String approbationsHierarchiques, String documentsJustificatifs) {
        this.demandeID = demandeID;
        this.employeID = employeID;
        this.datesCongeDebut = datesCongeDebut;
        this.datesCongeFin = datesCongeFin;
        this.typeConge = typeConge;
        this.dureeConge = dureeConge;
        this.motifConge = motifConge;
        this.soldeCongeActuel = soldeCongeActuel;
        this.statutDemande = statutDemande;
        this.dateSoumission = dateSoumission;
        this.approbationsHierarchiques = approbationsHierarchiques;
        this.documentsJustificatifs = documentsJustificatifs;
    }

    // Getters et setters
    public int getDemandeID() {
        return demandeID;
    }

    public void setDemandeID(int demandeID) {
        this.demandeID = demandeID;
    }

    public int getEmployeID() {
        return employeID;
    }

    public void setEmployeID(int employeID) {
        this.employeID = employeID;
    }

    public String getDatesCongeDebut() {
        return datesCongeDebut;
    }

    public void setDatesCongeDebut(String datesCongeDebut) {
        this.datesCongeDebut = datesCongeDebut;
    }

    public String getDatesCongeFin() {
        return datesCongeFin;
    }

    public void setDatesCongeFin(String datesCongeFin) {
        this.datesCongeFin = datesCongeFin;
    }

    public String getTypeConge() {
        return typeConge;
    }

    public void setTypeConge(String typeConge) {
        this.typeConge = typeConge;
    }

    public int getDureeConge() {
        return dureeConge;
    }

    public void setDureeConge(int dureeConge) {
        this.dureeConge = dureeConge;
    }

    public String getMotifConge() {
        return motifConge;
    }

    public void setMotifConge(String motifConge) {
        this.motifConge = motifConge;
    }

    public int getSoldeCongeActuel() {
        return soldeCongeActuel;
    }

    public void setSoldeCongeActuel(int soldeCongeActuel) {
        this.soldeCongeActuel = soldeCongeActuel;
    }

    public String getStatutDemande() {
        return statutDemande;
    }

    public void setStatutDemande(String statutDemande) {
        this.statutDemande = statutDemande;
    }

    public String getDateSoumission() {
        return dateSoumission;
    }

    public void setDateSoumission(String dateSoumission) {
        this.dateSoumission = dateSoumission;
    }

    public String getApprobationsHierarchiques() {
        return approbationsHierarchiques;
    }

    public void setApprobationsHierarchiques(String approbationsHierarchiques) {
        this.approbationsHierarchiques = approbationsHierarchiques;
    }

    public String getDocumentsJustificatifs() {
        return documentsJustificatifs;
    }

    public void setDocumentsJustificatifs(String documentsJustificatifs) {
        this.documentsJustificatifs = documentsJustificatifs;
    }
}
