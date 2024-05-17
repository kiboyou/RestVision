package com.example.rest_vision.model.Gestion_Personnel;

public class GestionAutorisations {
    private int roleID;
    private boolean ajoutSuppressionUtilisateurs;

    // Constructeur
    public GestionAutorisations(int roleID, boolean ajoutSuppressionUtilisateurs) {
        this.roleID = roleID;
        this.ajoutSuppressionUtilisateurs = ajoutSuppressionUtilisateurs;
    }

    // Getters et setters
    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public boolean isAjoutSuppressionUtilisateurs() {
        return ajoutSuppressionUtilisateurs;
    }

    public void setAjoutSuppressionUtilisateurs(boolean ajoutSuppressionUtilisateurs) {
        this.ajoutSuppressionUtilisateurs = ajoutSuppressionUtilisateurs;
    }
}

