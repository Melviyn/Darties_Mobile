package com.maximeattoumani.darties_mobile.control.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.maximeattoumani.darties_mobile.R;
import com.maximeattoumani.darties_mobile.model.Notification;
import com.maximeattoumani.darties_mobile.model.RowAccueil;

import java.util.List;

/**
 * Created by Maxime on 12/12/2016.
 */

public class NotificationAdapter extends ArrayAdapter<Notification> {

    public NotificationAdapter(Context context, List<Notification> list) {
        super(context,0,list);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.message,parent, false);
        }

        NotifViewHolder viewHolder = (NotifViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new NotifViewHolder();
            viewHolder.check = (CheckBox) convertView.findViewById(R.id.checkBoxNotif);
            viewHolder.title = (TextView) convertView.findViewById(R.id.titleNotif);
            viewHolder.message = (TextView) convertView.findViewById(R.id.msgNotif);
            viewHolder.date = (TextView) convertView.findViewById(R.id.dateNotif);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Notification row = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        //viewHolder.check.isChecked();
        viewHolder.title.setText(row.getTitle());
        viewHolder.message.setText(row.getMessage());
        viewHolder.date.setText(row.getDate());

        return convertView;
    }

    private class NotifViewHolder{
        public CheckBox check;
        public TextView title;
        public TextView message;
        public TextView date;
    }


}
