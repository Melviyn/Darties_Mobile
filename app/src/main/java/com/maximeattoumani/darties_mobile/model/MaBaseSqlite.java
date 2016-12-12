package com.maximeattoumani.darties_mobile.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Maxime on 11/12/2016.
 */

public class MaBaseSqlite extends SQLiteOpenHelper {

    private static final String TABLE_TACHE = "notification";
    private static final String COL_ID = "id";
    private static final String COL_MESSAGE = "message";
    private static final String COL_DATE ="date";
    private static final String COL_IDUSER = "id_user";
    private static final String COL_RESULT = "result";
    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_TACHE + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_MESSAGE + " TEXT , "+
            COL_DATE + " TEXT , "+
            COL_IDUSER + " INTEGER, "+
            COL_RESULT + " INTEGER );";

    public MaBaseSqlite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){

        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //on crée la table à partir de la requête écrite dans la variable CREATE_BDD
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //On peut faire ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
        //comme ça lorsque je change la version les id repartent de 0
        db.execSQL("DROP TABLE " + TABLE_TACHE + ";");
        onCreate(db);
    }

}
