package com.maximeattoumani.darties_mobile.control.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.maximeattoumani.darties_mobile.R;
import com.maximeattoumani.darties_mobile.model.Gestion;

import java.util.List;

/**
 * Created by Maxime on 11/01/2017.
 */

public class MagasinAdapter extends ArrayAdapter<Gestion> {

    private Integer[] icon;

    public MagasinAdapter(Context context, List<Gestion> list,Integer[] pics) {
        super(context,0,list);
        this.icon = pics;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.update_saisie,parent, false);
        }

       MagasinAdapter.MagasinViewHolder viewHolder = (MagasinAdapter.MagasinViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new MagasinAdapter.MagasinViewHolder();
            viewHolder.lib_up= (TextView) convertView.findViewById(R.id.lib_up);
            viewHolder.image= (ImageView) convertView.findViewById(R.id.mag);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Gestion row = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        //viewHolder.check.isChecked();
        viewHolder.lib_up.setText(row.getLib_profil());
        viewHolder.image.setBackgroundResource(icon[position]);

        return convertView;
    }

    private class MagasinViewHolder{
        public TextView lib_up;
        public ImageView image;

    }
}
