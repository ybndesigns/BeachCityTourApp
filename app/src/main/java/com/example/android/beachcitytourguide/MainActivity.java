package com.example.android.beachcitytourguide;

import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the view pager that will allow the user to swipe between fragments
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        //Finding the BottomNavigationView to be connected with ViewPager
        final BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.navigation_view);

        //Setting underground tab option as GONE. On purpose.
        (findViewById(R.id.menu_underground)).setVisibility(View.GONE);

        //Connecting ViewPager to navigationView
        navigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected (@NonNull MenuItem item){
                switch (item.getItemId()) {
                    case R.id.menu_fun:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.menu_food:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.menu_sights:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.menu_services:
                        viewPager.setCurrentItem(3);
                        break;
                } //It doesn't have the Underground tab on purpose
                return false;
            }
        });

        //Adding OnPageChangeListener to connect ViewChanger swipe changes to BottomNavigationMenu
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            MenuItem prevMenuItem;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else {
                    navigationView.getMenu().getItem(0).setChecked(false);
                }
                navigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = navigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // Create an adapter that knows which fragment should be shown on each page
        CategoriesFragmentPagerAdapter adapter = new CategoriesFragmentPagerAdapter(getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

    }


}
