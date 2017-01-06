package com.maximeattoumani.darties_mobile.control.Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maximeattoumani.darties_mobile.R;
import com.maximeattoumani.darties_mobile.control.Adapter.MyFragmentPagerAdapter;
import com.maximeattoumani.darties_mobile.model.ProduitAccueil;
import com.maximeattoumani.darties_mobile.model.RowAccueil;
import com.maximeattoumani.darties_mobile.model.SessionManager;
import com.maximeattoumani.darties_mobile.rest.ApiClient;
import com.maximeattoumani.darties_mobile.rest.ApiInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by melvi on 24/11/2016 and use by Thomas on 04/01/2017.
 */

public class TableauSaisiFragment extends Fragment {

    private SessionManager session;
    private String api;
    ViewPager viewPager;
    List<Fragment> fragmentList;
    private FragmentActivity myContext;
    private ApiInterface apiService;

    public TableauSaisiFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_layout,container,false);

         //Session
        session = new SessionManager(getActivity().getApplicationContext());
        session.checkLogin();
        HashMap<String,String> user = session.getAPI();
        api = user.get(SessionManager.KEY_API);
        // END SESSION
        viewPager = (ViewPager) rootView.findViewById(R.id.view_pager);

        fragmentList = new ArrayList<Fragment>();

        apiService = ApiClient.getClient();

                    SaisieFragment saisi1 = new SaisieFragment();
                    SaisieFragment saisi2 = new SaisieFragment();
                    SaisieFragment saisi3 = new SaisieFragment();

                    saisi1.setItemNb(0);
                    fragmentList.add(saisi1);
                    saisi2.setItemNb(1);

                    fragmentList.add(saisi2);
                     saisi3.setItemNb(2);
                    fragmentList.add(saisi3);

                    MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager(),fragmentList);
                    viewPager.setAdapter(myFragmentPagerAdapter);




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
