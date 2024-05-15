package com.example.rest_vision.model.Gestion_Personnel;

public class ControleAcces {
    private int roleID;
    private boolean modificationDonnees;

    // Constructeur
    public ControleAcces(int roleID, boolean modificationDonnees) {
        this.roleID = roleID;
        this.modificationDonnees = modificationDonnees;
    }

    // Getters et setters
    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public boolean isModificationDonnees() {
        return modificationDonnees;
    }

    public void setModificationDonnees(boolean modificationDonnees) {
        this.modificationDonnees = modificationDonnees;
    }
}
