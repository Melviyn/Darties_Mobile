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
import com.maximeattoumani.darties_mobile.control.Fragment.ModifCmpFragment;
import com.maximeattoumani.darties_mobile.control.Fragment.SaisieFragment;
import com.maximeattoumani.darties_mobile.control.Fragment.TableauSaisiFragment;
import com.maximeattoumani.darties_mobile.control.Fragment.TableauxFragment;
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
    private TextView name;
    private TextView mail;
    private ListView listN;
    ModifCmpFragment modifCmpFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dm_layout);

        session = new SessionManager(getApplicationContext());
        session.checkLogin();

        this.infoCompte();

        TableauxFragment fragment = new TableauxFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_main,fragment);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerview = navigationView.getHeaderView(0);

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

        if(id == R.id.delete){
            Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.action_modif){
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
            Toast.makeText(this, "Saisie", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.tabAcc) {
            TableauxFragment fragment = new TableauxFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_main,fragment);
            fragmentTransaction.commit();
            Toast.makeText(this, "Tableau", Toast.LENGTH_SHORT).show();

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

            AccueilFragment fragment = new AccueilFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_main,fragment);
            fragmentTransaction.commit();
        }

        else if(id == R.id.monComp){

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_main, comptefrg);
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
                            modifCmpFragment.userCourant(userInfo);
                            name.setText(userInfo.getPrenom()+" "+userInfo.getNom());
                            mail.setText(userInfo.getMail());

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

    public void onClick(View v) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_main, comptefrg);
        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

}