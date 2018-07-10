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
public class ServicesFragment extends Fragment {


    public ServicesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_fragment, container, false);

        //Setting background color of main View
        final int backgroundColor = R.color.backgroundGreen;
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
        banner.setImageDrawable(getResources().getDrawable(R.drawable.buddwick_front));

        title.setText(getString(R.string.services_tab));

        //Arrays for various slideshows set to separate variables
        int[] itsawashPics = {R.drawable.itsawash_outsideday, R.drawable.itsawash_overhead, R.drawable.itsawash_sign};
        int[] libraryPics = {R.drawable.buddwick_front, R.drawable.buddwick_inside, R.drawable.buddwick_detail, R.drawable.buddwick_founder};
        int[] visitorsPics = {R.drawable.beachcity_map};

        //Making an ArrayList of BeachCityLocations to populate ListView
        final ArrayList<BeachCityLocation> servicestops = new ArrayList<BeachCityLocation>();
        servicestops.add(new BeachCityLocation("Its A Wash", R.drawable.itsawash_sign, getString(R.string.wash), itsawashPics));
        servicestops.add(new BeachCityLocation("Buddwick Public Library", R.drawable.buddwick_front, getString(R.string.library), libraryPics));
        servicestops.add(new BeachCityLocation("Visitors Center", R.drawable.beachcity_map, getString(R.string.visitors), visitorsPics));

        //Initializing and setting adapter on ListView
        BeachCityLocationAdapter adapter = new BeachCityLocationAdapter(getActivity(), servicestops);
        final ListView listView = rootView.findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        //OnItemClickListener for listItemView to bundle information and send it to new Activity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                BeachCityLocation locationEntry = servicestops.get(position);

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
