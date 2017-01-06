package com.maximeattoumani.darties_mobile.control.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.util.SortedList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.maximeattoumani.darties_mobile.R;
import com.maximeattoumani.darties_mobile.control.Activity.MainActivity;
import com.maximeattoumani.darties_mobile.model.FamProd;
import com.maximeattoumani.darties_mobile.model.SessionManager;
import com.maximeattoumani.darties_mobile.rest.ApiClient;
import com.maximeattoumani.darties_mobile.rest.ApiInterface;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by melvi on 24/11/2016.
 */

public class SaisieFragment extends Fragment{
    private SessionManager session;
    private String api;
    private ApiInterface apiService;
    private ArrayList<FamProd> prods;
    private int nbProd;
    private static int prodActu=0;
    TextView prodName;
    private Button back,next;
    private  String[] nomMois = { "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet",
            "Août", "Septembre", "Octobre", "Novembre", "Décembre" };

    private HashMap<String,Integer> tabSaisi;
    public SaisieFragment(){

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.saisie_layout,container,false);
        prods = new ArrayList<FamProd>();
        tabSaisi=new HashMap<String,Integer>();
        prodName= (TextView) rootView.findViewById((R.id.prodName));
        next = (Button) rootView.findViewById(R.id.next);
        next.setVisibility(next.INVISIBLE);
        if(prodActu!=2){

            next.setVisibility(next.VISIBLE);
        }


        TextView moisVentes = (TextView) rootView.findViewById(R.id.moisVentes);
        RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.radioGroup);
        //EditText saisie=(EditText) rootView.findViewById(R.id.Valeurs);
        TextView objectif = (TextView) rootView.findViewById((R.id.moisVentesObjVal)) ;
        objectif.setText("1500");
        TextView ventes = (TextView) rootView.findViewById((R.id.moisVentesReelVal)) ;
        ventes.setText("500");

        final EditText ventesSaisi = (EditText) rootView.findViewById(R.id.saisieVentes);
        final EditText margeSaisi = (EditText) rootView.findViewById(R.id.SaisiMarge);
        final EditText caSaisi = (EditText) rootView.findViewById(R.id.SaisiChiffreAffaire);

        final ProgressBar progBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        //Button bValider= (Button) rootView.findViewById(R.id.buttonValide) ;

        Calendar cal = Calendar.getInstance();
        moisVentes.setText( nomMois[cal.get(Calendar.MONTH)]);

        //Session
        session = new SessionManager(getActivity().getApplicationContext());
        session.checkLogin();
        HashMap<String,String> user = session.getAPI();
        api = user.get(SessionManager.KEY_API);
        // END SESSION





        apiService = ApiClient.getClient();
        apiService.listFamProduitAsync(api, new Callback<List<FamProd>>() {
            /**
             * Successful HTTP response.
             *
             * @param famProds
             * @param response
             */
            @Override
            public void success(List<FamProd> famProds, Response response) {


                nbProd=famProds.size();
                System.out.println("Nombre de produit : "+nbProd);
                //for(int i =0;i< nbProd;i++){
                    FamProd tmp = new FamProd();
                    tmp.setDATEMAJ_FAMPROD(famProds.get(prodActu).getDATEMAJ_FAMPROD());
                    tmp.setID_FAMILLE_PRODUIT(famProds.get(prodActu).getID_FAMILLE_PRODUIT());
                    tmp.setLIB_FAMILLE_PRODUIT(famProds.get(prodActu).getLIB_FAMILLE_PRODUIT());
                    prods.add(tmp);

                //}
                prodName.setText(prods.get(0).getLIB_FAMILLE_PRODUIT());

            }

            /**
             * Unsuccessful HTTP response due to network failure, non-2XX status code, or unexpected
             * exception.
             *
             * @param error
             */
            @Override
            public void failure(RetrofitError error) {
                Log.d("Error", error.getMessage());
                System.out.println("Impossible de récuperer les informations, ERREUR "+error.getResponse().getStatus());
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*tabSaisi.put("ventes",Integer.parseInt(ventesSaisi.getText().toString()) );
                tabSaisi.put("marge",Integer.parseInt(margeSaisi.getText().toString()) );
                tabSaisi.put("ca",Integer.parseInt(caSaisi.getText().toString()) );*/

            }
        });


        //Barre de progression de l'objectif
        progBar.setMax(Integer.parseInt( objectif.getText().toString()));
        progBar.setProgress(Integer.parseInt( ventes.getText().toString()));




        return rootView;
    }
    public void setItemNb(int nb){

        this.prodActu=nb;
    }

}

