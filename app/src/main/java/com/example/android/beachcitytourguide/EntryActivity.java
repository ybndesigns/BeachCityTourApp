package com.example.android.beachcitytourguide;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        //Gathering bundled BeachCityLocation information from clicked listItem
        Bundle bundle = getIntent().getExtras();
        BeachCityLocation locationEntry = bundle.getParcelable(getString(R.string.entry));

        String name = locationEntry.getName();
        String infoText = locationEntry.getInfoText();
        int[] slideshow = locationEntry.getSlideshow();
        TabLayout indicator = (TabLayout) findViewById(R.id.indicator);

        //Assigning value and style of title to the TextView
        int entryTitleStyle = bundle.getInt(getString(R.string.titleStyle));
        TextView entryTitle = findViewById(R.id.entry_title);
        entryTitle.setText(name);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            entryTitle.setTextAppearance(entryTitleStyle);
        }
        else {
            TextViewCompat.setTextAppearance(entryTitle, entryTitleStyle);
        }

        //Assigning value to paragraph TextView
        TextView entryParagraph = findViewById(R.id.info_paragraph);
        entryParagraph.setText(infoText);
        if (entryTitleStyle == R.style.undergroundTitleTextView) { //Because the Underground background is dark
            entryParagraph.setTextColor(Color.WHITE);
        }

        //Gathering bundled information of background color of prior fragment and changing EntryActivity to match
        int entryBackground = bundle.getInt(getString(R.string.background));
        LinearLayout entryLayout = findViewById(R.id.entry_main);
        entryLayout.setBackgroundColor(getResources().getColor(entryBackground));

        //Assigning value to the slideshow ViewPager and pairing it with dot indicator
        ViewPager slideshowViewPager = findViewById(R.id.slideshow_viewpager);
        SlideshowAdapter slideshowAdapter = new SlideshowAdapter(this, slideshow);
        slideshowViewPager.setAdapter(slideshowAdapter);
        indicator.setupWithViewPager(slideshowViewPager);

        //Button to go back to last activity
        LinearLayout backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
