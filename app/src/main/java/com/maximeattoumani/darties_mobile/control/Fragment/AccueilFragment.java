package com.maximeattoumani.darties_mobile.control.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.maximeattoumani.darties_mobile.R;
import com.maximeattoumani.darties_mobile.control.Adapter.MyFragmentPagerAdapter;
import com.maximeattoumani.darties_mobile.control.Adapter.NotificationAdapter;
import com.maximeattoumani.darties_mobile.control.Adapter.RowAccueilAdapter;
import com.maximeattoumani.darties_mobile.model.Notification;
import com.maximeattoumani.darties_mobile.model.ProduitAccueil;
import com.maximeattoumani.darties_mobile.model.RowAccueil;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Maxime on 09/12/2016.
 */

public class AccueilFragment extends android.support.v4.app.Fragment{

    private View rootView;

    private List<Notification> info;
    ListView listN;
    List<Notification> notif;
    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(v == null){
            v = inflater.inflate(R.layout.accueil_main,container, false);
        }
        listN = (ListView) v.findViewById(R.id.message);

        getNotif();

        NotificationAdapter adapter = new NotificationAdapter(getActivity().getApplicationContext(), info);
        listN.setAdapter(adapter);

       return v;
    }

    public void setList(List<Notification> list){
        info = list;
    }


    public void getNotif(){

        notif = new ArrayList<Notification>();


        for(int i = 0; i < 6;i++){


            notif.add(new Notification("test","message de test",1,"16/02/1996"));
        }

        this.setList(notif);

    }


}
