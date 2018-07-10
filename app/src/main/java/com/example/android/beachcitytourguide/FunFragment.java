package com.example.android.beachcitytourguide;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FunFragment extends Fragment {

    public FunFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_fragment, container, false);

        //Setting background color of main View
        final int backgroundColor = R.color.backgroundPapayaWhip;
        LinearLayout layout = rootView.findViewById(R.id.fragment_main);
        layout.setBackgroundColor(getResources().getColor(backgroundColor));

        //Changing style of header title dependent on category
        final int titleStyle = (R.style.titleTextViews);
        TextView title = rootView.findViewById(R.id.fragment_text_view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            title.setTextAppearance(titleStyle);
            title.setTextColor(Color.WHITE);
        }
        else {
            TextViewCompat.setTextAppearance(title, titleStyle);
            title.setTextColor(Color.WHITE);
        }

        //Changing header image dependent on category
        ImageView banner = rootView.findViewById(R.id.fragment_image_view);
        banner.setImageDrawable(getResources().getDrawable(R.drawable.funlandpark_pan));

        title.setText(getString(R.string.fun_tab));

        //Arrays for various slideshows set to separate variables
        int[] beachPics = {R.drawable.beach_daytime, R.drawable.beach_nighttime, R.drawable.beach_beachapalooza2, R.drawable.beach_beachapalooza_full};
        int[] arcadePics = {R.drawable.funlandarcade_front2, R.drawable.funlandarcade_front3, R.drawable.funlandarcade_inside, R.drawable.funlandarcade_inside2};
        int[] funparkPics = {R.drawable.funlandpark_front, R.drawable.funlandpark_appalacian, R.drawable.funlandpark_appalacian2, R.drawable.funlandpark_ferriswheel, R.drawable.funlandpark_pan};
        int[] theaterPics = {R.drawable.theater_1, R.drawable.theater_2, R.drawable.theater_3};

        //Making an ArrayList of BeachCityLocations to populate ListView
        final ArrayList<BeachCityLocation> funstops = new ArrayList<BeachCityLocation>();
        funstops.add(new BeachCityLocation("The Beach", R.drawable.beach_daytime, getString(R.string.beach), beachPics));
        funstops.add(new BeachCityLocation("Funland Arcade", R.drawable.funlandarcade_front, getString(R.string.arcade), arcadePics));
        funstops.add(new BeachCityLocation("Funland Amusement Park", R.drawable.funlandpark_front2, getString(R.string.amusementpark), funparkPics));
        funstops.add(new BeachCityLocation("Beach City Theater", R.drawable.theater_1, getString(R.string.theater), theaterPics));

        //Initializing and setting adapter on ListView
        BeachCityLocationAdapter adapter = new BeachCityLocationAdapter(getActivity(), funstops);
        final ListView listView = rootView.findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        //OnItemClickListener for listItemView to bundle information and send it to new Activity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                BeachCityLocation locationEntry = funstops.get(position);

                Intent entryActivity = new Intent(getActivity(), EntryActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("Entry", locationEntry); //For BeachCityLocation
                bundle.putInt("Title Style", titleStyle); //For style applied to header title
                bundle.putInt("Background", backgroundColor); //For background color
                entryActivity.putExtras(bundle);
                startActivity(entryActivity);
            }
        });


        return rootView;
    }

}
