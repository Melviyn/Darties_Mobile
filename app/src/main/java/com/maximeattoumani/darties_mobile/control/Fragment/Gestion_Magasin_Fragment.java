package com.maximeattoumani.darties_mobile.control.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.maximeattoumani.darties_mobile.R;
import com.maximeattoumani.darties_mobile.control.Adapter.MagasinAdapter;
import com.maximeattoumani.darties_mobile.model.Gestion;

import java.util.List;

/**
 * Created by Maxime on 11/01/2017.
 */

public class Gestion_Magasin_Fragment extends Fragment {

    ListView listN;
    View v;
    List<Gestion> magasin;
    private TextView nbmag;


    public Gestion_Magasin_Fragment() {

    }

    public void getGestion( List<Gestion> mag){
        this.magasin = mag;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(v == null){
            v = inflater.inflate(R.layout.gest_mag,container, false);
        }
        listN = (ListView) v.findViewById(R.id.update_all);

        Integer[] icon = new Integer[magasin.size()];

        for(int i=0; i<magasin.size();i++){

            icon[i] =  R.mipmap.magasin;
        }

        nbmag = (TextView) v.findViewById(R.id.nbmag);
        nbmag.setText(Html.fromHtml("Nombre(s) de magasin(s) non à jour : <b>" + magasin.size() + " magasin(s)</b>"));

        final MagasinAdapter adapter = new MagasinAdapter(getActivity().getApplicationContext(), magasin,icon);
        listN.setAdapter(adapter);

        return v;
    }



}
