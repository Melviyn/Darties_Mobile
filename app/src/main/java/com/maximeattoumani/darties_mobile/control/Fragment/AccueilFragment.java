package com.maximeattoumani.darties_mobile.control.Fragment;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maximeattoumani.darties_mobile.R;

/**
 * Created by Maxime on 09/12/2016.
 */

public class AccueilFragment extends android.support.v4.app.Fragment{

    private View rootView;

    public AccueilFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.accueil_main,container,false);
        return rootView;
    }


}
