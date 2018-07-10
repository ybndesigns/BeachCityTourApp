package com.example.android.beachcitytourguide;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the view pager that will allow the user to swipe between fragments
        final ViewPager viewPager = findViewById(R.id.viewpager);

        //Setting the TabLayout to a variable to be later connected with ViewPager
        final TabLayout navigation = findViewById(R.id.navigation);

        // Create an adapter that knows which fragment should be shown on each page
        CategoriesFragmentPagerAdapter adapter = new CategoriesFragmentPagerAdapter(getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        //Pairing TabLayout with ViewPager
        navigation.setupWithViewPager(viewPager);

        //Setting each tab with special icon
        navigation.getTabAt(0).setIcon(R.drawable.ic_fun);
        navigation.getTabAt(1).setIcon(R.drawable.ic_food);
        navigation.getTabAt(2).setIcon(R.drawable.ic_sights);
        navigation.getTabAt(3).setIcon(R.drawable.ic_services);

        //Removing tab for Underground. This is on purpose.
        navigation.removeTabAt(4);

    }


}
