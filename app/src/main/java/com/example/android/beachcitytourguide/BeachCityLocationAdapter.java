package com.example.android.beachcitytourguide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BeachCityLocationAdapter extends ArrayAdapter { //Adapter to allow BeachCityLocation info to show in a ListView
    public BeachCityLocationAdapter(@NonNull Context context, @NonNull List<BeachCityLocation> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        BeachCityLocation currentLocation = (BeachCityLocation) getItem(position);

        ImageView listImageView = listItemView.findViewById(R.id.list_image_view);
        listImageView.setImageResource(currentLocation.getPhoto());

        TextView listTextView = listItemView.findViewById(R.id.list_text_view);
        listTextView.setText(currentLocation.getName());

        return listItemView;
    }
}
