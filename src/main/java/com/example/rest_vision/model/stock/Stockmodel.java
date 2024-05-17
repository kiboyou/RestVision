package com.example.rest_vision.model.stock;

public class Stockmodel {
    private String produitNom;
    private String description;
    private double prix;
    private int quantite;
    private String fournisseurNom;
    private String adresse;
    private String ville;
    private String telephone;

    // Constructeurs, getters et setters

    public Stockmodel(String produitNom, String description, double prix, int quantite, String fournisseurNom, String adresse, String ville, String telephone) {
        this.produitNom = produitNom;
        this.description = description;
        this.prix = prix;
        this.quantite = quantite;
        this.fournisseurNom = fournisseurNom;
        this.adresse = adresse;
        this.ville = ville;
        this.telephone = telephone;
    }

    public String getProduitNom() {
        return produitNom;
    }

    public void setProduitNom(String produitNom) {
        this.produitNom = produitNom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getFournisseurNom() {
        return fournisseurNom;
    }

    public void setFournisseurNom(String fournisseurNom) {
        this.fournisseurNom = fournisseurNom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
