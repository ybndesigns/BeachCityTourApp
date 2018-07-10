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
public class FoodFragment extends Fragment {


    public FoodFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_fragment, container, false);

        //Setting background color of main View
        final int backgroundColor = R.color.backgroundAzureWhite;
        LinearLayout layout = rootView.findViewById(R.id.fragment_main);
        layout.setBackgroundColor(getResources().getColor(backgroundColor));

        //Changing style of header title dependent on category
        final int titleStyle = (R.style.titleTextViews);
        TextView title = rootView.findViewById(R.id.fragment_text_view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            title.setTextAppearance(titleStyle);
            title.setTextColor(Color.WHITE);
        } else {
            TextViewCompat.setTextAppearance(title, titleStyle);
            title.setTextColor(Color.WHITE);
        }

        //Changing header image dependent on category
        ImageView banner = rootView.findViewById(R.id.fragment_image_view);
        banner.setImageDrawable(getResources().getDrawable(R.drawable.bigdonut_menu));

        title.setText(getString(R.string.food_tab));

        //Arrays for various slideshows set to separate variables
        int[] bigDonutPics = {R.drawable.bigdonut_front1, R.drawable.bigdonut_front2, R.drawable.bigdonut_glass, R.drawable.bigdonut_inside, R.drawable.bigdonut_food};
        int[] fishStewPics = {R.drawable.fishstewpizza_logo, R.drawable.fishstewpizza_front, R.drawable.fishstewpizza_inside};
        int[] friesPics = {R.drawable.beachcitywalkfries_storefront2, R.drawable.beachcitywalkfries_closeup};
        int[] crabShackPics = {R.drawable.crabshack_1, R.drawable.crabshack_2, R.drawable.crabshack_3};

        //Making an ArrayList of BeachCityLocations to populate ListView
        final ArrayList<BeachCityLocation> foodstops = new ArrayList<BeachCityLocation>();
        foodstops.add(new BeachCityLocation("Big Donut", R.drawable.bigdonut_front1, getString(R.string.bigDonut), bigDonutPics));
        foodstops.add(new BeachCityLocation("Fish Stew Pizza", R.drawable.fishstewpizza_logo, getString(R.string.fishStewPizza), fishStewPics));
        foodstops.add(new BeachCityLocation("Beach Citywalk Fries", R.drawable.beachcitywalkfries_storefront2, getString(R.string.beachCitywakFries), friesPics));
        foodstops.add(new BeachCityLocation("The Crab Shack", R.drawable.crabshack_1, getString(R.string.crabShack), crabShackPics));

        //Initializing and setting adapter on ListView
        BeachCityLocationAdapter adapter = new BeachCityLocationAdapter(getActivity(), foodstops);
        final ListView listView = rootView.findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        //OnItemClickListener for listItemView to bundle information and send it to new Activity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                BeachCityLocation locationEntry = foodstops.get(position);

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
