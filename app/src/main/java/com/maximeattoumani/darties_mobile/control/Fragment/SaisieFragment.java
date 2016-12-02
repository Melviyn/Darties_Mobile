package com.maximeattoumani.darties_mobile.control.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maximeattoumani.darties_mobile.R;
import com.maximeattoumani.darties_mobile.model.SessionManager;
import com.maximeattoumani.darties_mobile.rest.ApiInterface;

/**
 * Created by melvi on 24/11/2016.
 */

public class SaisieFragment extends Fragment{

    private SessionManager session;
    private TextView id;
    private TextView nom;
    private TextView prenom;
    private String api;
    private ApiInterface apiService;

    public SaisieFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.saisie_layout,container,false);

        return rootView;
    }
}

