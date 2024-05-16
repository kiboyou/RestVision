package com.example.rest_vision.controller.commande;

public class CommandeEntity {
    private int id;
    private String menuId;
    private String nom;
    private String type;
    private double prix;
    private int quantite;

    public CommandeEntity(int id, String menuId, String nom, String type, double prix, int quantite) {
        this.id = id;
        this.menuId = menuId;
        this.nom = nom;
        this.type = type;
        this.prix = prix;
        this.quantite = quantite;
    }

//    public CommandeEntity(String menuId, String nom, String type, double prix, int quantite) {
//        this.menuId = menuId;
//        this.nom = nom;
//        this.type = type;
//        this.prix = prix;
//        this.quantite = quantite;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
