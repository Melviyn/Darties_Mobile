package com.maximeattoumani.darties_mobile.model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.maximeattoumani.darties_mobile.control.Activity.LoginActivity;
import com.maximeattoumani.darties_mobile.control.Activity.MainActivity;

import java.util.HashMap;

/**
 * Created by Maxime ATTOUMANI on 16/11/2016.
 */
public class SessionManager {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context _context;
    private int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "AndroidHivePref";
    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String KEY_API = "apikey";


    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    //Ajoutes dans une variable de session le login = true et le token
    public void createLoginSession(String apikey){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_API, apikey);
        editor.commit();
    }

    public HashMap<String, String> getAPI(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_API, pref.getString(KEY_API, null));
        return user;
    }

    public void addValueString(String key, String value){
        editor.putString(key,value);
        editor.commit();
    }

    /*Vérifie si la variable de session est toujours à true
    sinon on redirige l'utilisateur sur l'interface connexion*/
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

        else {
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }

    public String getKeyApi(){
        return pref.getString(KEY_API,null);
    }

    public String getStringValue(String key){
        return pref.getString(key,null);
    }
    //Supprime tous les variable de session et redirige l'utilisateur sur l'interface connexion
    public void logoutUser(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(_context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);

    }

}
