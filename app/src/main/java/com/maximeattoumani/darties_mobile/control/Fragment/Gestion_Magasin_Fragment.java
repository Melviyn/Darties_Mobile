package com.maximeattoumani.darties_mobile.control.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.maximeattoumani.darties_mobile.R;
import com.maximeattoumani.darties_mobile.control.Adapter.MagasinAdapter;
import com.maximeattoumani.darties_mobile.control.Adapter.NotificationAdapter;
import com.maximeattoumani.darties_mobile.model.Magasin;
import com.maximeattoumani.darties_mobile.model.Notification;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxime on 11/01/2017.
 */

public class Gestion_Magasin_Fragment extends Fragment {

    private List<Magasin> info;
    ListView listN;
    List<Magasin> notif;
    View v;

    public Gestion_Magasin_Fragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(v == null){
            v = inflater.inflate(R.layout.gest_mag,container, false);
        }
        listN = (ListView) v.findViewById(R.id.update_all);

        getNotif();

        final MagasinAdapter adapter = new MagasinAdapter(getActivity().getApplicationContext(), info);
        listN.setAdapter(adapter);


        return v;
    }

    public void setList(List<Magasin> list){
        info = list;
    }


    public void getNotif(){

        notif = new ArrayList<Magasin>();


        for(int i = 0; i < 6;i++){


            notif.add(new Magasin("Administrateur","22/09/2016"));
        }

        this.setList(notif);

    }


}
