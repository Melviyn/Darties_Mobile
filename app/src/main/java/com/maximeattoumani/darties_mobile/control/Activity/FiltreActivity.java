package com.maximeattoumani.darties_mobile.control.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.maximeattoumani.darties_mobile.R;
import com.maximeattoumani.darties_mobile.model.Enseigne;
import com.maximeattoumani.darties_mobile.model.Geographie;
import com.maximeattoumani.darties_mobile.model.Profil;
import com.maximeattoumani.darties_mobile.model.SessionManager;
import com.maximeattoumani.darties_mobile.model.User;
import com.maximeattoumani.darties_mobile.rest.ApiClient;
import com.maximeattoumani.darties_mobile.rest.ApiInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by melvi on 05/12/2016.
 */

public class FiltreActivity extends Activity {

    String géo;
    String enseigne;
    String temps;

    private String api;
    private SessionManager session;
    private ApiInterface apiService;
    private List<Geographie> listGeo;
    private List<Enseigne> listEns;
    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtre_layout);

        // Spinner element
        spinner1 = (Spinner) findViewById(R.id.Géo);
        spinner2 = (Spinner) findViewById(R.id.enseigne);
        spinner3 = (Spinner) findViewById(R.id.periode);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                géo = parent.getItemAtPosition(position).toString();
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                géo = arg0.getItemAtPosition(0).toString();
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                enseigne = parent.getItemAtPosition(position).toString();
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                enseigne = arg0.getItemAtPosition(0).toString();
            }
        });

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                temps = parent.getItemAtPosition(position).toString();
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                temps = arg0.getItemAtPosition(0).toString();
            }
        });


        final List<String> listSpinGeo = new ArrayList<String>();
        final List<String> listSpinEns = new ArrayList<String>();

        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        HashMap<String,String> user = session.getAPI();
        api = user.get(SessionManager.KEY_API);
        apiService = ApiClient.getClient();

        final ProgressDialog ringProgressDialog = ProgressDialog.show(this, "Veuillez patienter", "récupération de données ...", true);
        ringProgressDialog.setCancelable(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
        apiService.listGeoAsync(api, new Callback<List<Geographie>>() {
            @Override
            public void success(List<Geographie> geo, Response response) {
                if (response.getStatus() == 200) {
                    listGeo = geo;
                    for(int i=0;i <geo.size();i++){
                        System.out.println(geo.get(i).getLIBELLE());
                        listSpinGeo.add(geo.get(i).getLIBELLE());
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {

                Log.d("Error", error.getMessage());
            }

        });


        apiService.listEnseigneAsync(api, new Callback<List<Enseigne>>() {
            @Override
            public void success(List<Enseigne> ens, Response response) {
                if (response.getStatus() == 200) {
                    listEns = ens;
                    System.out.println(ens.size());
                    for(int i=0;i <ens.size();i++){
                        System.out.println(ens.get(i).getLib_enseigne());
                        listSpinEns.add(ens.get(i).getLib_enseigne());
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {

                Log.d("Error", error.getMessage());
            }

        });

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
                ringProgressDialog.dismiss();
            }
        }).start();
    // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Automobile");
        categories.add("Business Services");
        categories.add("Computers");
        categories.add("Education");
        categories.add("Personal");
        categories.add("Travel");

        ArrayAdapter<String> dataAdapterGeo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listSpinGeo);
        dataAdapterGeo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapterGeo);

        ArrayAdapter<String> dataAdapterEns = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listSpinEns);
        dataAdapterEns.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapterEns);
        /*Intent resultIntent = new Intent();
        resultIntent.putExtra("myResult", "test");
        setResult(Activity.RESULT_OK, resultIntent);
        finish();*/
    }
}
