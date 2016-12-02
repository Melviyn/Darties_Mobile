package com.maximeattoumani.darties_mobile.control.Activity;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.maximeattoumani.darties_mobile.R;
import com.maximeattoumani.darties_mobile.control.Fragment.CmpFragment;
import com.maximeattoumani.darties_mobile.control.Fragment.SaisieFragment;
import com.maximeattoumani.darties_mobile.control.Fragment.TableauxFragment;
import com.maximeattoumani.darties_mobile.model.SessionManager;
import com.maximeattoumani.darties_mobile.model.User;
import com.maximeattoumani.darties_mobile.rest.ApiClient;
import com.maximeattoumani.darties_mobile.rest.ApiInterface;

import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    private Button compte;
    NavigationView navigationView;
    Toolbar toolbar;
    private ImageView image;

    private TextView id;
    private TextView nom;
    private TextView prenom;
    private Button logout;
    private ApiInterface apiService;
    private SessionManager session;
    private String api;
    CmpFragment comptefrg;




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

        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.saisie) {
            SaisieFragment fragment = new SaisieFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_main,fragment);
            fragmentTransaction.commit();
            Toast.makeText(this, "Saisie", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.tab) {
            TableauxFragment fragment = new TableauxFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_main,fragment);
            fragmentTransaction.commit();
            Toast.makeText(this, "Tableau", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.deconnect) {
            //session = new SessionManager(getApplicationContext());
            session.logoutUser();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void infoCompte(){
        HashMap<String,String> user = session.getAPI();
        api = user.get(SessionManager.KEY_API);
        apiService = ApiClient.getClient();

        final ProgressDialog ringProgressDialog = ProgressDialog.show(MainActivity.this, "Veuillez patienter", "récupération de données ...", true);
        ringProgressDialog.setCancelable(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
        apiService.listUserAsync(api, new Callback<List<User>>() {
            @Override
            public void success(List<User> users, Response response) {
                if (response.getStatus() == 200) {
                    if(users!= null){
                        User user = users.get(0);
                        System.out.println(user);
                        if(api.equals(user.getApi_key())) {
                            comptefrg = new CmpFragment();
                            comptefrg.userCourant(user);
                        }
                    }

                }
            }
            @Override
            public void failure(RetrofitError error) {
                Log.d("Error", error.getMessage());
            }
        });

            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }
            ringProgressDialog.dismiss();
        }
    }).start();
    }

    public void onClick(View v) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_main, comptefrg);
        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

}