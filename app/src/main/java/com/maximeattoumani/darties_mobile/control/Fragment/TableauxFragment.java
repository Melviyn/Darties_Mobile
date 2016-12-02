package com.maximeattoumani.darties_mobile.control.Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.maximeattoumani.darties_mobile.R;
import com.maximeattoumani.darties_mobile.control.Activity.LoginActivity;
import com.maximeattoumani.darties_mobile.control.Activity.MainActivity;
import com.maximeattoumani.darties_mobile.control.Adapter.MyFragmentPagerAdapter;
import com.maximeattoumani.darties_mobile.model.ProduitAccueil;
import com.maximeattoumani.darties_mobile.model.RowAccueil;
import com.maximeattoumani.darties_mobile.model.SessionManager;
import com.maximeattoumani.darties_mobile.model.User;
import com.maximeattoumani.darties_mobile.rest.ApiClient;
import com.maximeattoumani.darties_mobile.rest.ApiInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by melvi on 24/11/2016.
 */

public class TableauxFragment extends Fragment {

    private SessionManager session;
    private String api;
    ViewPager viewPager;
    List<RowAccueil> ca;
    List<RowAccueil> ventes;
    List<RowAccueil> marge;
    List<Fragment> fragmentList;
    private FragmentActivity myContext;
    private ApiInterface apiService;
    private String temps ;
    private String geo ;
    private String enseigne ;


    public TableauxFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_layout,container,false);

         //Session
        session = new SessionManager(getActivity().getApplicationContext());
        session.checkLogin();
        HashMap<String,String> user = session.getAPI();
        api = user.get(SessionManager.KEY_API);
        // END SESSION

        // CALL API
        temps = "2015_1_2015";
        geo = "101";
        enseigne = "0";

        viewPager = (ViewPager) rootView.findViewById(R.id.view_pager);

        fragmentList = new ArrayList<Fragment>();

        apiService = ApiClient.getClient();

        final ProgressDialog ringProgressDialog = ProgressDialog.show(rootView.getContext(), "Veuillez patienter", "récupération de données ...", true);
        ringProgressDialog.setCancelable(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
        apiService.listAccueilAsync(api,temps,geo,enseigne, new Callback<List<ProduitAccueil>>() {
            @Override
            public void success(List<ProduitAccueil> prod, Response response) {
                if (response.getStatus() == 200) {
                    ca = new ArrayList<RowAccueil>();
                    ventes = new ArrayList<RowAccueil>();
                    marge = new ArrayList<RowAccueil>();

                    for(int i = 0; i < prod.size();i++){
                        String ca_obj = prod.get(i).getCA_Objectif();
                        String ca_reel = prod.get(i).getCA_Reel();
                        String vente_obj = prod.get(i).getVentes_Objectif();
                        String vente_reel = prod.get(i).getVentes_Reel();
                        String marge_obj = prod.get(i).getMarge_Objectif();
                        String marg_reel = prod.get(i).getMarge_Reel();

                        ca.add(new RowAccueil(prod.get(i).getLib_famille_produit(),ca_obj.substring(0,ca_obj.lastIndexOf('.')-1),ca_reel.substring(0,ca_reel.lastIndexOf('.'))));
                        ventes.add(new RowAccueil(prod.get(i).getLib_famille_produit(),vente_obj.substring(00,vente_obj.lastIndexOf('.')),vente_reel.substring(0,vente_reel.lastIndexOf('.'))));
                        marge.add(new RowAccueil(prod.get(i).getLib_famille_produit(),marge_obj.substring(0,marge_obj.lastIndexOf('.')),marg_reel.substring(0,marg_reel.lastIndexOf('.'))));
                    }
                    CA_fragment ca_frag = new CA_fragment();
                    Vente_fragment vente_frag = new Vente_fragment();
                    Marge_fragment marge_frag = new Marge_fragment();
                    ca_frag.setList(ca);
                    vente_frag.setList(ventes);
                    marge_frag.setList(marge);
                    fragmentList.add(ca_frag);
                    fragmentList.add(vente_frag);
                    fragmentList.add(marge_frag);

                    MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager(),fragmentList);
                    viewPager.setAdapter(myFragmentPagerAdapter);
                }
            }

            @Override
            public void failure(RetrofitError error) {

                Log.d("Error", error.getMessage());
                System.out.println("Impossible de récuperer les informations, ERREUR "+error.getResponse().getStatus());
            }

        });
                try {
                    Thread.sleep(6500);
                } catch (Exception e) {
                }
                ringProgressDialog.dismiss();
            }
        }).start();

        // END CALL API
        // View Pager





        // END View Pager

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        return rootView;
    }


    @Override
    public void onAttach(Activity activity){
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }
}
