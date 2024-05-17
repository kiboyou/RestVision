package com.example.rest_vision.controller.reservation;

import java.sql.Time;
import java.util.Date;

public class ReservationModel {
    private int idreservation;
    private  String firstname;
    private String lastname;
    private Date datecheckin;
    private String timecheckin;
    private String timecheckout;
    private int numperson;
    private String statutreservation;
    private String phone;

    public int getIdreservation() {
        return idreservation;
    }

    public void setIdreservation(int idreservation) {
        this.idreservation = idreservation;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDatecheckin() {
        return datecheckin;
    }

    public void setDatecheckin(Date datecheckin) {
        this.datecheckin = datecheckin;
    }

    public String getTimecheckin() {
        return timecheckin;
    }

    public void setTimecheckin(String timecheckin) {
        this.timecheckin = timecheckin;
    }

    public String getTimecheckout() {
        return timecheckout;
    }

    public void setTimecheckout(String timecheckout) {
        this.timecheckout = timecheckout;
    }

    public int getNumperson() {
        return numperson;
    }

    public void setNumperson(int numperson) {
        this.numperson = numperson;
    }

    public String getStatutreservation() {
        return statutreservation;
    }

    public void setStatutreservation(String statutreservation) {
        this.statutreservation = statutreservation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
