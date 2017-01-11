package com.maximeattoumani.darties_mobile.control.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.maximeattoumani.darties_mobile.R;
import com.maximeattoumani.darties_mobile.model.Magasin;
import com.maximeattoumani.darties_mobile.model.Notification;

import java.util.List;

/**
 * Created by Maxime on 11/01/2017.
 */

public class MagasinAdapter extends ArrayAdapter<Magasin> {

    public MagasinAdapter(Context context, List<Magasin> list) {
        super(context,0,list);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.update_saisie,parent, false);
        }

       MagasinAdapter.MagasinViewHolder viewHolder = (MagasinAdapter.MagasinViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new MagasinAdapter.MagasinViewHolder();
            viewHolder.lib_up= (TextView) convertView.findViewById(R.id.lib_up);
            viewHolder.date_up = (TextView) convertView.findViewById(R.id.date_up);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Magasin row = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        //viewHolder.check.isChecked();
        viewHolder.lib_up.setText(row.getLibelle());
        viewHolder.date_up.setText(row.getDate_update());

        return convertView;
    }

    private class MagasinViewHolder{
        public TextView lib_up;
        public TextView date_up;

    }
}
