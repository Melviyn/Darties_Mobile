package com.maximeattoumani.darties_mobile.control.Fragment;

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

public class Marge_fragment extends android.support.v4.app.Fragment {

    private List<RowAccueil> info;
    ListView listMarge;
    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(v == null){
            v = inflater.inflate(R.layout.marge_layout,container, false);
        }


        listMarge = (ListView) v.findViewById(R.id.listMarge);

        RowAccueilAdapter adapter = new RowAccueilAdapter(getActivity().getApplicationContext(), info);
        listMarge.setAdapter(adapter);

        return v;
    }

    public void setList(List<RowAccueil> list) {
        this.info = list;
    }
}
