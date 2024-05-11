package com.example.rest_vision.controller.menu;

public class MenuEntity {
    //menu_id, nom, type, prix, status
    private String menuId;
    private String nom;
    private String type;
    private double prix;
    private String status;

    public MenuEntity(String menuId, String nom, String type, double prix, String status) {
        this.menuId = menuId;
        this.nom = nom;
        this.type = type;
        this.prix = prix;
        this.status = status;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
