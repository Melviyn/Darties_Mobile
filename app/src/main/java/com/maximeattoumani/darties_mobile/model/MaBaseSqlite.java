package com.maximeattoumani.darties_mobile.model;

/**
 * Created by Maxime on 12/01/2017.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MaBaseSqlite extends SQLiteOpenHelper {

    private static final String TABLE_TACHE = "Notification";
    private static final String COL_TITRE = "Titre";
    private static final String COL_MESSAGE = "Message";
    private static final String COL_DATE ="Date";
    private static final String COL_LIBELLE="Libelle";
    private static final String COL_ID="Id";
    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_TACHE + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_TITRE + " TEXT, " +
            COL_MESSAGE + " TEXT, "+
            COL_DATE + " TEXT, "+
            COL_LIBELLE + " TEXT NOT NULL);";

    public MaBaseSqlite(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){

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
