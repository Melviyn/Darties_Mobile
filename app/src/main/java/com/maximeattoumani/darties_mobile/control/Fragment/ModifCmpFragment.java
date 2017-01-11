package com.maximeattoumani.darties_mobile.control.Fragment;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.maximeattoumani.darties_mobile.R;
import com.maximeattoumani.darties_mobile.model.SessionManager;
import com.maximeattoumani.darties_mobile.model.User;
import com.maximeattoumani.darties_mobile.rest.ApiInterface;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by Maxime on 01/12/2016.
 */

public class ModifCmpFragment extends android.support.v4.app.Fragment {

    private TextView id;
    private TextView nom;
    private TextView prenom;
    private TextView pwd;
    private TextView adresse;
    private EditText newAdress, newPwd;
    private Button logout;
    private Button valider;
    private ApiInterface apiService;
    private SessionManager session;
    private String api;
    private View rootView;
    private User user;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\.]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    /*Regex ci dessous pour le password :
    (?=.*\d)		#   must contains one digit from 0-9
    (?=.*[a-z])		#   must contains one lowercase characters
    (?=.*[A-Z])		#   must contains one uppercase characters
    {6,20}	        #   length at least 6 characters and maximum of 20
    */
    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";

    public ModifCmpFragment() {

    }

    public void userCourant(User user){

        this.user = user;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_modif_cmp,container,false);
        valider = (Button) rootView.findViewById(R.id.button_valider);
        newAdress = (EditText) rootView.findViewById(R.id.editText_adresse);
        newPwd = (EditText) rootView.findViewById(R.id.editText_pwd);



        id = (TextView) rootView.findViewById(R.id.id);
        nom = (TextView) rootView.findViewById(R.id.nom);
        prenom = (TextView) rootView.findViewById(R.id.prenom);
        pwd = (TextView) rootView.findViewById(R.id.pwd);
        adresse = (TextView) rootView.findViewById(R.id.adresse);

        id.setText(Html.fromHtml("Id : <b>" + user.getId_profil() + "</b>"));
        nom.setText(Html.fromHtml("Nom : <b>" + user.getNom() + "</b>"));
        prenom.setText(Html.fromHtml("Prénom : <b>" + user.getPrenom() + "</b>"));
        adresse.setText(Html.fromHtml("Email :"));
        pwd.setText(Html.fromHtml("Mot de passe :"));

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = true;
                if(verifAdress(newAdress.getText().toString())){
                    //TODO MISE A JOUR DU CHAMP EN BASE
                    System.out.println("Mail OK");
                    newAdress.setText("");
                } else{
                    System.out.println("Mail NONOK");
                    check = false;
                }

                if(verifPwd(newPwd.getText().toString())){
                    //TODO MISE A JOUR DU CHAMP EN BASE
                    System.out.println("Password OK");
                    newPwd.setText("");
                }else{
                    System.out.println("Password NONOK");
                    check = false;
                }
            }
        });

        return rootView;
    }

    public boolean verifAdress(String s){
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(s);
        boolean valid = matcher.matches();
        return valid;
    }

    public boolean verifPwd(String s){
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(s);
        boolean valid = matcher.matches();
        return valid;
    }

}
