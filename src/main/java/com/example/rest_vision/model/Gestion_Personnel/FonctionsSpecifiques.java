package com.example.rest_vision.model.Gestion_Personnel;

public class FonctionsSpecifiques {
    private int roleID;
    private String fonction;

    // Constructeur
    public FonctionsSpecifiques(int roleID, String fonction) {
        this.roleID = roleID;
        this.fonction = fonction;
    }

    // Getters et setters
    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }
}
