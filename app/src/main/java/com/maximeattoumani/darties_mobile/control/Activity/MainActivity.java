package com.maximeattoumani.darties_mobile.control.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.maximeattoumani.darties_mobile.R;
import com.maximeattoumani.darties_mobile.control.Fragment.AccueilFragment;
import com.maximeattoumani.darties_mobile.control.Fragment.CmpFragment;
import com.maximeattoumani.darties_mobile.control.Fragment.Gestion_Magasin_Fragment;
import com.maximeattoumani.darties_mobile.control.Fragment.ModifCmpFragment;
import com.maximeattoumani.darties_mobile.control.Fragment.SaisieFragment;
import com.maximeattoumani.darties_mobile.control.Fragment.TableauSaisiFragment;
import com.maximeattoumani.darties_mobile.control.Fragment.TableauxFragment;
import com.maximeattoumani.darties_mobile.model.Gestion;
import com.maximeattoumani.darties_mobile.model.Profil;
import com.maximeattoumani.darties_mobile.model.SessionManager;
import com.maximeattoumani.darties_mobile.model.User;
import com.maximeattoumani.darties_mobile.rest.ApiClient;
import com.maximeattoumani.darties_mobile.rest.ApiInterface;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {


    NavigationView navigationView;
    Toolbar toolbar;
    private ImageView image;

    private ApiInterface apiService;
    private SessionManager session;
    private String api;
    CmpFragment comptefrg;

    private User userInfo;
    private Profil profilInfo;
    private TextView name;
    private TextView mail;
    private ListView listN;
    ModifCmpFragment modifCmpFragment;
    AccueilFragment accueil;
    Gestion_Magasin_Fragment fragment;
    Menu nav_Menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dm_layout);

        session = new SessionManager(getApplicationContext());
        session.checkLogin();


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerview = navigationView.getHeaderView(0);
        nav_Menu = navigationView.getMenu();
        this.infoCompte();
        this.gestion_utilisateur();

        image = (ImageView) headerview.findViewById(R.id.imageView) ;
        image.setOnClickListener(this);

        name = (TextView) headerview.findViewById(R.id.nomprenom);
        mail = (TextView) headerview.findViewById(R.id.mail);

        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }*/

        AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
        builder.setIcon(R.mipmap.deco);
        builder.setTitle("Déconnexion");
        builder.setInverseBackgroundForced(true);
        builder.setMessage("Voulez-vous vraiment quitter l'application ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                session.logoutUser();
            }
        });
        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_modif){
            Toast.makeText(this,"modif",Toast.LENGTH_LONG).show();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_main, modifCmpFragment);
            fragmentTransaction.commit();

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }



        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.saisie) {
            TableauSaisiFragment fragment = new TableauSaisiFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_main,fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.tabAcc) {
            TableauxFragment fragment = new TableauxFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_main,fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.deconnect) {

            AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
            builder.setIcon(R.mipmap.deco);
            builder.setTitle("Déconnexion");
            builder.setInverseBackgroundForced(true);
            builder.setMessage("Voulez-vous vraiment quitter l'application ?");
            builder.setCancelable(false);
            builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {

                    session.logoutUser();
                }
            });
            builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alert=builder.create();
            alert.show();
        }

        else if(id == R.id.acc){

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_main,accueil);
            fragmentTransaction.commit();
        }

        else if(id == R.id.monComp){

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_main, comptefrg);
            fragmentTransaction.commit();
        }

        else if(id == R.id.gestion){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_main,fragment);
            fragmentTransaction.commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void infoCompte(){
        api = session.getKeyApi();
        apiService = ApiClient.getClient();
        apiService.listUserAsync(api, new Callback<List<User>>() {
            @Override
            public void success(List<User> users, Response response) {
                if (response.getStatus() == 200) {
                    if(users!= null){
                        userInfo = users.get(0);
                        if(api.equals(userInfo.getApi_key())) {
                            comptefrg = new CmpFragment();
                            comptefrg.userCourant(userInfo);
                            modifCmpFragment = new ModifCmpFragment();
                            accueil = new AccueilFragment();
                            modifCmpFragment.userCourant(userInfo);
                            accueil.NotifCourant(userInfo);
                            name.setText(userInfo.getPrenom()+" "+userInfo.getNom());
                            mail.setText(userInfo.getMail());
                            String libelle = userInfo.getLib_profil();

                            if(libelle.equals("Directeur Nord_Est") || libelle.equals("Directeur Sud_Ouest")
                || libelle.equals("Directeur Sud_Est") || libelle.equals("Directeur Region_parisienne")
                || libelle.equals("Directeur Nord_Ouest")){
                    nav_Menu.findItem(R.id.saisie).setVisible(false);
                    nav_Menu.findItem(R.id.acc).setVisible(false);
                            }
                            else {
                                nav_Menu.findItem(R.id.gestion).setVisible(false);
                                nav_Menu.performIdentifierAction(R.id.acc, 0);
                            }

                        }
                    }

                }
            }
            @Override
            public void failure(RetrofitError error) {
                Log.d("Error", error.getMessage());
            }
        });

    }

    private void gestion_utilisateur(){
        api = session.getKeyApi();
        apiService = ApiClient.getClient();
        apiService.listProfilAsync(api, new Callback<List<Profil>>() {
            @Override
            public void success(List<Profil> profils, Response response) {
                profilInfo = profils.get(0);
                if(profilInfo.getType_zone().equals("1")){
                    String libelle = profilInfo.getLib_profil();
                    String[] parts = libelle.split(" ");
                    apiService.gestion_utilisateur(parts[1], new Callback<List<Gestion>>() {
                        @Override
                        public void success(List<Gestion> gestions, Response response) {
                            fragment = new Gestion_Magasin_Fragment();
                            fragment.getGestion(gestions);
                            nav_Menu.performIdentifierAction(R.id.gestion, 0);

                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }

    public void onClick(View v) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_main, comptefrg);
        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

}