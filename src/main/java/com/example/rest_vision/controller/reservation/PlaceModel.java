package com.example.rest_vision.controller.reservation;

public class PlaceModel {
    private int idplace;
    private int numperson;
    private String statutplace;

    public int getIdplace() {
        return idplace;
    }

    public void setIdplace(int idplace) {
        this.idplace = idplace;
    }

    public int getNumperson() {
        return numperson;
    }

    public void setNumperson(int numperson) {
        this.numperson = numperson;
    }

    public String getStatutplace() {
        return statutplace;
    }

    public void setStatutplace(String statutplace) {
        this.statutplace = statutplace;
    }
}
