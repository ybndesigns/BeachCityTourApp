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
public class SightsFragment extends Fragment {


    public SightsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_fragment, container, false);

        //Setting background color of main View
        final int backgroundColor = R.color.backgroundPlatinum;
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
        banner.setImageDrawable(getResources().getDrawable(R.drawable.pier_2));

        title.setText(getString(R.string.sights_tab));

        //Arrays for various slideshows set to separate variables
        int[] boardwalkPics = {R.drawable.boardwalk_day, R.drawable.boardwalk_dusk, R.drawable.boardwalk_background, R.drawable.boardwalk_overhead};
        int[] watertowerPics = {R.drawable.watertower_ontherun, R.drawable.watertower_map};
        int[] pierPics = {R.drawable.pier, R.drawable.pier_2, R.drawable.pier_3};
        int[] parkPics = {R.drawable.deweypark_green, R.drawable.deweypark_plaza, R.drawable.deweypark_map, R.drawable.deweypark_statue};

        //Making an ArrayList of BeachCityLocations to populate ListView
        final ArrayList<BeachCityLocation> sightstops = new ArrayList<BeachCityLocation>();
        sightstops.add(new BeachCityLocation(getString(R.string.boardwalkName), R.drawable.boardwalk_overhead, getString(R.string.boardwalk), boardwalkPics));
        sightstops.add(new BeachCityLocation(getString(R.string.waterTowerName), R.drawable.watertower_ontherun, getString(R.string.waterTower), watertowerPics));
        sightstops.add(new BeachCityLocation(getString(R.string.pierName), R.drawable.pier, getString(R.string.pier), pierPics));
        sightstops.add(new BeachCityLocation(getString(R.string.parkName), R.drawable.deweypark_map, getString(R.string.park), parkPics));

        //Initializing and setting adapter on ListView
        BeachCityLocationAdapter adapter = new BeachCityLocationAdapter(getActivity(), sightstops);
        final ListView listView = rootView.findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        //OnItemClickListener for listItemView to bundle information and send it to new Activity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                BeachCityLocation locationEntry = sightstops.get(position);

                Intent entryActivity = new Intent(getActivity(), EntryActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(getString(R.string.entry), locationEntry); //For BeachCityLocation
                bundle.putInt(getString(R.string.titleStyle), titleStyle); //For style applied to header title
                bundle.putInt(getString(R.string.background), backgroundColor); //For background color
                entryActivity.putExtras(bundle);
                startActivity(entryActivity);
            }
        });

        return rootView;
    }
}

