package com.example.rest_vision.model.Gestion_Personnel;

public class Roles {
    private int roleID;
    private String nomRole;

    // Constructeur
    public Roles(int roleID, String nomRole) {
        this.roleID = roleID;
        this.nomRole = nomRole;
    }

    // Getters et setters
    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getNomRole() {
        return nomRole;
    }

    public void setNomRole(String nomRole) {
        this.nomRole = nomRole;
    }
}

