package com.maximeattoumani.darties_mobile.model;

import java.util.Date;

/**
 * Created by Maxime on 12/12/2016.
 */

public class Notification {

    private String message;
    private String title;
    private String date;
    private String libelle;
    private int id;

    public Notification(String message, String title, String date, String libelle) {
        this.message = message;
        this.title = title;
        this.date = date;
        this.libelle = libelle;
    }

    public Notification() {
        this.id = 0;
        this.message = "";
        this.title = "";
        this.date = "";
        this.libelle = "";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
