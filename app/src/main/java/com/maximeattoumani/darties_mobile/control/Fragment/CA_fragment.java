package com.maximeattoumani.darties_mobile.control.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.maximeattoumani.darties_mobile.R;
import com.maximeattoumani.darties_mobile.control.Adapter.RowAccueilAdapter;
import com.maximeattoumani.darties_mobile.model.RowAccueil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvi on 25/11/2016.
 */

public class CA_fragment extends Fragment {

    private List<RowAccueil> info;
    ListView listCa;
    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(v == null){
            v = inflater.inflate(R.layout.ca_layout,container, false);
        }
        listCa = (ListView) v.findViewById(R.id.listCa);

        RowAccueilAdapter adapter = new RowAccueilAdapter(getActivity().getApplicationContext(), info);
        listCa.setAdapter(adapter);

        return v;
    }

    public void setList(List<RowAccueil> list){
        info = list;
    }
}
