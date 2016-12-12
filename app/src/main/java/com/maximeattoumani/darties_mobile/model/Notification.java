package com.maximeattoumani.darties_mobile.model;

import java.util.Date;

/**
 * Created by Maxime on 12/12/2016.
 */

public class Notification {

    private String message;
    private int id;
    private String result;
    private int id_user;
    private String date;

    public Notification(String message, String result, int id_user, String date) {
        this.message = message;
        this.result = result;
        this.id_user = id_user;
        this.date = date;
    }

    public Notification() {
        this.message = "";
        this.id = 0;
        this.result = "";
        this.id_user = 0;
        this.date = "";
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
