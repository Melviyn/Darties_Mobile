package com.maximeattoumani.darties_mobile.model;

/**
 * Created by Maxime ATTOUMANI on 15/11/2016.
 */
public class User {

    private int id_profil;
    private String nom;
    private String prenom;
    private String password;
    private String mail;
    private String apikey;
    private String lib_profil;
    private String message;

    public String getLib_profil() {
        return lib_profil;
    }

    public void setLib_profil(String lib_profil) {
        this.lib_profil = lib_profil;
    }

    public int getId_profil() {
        return id_profil;
    }

    public void setId_profil(int id_profil) {
        this.id_profil = id_profil;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getApi_key() {
        return apikey;
    }

    public void setApi_key(String apikey) {
        this.apikey = apikey;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
