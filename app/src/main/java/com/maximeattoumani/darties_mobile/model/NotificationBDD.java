package com.maximeattoumani.darties_mobile.model;

/**
 * Created by Maxime on 12/01/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class NotificationBDD {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD ="notif.db";
    private static final String TABLE_TACHE = "Notification";
    private static final String COL_ID = "Id";
    private static final int NUM_COL_ID = 0;
    private static final String COL_TITRE = "Titre";
    private static final int NUM_COL_TITRE = 1;
    private static final String COL_MESSAGE = "Message";
    private static final int NUM_COL_MESSAGE = 2;
    private static final String COL_DATE ="Date";
    private static final int NUM_COL_DATE = 3;
    private static final String COL_LIBELLE ="Libelle";
    private static final int NUM_COL_LIBELLE = 4;
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

    public void insertNotfication(Notification not){

        bdd = maBaseSqlite.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_TITRE, not.getTitle());
        values.put(COL_MESSAGE, not.getMessage());
        values.put(COL_DATE , not.getDate());
        values.put(COL_LIBELLE , not.getLibelle());
        bdd.insert(TABLE_TACHE, null, values);
        System.out.println("INSERT SUCCESSFULL");
    }

    public void removeTacheWithTitre(int id){
        //Suppression d'un livre de la BDD grâce àu titre
        bdd = maBaseSqlite.getWritableDatabase();
        bdd.delete(TABLE_TACHE, COL_ID + " = " + id, null);
    }

    public void removeTache(Notification not) {

        bdd = maBaseSqlite.getWritableDatabase();
        bdd.delete(TABLE_TACHE, COL_ID+ "=?", new String[]{String.valueOf(not.getId())});
        bdd.close();
    }

    public List<Notification> sortByLibelle(String libelle){

        List<Notification> toutQuest = new ArrayList<Notification>();
        bdd = maBaseSqlite.getWritableDatabase();
        String whereClause = "Libelle = ?";
        String[] whereArgs = new String[] {libelle};
        Cursor c = bdd.query(TABLE_TACHE , new String[]{String.valueOf("*")}, whereClause, whereArgs, null, null, null);
        if(c.moveToFirst()) do {

            Notification quest = new Notification();
            quest.setId(c.getInt(NUM_COL_ID));
            quest.setTitle(c.getString(NUM_COL_TITRE));
            quest.setMessage(c.getString(NUM_COL_MESSAGE));
            quest.setDate(c.getString(NUM_COL_DATE));
            quest.setLibelle(c.getString(NUM_COL_LIBELLE));
            toutQuest.add(quest);
        }

        while (c.moveToNext());
        return toutQuest;
    }

    public void removeAll(){
        bdd = maBaseSqlite.getWritableDatabase();
        bdd.execSQL("delete from " + TABLE_TACHE);
        bdd.close();
    }

    public int getNbNotifByLibelle(String libelle) {

        bdd = maBaseSqlite.getWritableDatabase();
        String whereClause = "Libelle = ?";
        String[] whereArgs = new String[] {libelle};
        Cursor c = bdd.query(TABLE_TACHE , new String[]{String.valueOf("*")}, whereClause, whereArgs, null, null, null);
        int count = c.getCount();
        bdd.close();
        c.close();
        return c.getCount();
    }

    public int getNbTache(String libelle) {

        bdd = maBaseSqlite.getWritableDatabase();
        String selectQuery = "SELECT * FROM "+TABLE_TACHE ;
        String whereClause = "Libelle = ?";
        String[] whereArgs = new String[] {libelle};
        Cursor c = bdd.query(TABLE_TACHE , new String[]{String.valueOf("*")}, whereClause, whereArgs, null, null, null);
        int count = c.getCount();
        bdd.close();
        c.close();

        return c.getCount();
    }

    public List<Notification> allNotif(){

        List<Notification> toutQuest = new ArrayList<Notification>();
        bdd = maBaseSqlite.getWritableDatabase();
        String selectQuery = "SELECT * FROM "+TABLE_TACHE ;
        Cursor c = bdd.rawQuery(selectQuery, null);
        if(c.moveToFirst()) do {

            Notification quest = new Notification();
            quest.setId(c.getInt(NUM_COL_ID));
            quest.setTitle(c.getString(NUM_COL_TITRE));
            quest.setMessage(c.getString(NUM_COL_MESSAGE));
            quest.setDate(c.getString(NUM_COL_DATE));
            quest.setLibelle(c.getString(NUM_COL_LIBELLE));
            toutQuest.add(quest);
        }

        while (c.moveToNext());
        return toutQuest;
    }




}
