package com.example.android.beachcitytourguide;


import android.content.Intent;
import android.graphics.Color;
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
public class UndergroundFragment extends Fragment {


    public UndergroundFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_fragment, container, false);

        //Setting background color of main View
        final int backgroundColor = R.color.richBlack;
        LinearLayout layout = rootView.findViewById(R.id.fragment_main);
        layout.setBackgroundColor(getResources().getColor(backgroundColor));


        //Changing style of header title dependent on category
        final int titleStyle = (R.style.undergroundTitleTextView);
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
        banner.setImageDrawable(getResources().getDrawable(R.drawable.abandonedwarehouse_dance));

        title.setText(getString(R.string.underground));

        //Arrays for various slideshows set to separate variables
        int[] warehousePics = {R.drawable.abandonedwarehouse_outside, R.drawable.abandonedwarehouse_dance, R.drawable.abandonedwarehouse_wrestling};
        int[] racingPics = {R.drawable.speedracing_lot, R.drawable.speedracing_hill, R.drawable.speedracing_cars2};
        int[] lighthousePics = {R.drawable.lighthouse_far, R.drawable.lighthouse_overhead, R.drawable.lighthouse_sunset};
        int[] templePics = {R.drawable.crystaltemple_day, R.drawable.crystaltemple_storm, R.drawable.crystaltemple_headon, R.drawable.crystaltemple_stevenshouse};
        int[] kbcwPics = {R.drawable.keepbeachcityweird_risingtides, R.drawable.keepbeachcityweird_sneeple, R.drawable.keepbeachcityweird_ronaldorants, R.drawable.keepbeachcityweird_watermelonautopsy};

        //Making an ArrayList of BeachCityLocations to populate ListView
        final ArrayList<BeachCityLocation> secretspots = new ArrayList<BeachCityLocation>();
        secretspots.add(new BeachCityLocation("Keep Beach City Weird", R.drawable.keepbeachcityweird_ronaldo, getString(R.string.kbcw), kbcwPics));
        secretspots.add(new BeachCityLocation("Abandoned Warehouse", R.drawable.abandonedwarehouse_dance, getString(R.string.warehouse), warehousePics));
        secretspots.add(new BeachCityLocation("Street Racing", R.drawable.speedracing_hill, getString(R.string.races), racingPics));
        secretspots.add(new BeachCityLocation("The Lighthouse", R.drawable.lighthouse_sunset, getString(R.string.lighthouse), lighthousePics));
        secretspots.add(new BeachCityLocation("Crystal Temple", R.drawable.crystaltemple_headon, getString(R.string.temple), templePics));

        //Initializing and setting adapter on ListView
        BeachCityLocationAdapter adapter = new BeachCityLocationAdapter(getActivity(), secretspots);
        final ListView listView = rootView.findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        //OnItemClickListener for listItemView to bundle information and send it to new Activity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                BeachCityLocation locationEntry = secretspots.get(position);

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
