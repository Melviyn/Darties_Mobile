package com.maximeattoumani.darties_mobile.model;

/**
 * Created by Maxime on 11/01/2017.
 */

public class Magasin {

    private String libelle;
    private String date_update;

    public Magasin(String libelle, String date_update) {
        this.libelle = libelle;
        this.date_update = date_update;
    }

    public String getDate_update() {
        return date_update;
    }

    public void setDate_update(String date_update) {
        this.date_update = date_update;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
