package com.maximeattoumani.darties_mobile.control.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.maximeattoumani.darties_mobile.R;

/**
 * Created by melvi on 24/11/2016.
 */

public class SaisieFragment extends Fragment{

    public SaisieFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.saisie_layout,container,false);

        RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.radioGroup);
        EditText saisie=(EditText) rootView.findViewById(R.id.Valeurs);
        TextView objectif = (TextView) rootView.findViewById((R.id.moisVentesObjVal)) ;
        TextView ventes = (TextView) rootView.findViewById((R.id.moisVentesReelVal)) ;
        ProgressBar progBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        Button bValider= (Button) rootView.findViewById(R.id.buttonValide) ;

        progBar.setMax(Integer.getInteger( objectif.getText().toString()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            progBar.setProgress(Integer.getInteger( ventes.getText().toString()),true);
        }




        return rootView;
    }
}

