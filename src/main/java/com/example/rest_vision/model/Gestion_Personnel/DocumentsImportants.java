package com.example.rest_vision.model.Gestion_Personnel;

public class DocumentsImportants {
    private int employeID;
    private String piecesIdentite;

    // Constructeur
    public DocumentsImportants(int employeID, String piecesIdentite) {
        this.employeID = employeID;
        this.piecesIdentite = piecesIdentite;
    }

    // Getters et setters
    public int getEmployeID() {
        return employeID;
    }

    public void setEmployeID(int employeID) {
        this.employeID = employeID;
    }

    public String getPiecesIdentite() {
        return piecesIdentite;
    }

    public void setPiecesIdentite(String piecesIdentite) {
        this.piecesIdentite = piecesIdentite;
    }
}

