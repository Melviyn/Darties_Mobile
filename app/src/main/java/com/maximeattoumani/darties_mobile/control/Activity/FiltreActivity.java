package com.maximeattoumani.darties_mobile.control.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.maximeattoumani.darties_mobile.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvi on 05/12/2016.
 */

public class FiltreActivity extends Activity {

    String géo;
    String enseigne;
    String temps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtre_layout);

        // Spinner element
        Spinner spinner1 = (Spinner) findViewById(R.id.Géo);
        Spinner spinner2 = (Spinner) findViewById(R.id.enseigne);
        Spinner spinner3 = (Spinner) findViewById(R.id.periode);

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


        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Automobile");
        categories.add("Business Services");
        categories.add("Computers");
        categories.add("Education");
        categories.add("Personal");
        categories.add("Travel");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner1.setAdapter(dataAdapter);
        spinner2.setAdapter(dataAdapter);
        spinner3.setAdapter(dataAdapter);

        /*Intent resultIntent = new Intent();
        resultIntent.putExtra("myResult", "test");
        setResult(Activity.RESULT_OK, resultIntent);
        finish();*/
    }
}
