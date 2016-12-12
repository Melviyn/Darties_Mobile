package com.maximeattoumani.darties_mobile.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxime on 12/12/2016.
 */

public class NotificationBDD {

    private static final int VERSION_BDD = 4;
    private static final String NOM_BDD ="database.db";
    private static final String TABLE_TACHE = "notification";
    private static final String COL_ID = "id";
    private static final int NUM_COL_ID = 0;
    private static final String COL_MESSAGE = "message";
    private static final int NUM_COL_MESSAGE = 1;
    private static final String COL_DATE ="date";
    private static final int NUM_COL_DATE = 2;
    private static final String COL_IDUSER = "id_user";
    private static final int NUM_COL_IDUSER = 3;
    private static final String COL_RESULT = "result";
    private static final int NUM_COL_RESULT = 4;

    private Notification notification;
    private SQLiteDatabase bdd;
    private MaBaseSqlite maBaseSqlite;

    public NotificationBDD(Context context){
        //On crée la BDD et sa table
        maBaseSqlite = new MaBaseSqlite(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open() {

        //on ouvre la BDD en écriture
        bdd = maBaseSqlite.getWritableDatabase();
    }

    public void close () {

        bdd.close();
    }

    public SQLiteDatabase getBDD(){

        return bdd;
    }

    public void insertQuestion(Notification notification){

        bdd = maBaseSqlite.getWritableDatabase();

        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_MESSAGE, notification.getMessage());
        values.put(COL_DATE, notification.getDate());
        values.put(COL_IDUSER , notification.getId_user());
        values.put(COL_RESULT , notification.getResult());
        //on insère l'objet dans la BDD via le ContentValues
        bdd.insert(TABLE_TACHE, null, values);
    }

    public List<Notification> allNotification(){

        List<Notification> toutNotif= new ArrayList<Notification>();
        bdd = maBaseSqlite.getWritableDatabase();
        String selectQuery = "SELECT * FROM "+TABLE_TACHE ;
        Cursor c = bdd.rawQuery(selectQuery, null);
        if(c.moveToFirst()) do {

            Notification notif = new Notification();
            notif.setId(c.getInt(NUM_COL_ID));
            notif.setMessage(c.getString(NUM_COL_MESSAGE));
            notif.setDate(c.getString(NUM_COL_DATE));
            notif.setId_user(c.getInt(NUM_COL_IDUSER));
            notif.setResult(c.getString(NUM_COL_RESULT));
            toutNotif.add(notif);
        }

        while (c.moveToNext());
        return toutNotif;
    }

    public int getNbNotification() {

        bdd = maBaseSqlite.getWritableDatabase();
        String selectQuery = "SELECT * FROM "+TABLE_TACHE ;
        Cursor c = bdd.rawQuery(selectQuery,null);
        int count = c.getCount();
        bdd.close();
        c.close();
        return c.getCount();
    }
}
