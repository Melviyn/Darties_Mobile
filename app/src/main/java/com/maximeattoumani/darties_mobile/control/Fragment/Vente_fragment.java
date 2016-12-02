package com.maximeattoumani.darties_mobile.control.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.maximeattoumani.darties_mobile.R;
import com.maximeattoumani.darties_mobile.control.Adapter.RowAccueilAdapter;
import com.maximeattoumani.darties_mobile.model.RowAccueil;

import java.util.List;

/**
 * Created by melvi on 25/11/2016.
 */

public class Vente_fragment extends android.support.v4.app.Fragment {

    private List<RowAccueil> info;
    ListView listVente;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.vente_layout,container, false);
        listVente = (ListView) v.findViewById(R.id.listVente);

        RowAccueilAdapter adapter = new RowAccueilAdapter(getActivity().getApplicationContext(), info);
        listVente.setAdapter(adapter);
        return v;
    }

    public void setList(List<RowAccueil> list) {
        this.info = list;
    }
}
