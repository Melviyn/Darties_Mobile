package com.maximeattoumani.darties_mobile.control.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.maximeattoumani.darties_mobile.R;
import com.maximeattoumani.darties_mobile.model.RowAccueil;

import java.util.List;

import static android.R.attr.resource;

/**
 * Created by melvi on 01/12/2016.
 */

public class RowAccueilAdapter extends ArrayAdapter<RowAccueil> {

    public RowAccueilAdapter(Context context, List<RowAccueil> list) {
        super(context,0,list);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_tab_acc,parent, false);
        }

        AccueilViewHolder viewHolder = (AccueilViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new AccueilViewHolder();
            viewHolder.lib = (TextView) convertView.findViewById(R.id.lib);
            viewHolder.info1 = (TextView) convertView.findViewById(R.id.info1);
            viewHolder.info2 = (TextView) convertView.findViewById(R.id.info2);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        RowAccueil row = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.lib.setText(row.getLib());
        viewHolder.info1.setText(row.getInfo1());
        viewHolder.info2.setText(row.getInfo2());

        return convertView;
    }

    private class AccueilViewHolder{
        public TextView lib;
        public TextView info1;
        public TextView info2;
    }
}
