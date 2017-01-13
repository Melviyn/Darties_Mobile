package com.maximeattoumani.darties_mobile.control.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
import com.maximeattoumani.darties_mobile.model.FaitsVentes;
import com.maximeattoumani.darties_mobile.model.Notification;
import com.maximeattoumani.darties_mobile.model.NotificationBDD;
import com.maximeattoumani.darties_mobile.model.ProduitAccueil;
import com.maximeattoumani.darties_mobile.model.Profil;
import com.maximeattoumani.darties_mobile.model.RowAccueil;
import com.maximeattoumani.darties_mobile.model.User;
import com.maximeattoumani.darties_mobile.rest.ApiClient;
import com.maximeattoumani.darties_mobile.rest.ApiInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
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
    List<Notification> notif = new ArrayList<Notification>();
    List<Notification> verifMessage = new ArrayList<Notification>();
    View v;
    private int longClickItem;
    private NotificationAdapter adapter;
    private ApiInterface apiService;
    private NotificationBDD notBDD;
    private User user;
    private TextView recept;

    public AccueilFragment(){

    }

    public void NotifCourant(User users){

        this.user = users;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        notBDD = new NotificationBDD(getActivity().getApplicationContext());

        if(v == null){
            v = inflater.inflate(R.layout.accueil_main,container, false);
        }
        listN = (ListView) v.findViewById(R.id.message);
        recept = (TextView) v.findViewById(R.id.reception);



        verifMessage = notBDD.sortByLibelle(user.getLib_profil());

        Date aujourdhui = new Date();SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        String date = formater.format(aujourdhui);

        if(!user.getMessage().equals("") && !MessageExists(user)){
            notBDD.insertNotfication(new Notification(user.getMessage(),"Objet : Saisie donnée",date,user.getLib_profil()));
        }

        if(notBDD.getNbTache(user.getLib_profil()) != 0) {
            notif.addAll(notBDD.sortByLibelle(user.getLib_profil()));
        }
        recept.setText(Html.fromHtml("Boite de reception : <b>" + notif.size() + " message(s)</b>"));



        this.setList(notif);

        this.initialise();

        registerForContextMenu(listN);

         listN.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Notification selectedItem = (Notification) parent.getItemAtPosition(position);
                longClickItem = position;
                return false;
            }
        });

       return v;
    }

    public void setList(List<Notification> list){
        info = list;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
       // menu.setHeaderTitle("Option");
        menu.add(0, v.getId(), 0, "Effacer le message");
    }
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "Effacer le message") {
            notBDD.removeTache(notif.get(longClickItem));
            notif.remove(longClickItem);
            apiService = ApiClient.getClient();
            apiService.deleteMessage(user.getId_profil(), new Callback<String>() {
                @Override
                public void success(String s, Response response) {
                    recept.setText(Html.fromHtml("Boite de reception : <b>" + notif.size() + " message(s)</b>"));
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
            adapter.notifyDataSetChanged();
        }
        return true;
    }

    public void initialise(){
        adapter = new NotificationAdapter(getActivity().getApplicationContext(), info);
        listN.setAdapter(adapter);
    }

    private boolean MessageExists(User not) {
        String message = not.getMessage();
        int TacheCount = verifMessage.size();

        for (int i = 0; i < TacheCount; i++) {
            if (message.compareToIgnoreCase(verifMessage.get(i).getMessage()) == 0)
                return true;
        }
        return false;
    }




}
