package com.example.android.beachcitytourguide;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class CategoriesFragmentPagerAdapter extends FragmentPagerAdapter { //Adapter to display the Category Fragments in MainActivity

    public CategoriesFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FunFragment();
            case 1:
                return new FoodFragment();
            case 2:
                return new SightsFragment();
            case 3:
                return new ServicesFragment();
            case 4:
                return new UndergroundFragment();
            default:
                return null;
        }
    }
}
