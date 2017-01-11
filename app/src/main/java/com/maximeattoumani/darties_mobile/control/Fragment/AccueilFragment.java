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
import com.maximeattoumani.darties_mobile.model.ProduitAccueil;
import com.maximeattoumani.darties_mobile.model.RowAccueil;
import com.maximeattoumani.darties_mobile.model.User;
import com.maximeattoumani.darties_mobile.rest.ApiClient;
import com.maximeattoumani.darties_mobile.rest.ApiInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    List<Notification> test = new ArrayList<Notification>();
    View v;
    private int longClickItem;
    private NotificationAdapter adapter;
    private ApiInterface apiService;
    int jour,mois,annee,jourUp,moisUp,anneeUp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(v == null){
            v = inflater.inflate(R.layout.accueil_main,container, false);
        }
        listN = (ListView) v.findViewById(R.id.message);

        getNotif();

        adapter = new NotificationAdapter(getActivity().getApplicationContext(), info);
        listN.setAdapter(adapter);
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


    public void getNotif(){

        apiService = ApiClient.getClient();
        apiService.listFaitsVentesAsync("51ff65b83616450eeb197760ebe2ec42", new Callback<List<FaitsVentes>>() {
            @Override
            public void success(List<FaitsVentes> faitsVentes, Response response) {
                if (response.getStatus() == 200) if (faitsVentes != null) {

                    SimpleDateFormat formater1 = new SimpleDateFormat("dd");
                    SimpleDateFormat formater2 = new SimpleDateFormat("MM");
                    SimpleDateFormat formater3 = new SimpleDateFormat("yyyy");
                    Date aujourdhui = new Date();
                    jour = Integer.parseInt(formater1.format(aujourdhui));
                    mois=Integer.parseInt(formater2.format(aujourdhui));
                    annee=Integer.parseInt(formater3.format(aujourdhui));

                    for(int i=0; i<faitsVentes.size();i++) {

                        if(faitsVentes.get(i).getDATE_MAJ() != null){


                            String string = faitsVentes.get(i).getDATE_MAJ();
                            String[] parts = string.substring(0,10).split("-");

                            jourUp=Integer.parseInt(parts[2]);
                            moisUp=Integer.parseInt(parts[1]);
                            anneeUp=Integer.parseInt(parts[0]);

                           // System.out.println(anneeUp == 2017);
                            if(jour >=1 && jour <=10){
                                if(moisUp == mois && anneeUp==annee){

                                    if(jourUp >= 7 && jourUp <=10) {
                                        //message rappel
                                        test.add(new Notification("test","message de test",1,"16/02/1996"));
                                    }

                                }
                                else if(anneeUp == annee && moisUp < mois){
                                    //message!
                                    test.add(new Notification("test","message de test",1,"16/02/1996"));
                                }
                               /* else if(anneeUp < annee){
                                    //message!
                                    test.add(new Notification("test","message de test",1,"16/02/1996"));
                                }*/

                            }
                            else if(jour >=10 && jour <=20){
                                if(moisUp == mois && anneeUp==annee){

                                    if(jourUp >= 17 && jourUp <=20) {
                                        //message rappel
                                        test.add(new Notification("test","message de test",1,"16/02/1996"));
                                    }
                                    else{
                                        System.out.println("à jour");
                                    }

                                }
                                else if(anneeUp == annee && moisUp < mois){
                                    //message!
                                    test.add(new Notification("test","message de test",1,"16/02/1996"));
                                }
                               /* else if(anneeUp < annee){
                                    //message!
                                    test.add(new Notification("test","message de test",1,"16/02/1996"));
                                }*/
                            }
                            else if(jour >=20 && jour<=20) {
                                if (moisUp == mois && anneeUp == annee) {

                                    if (jourUp >= 28 && jourUp <= 31) {
                                        //message rappel
                                        test.add(new Notification("test","message de test",1,"16/02/1996"));
                                    }
                                }
                                else if (anneeUp == annee && moisUp < mois) {
                                    //message!
                                    test.add(new Notification("test","message de test",1,"16/02/1996"));
                                }
                               /*else if (anneeUp < annee) {
                                    //message!
                                    test.add(new Notification("test","message de test",1,"16/02/1996"));
                                }*/
                            }
                        }
                    }

                    System.out.println("notif :"+test.size());
                    System.out.println("vente :"+faitsVentes.size());
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

        notif = new ArrayList<Notification>();
        for(int i = 0; i < 6;i++){


            notif.add(new Notification("test","message de test",1,"16/02/1996"));
        }

        this.setList(notif);

    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
       // menu.setHeaderTitle("Option");
        menu.add(0, v.getId(), 0, "Effacer le message");
    }
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "Effacer le message") {
            notif.remove(longClickItem);
            adapter.notifyDataSetChanged();
        }
        return true;
    }




}
