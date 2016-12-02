package com.maximeattoumani.darties_mobile.control.Fragment;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.maximeattoumani.darties_mobile.R;
import com.maximeattoumani.darties_mobile.model.SessionManager;
import com.maximeattoumani.darties_mobile.model.User;
import com.maximeattoumani.darties_mobile.rest.ApiInterface;

/**
 * Created by Maxime on 01/12/2016.
 */

public class CmpFragment extends android.support.v4.app.Fragment {

    private TextView id;
    private TextView nom;
    private TextView prenom;
    private TextView pwd;
    private TextView adresse;
    private Button logout;
    private ApiInterface apiService;
    private SessionManager session;
    private String api;
    private View rootView;
    private User user;

    public CmpFragment() {

    }

    public void userCourant(User user){

        this.user = user;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.user_layout,container,false);

        id = (TextView) rootView.findViewById(R.id.id);
        nom = (TextView) rootView.findViewById(R.id.nom);
        prenom = (TextView) rootView.findViewById(R.id.prenom);
        pwd = (TextView) rootView.findViewById(R.id.pwd);
        adresse = (TextView) rootView.findViewById(R.id.adresse);

        id.setText(Html.fromHtml("Id : <b>" + user.getId_profil() + "</b>"));
        nom.setText(Html.fromHtml("Nom : <b>" + user.getNom() + "</b>"));
        prenom.setText(Html.fromHtml("Prénom : <b>" + user.getPrenom() + "</b>"));
        adresse.setText(Html.fromHtml("Email : <b>" + user.getMail() + "</b>"));
        pwd.setText(Html.fromHtml("Mot de passe : <b>" + user.getPassword() + "</b>"));

        return rootView;
    }
}
