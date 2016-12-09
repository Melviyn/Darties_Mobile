package com.maximeattoumani.darties_mobile.control.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.maximeattoumani.darties_mobile.R;
import com.maximeattoumani.darties_mobile.model.Profil;
import com.maximeattoumani.darties_mobile.model.SessionManager;
import com.maximeattoumani.darties_mobile.model.User;
import com.maximeattoumani.darties_mobile.rest.ApiClient;
import com.maximeattoumani.darties_mobile.rest.ApiInterface;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button connexion;
    private SessionManager session;
    private String api;
    private ApiInterface apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        connexion = (Button) findViewById(R.id.connexion);

        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail = email.getText().toString();
                String pwd = password.getText().toString();

                if(mail.trim().length() > 0 && pwd.trim().length() > 0) {

                    if(!mail.equals("") && !password.equals("")){

                        apiService = ApiClient.getClient();
                        session = new SessionManager(getApplicationContext());
                        apiService.listApiAsync(mail, pwd, new Callback<List<User>>() {
                            @Override
                            public void success(List<User> users, Response response) {
                                if (response.getStatus() == 200) {
                                    if (users != null) {
                                        User user = users.get(0);
                                        session.createLoginSession(user.getApi_key());
                                        api = session.getKeyApi();
                                        apiService.listProfilAsync(api, new Callback<List<Profil>>() {

                                            @Override
                                            public void success(List<Profil> prof, Response response) {
                                                if (response.getStatus() == 200) {
                                                    if (prof != null) {
                                                        Profil profil = prof.get(0);
                                                        session.addValueString("LIB_PROFIL",profil.getLib_profil());
                                                        session.addValueString("id_zone",profil.getId_zone());
                                                    }
                                                }
                                            }

                                            @Override
                                            public void failure(RetrofitError error) {

                                                Log.d("Error", error.getMessage());
                                            }

                                        });

                                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                }
                            }

                            @Override
                            public void failure(RetrofitError error) {

                                Log.d("Error", error.getMessage());
                                Toast.makeText(LoginActivity.this, "Vous n'êtes pas membre de Darties, vérifier votre connexion Internet ou inscrivez-vous chez Darties puis réessayer", Toast.LENGTH_LONG).show();
                            }

                        });



                    }
                }
                else if(mail.trim().length() > 0){
                    Toast.makeText(LoginActivity.this, "Veuillez renseigner le mot de passe", Toast.LENGTH_LONG).show();
                }
                else if(pwd.trim().length() > 0){
                    Toast.makeText(LoginActivity.this, "Veuillez renseigner l'adresse mail", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(LoginActivity.this, "Veuillez saisir l'email et le mot de passe", Toast.LENGTH_LONG).show();
                }

            }
        });

    }


}

