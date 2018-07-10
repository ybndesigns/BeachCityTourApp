package com.example.android.beachcitytourguide;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class IntroActivity extends AppCompatActivity {

    int currentImg = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        //background slideshow, making array of images and setting it on ViewPager
        final int[] beachcity = {R.drawable.beachcity_1, R.drawable.beachcity_far2, R.drawable.beachcity_2, R.drawable.beachcity_far, R.drawable.beachcity_street, R.drawable.beachcity_map};
        final ViewPager introSlideshow = findViewById(R.id.intro_slideshow);
        SlideshowAdapter adapter = new SlideshowAdapter(this, beachcity);
        introSlideshow.setAdapter(adapter);

        //Automated swiping of images
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            @Override
            public void run() {
                if (currentImg == beachcity.length) {
                    currentImg = 0;
                }
                introSlideshow.setCurrentItem(currentImg++, true);
            }
        };
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 1000, 3000);

        //OnClickListener for button to send user to MainActivity
        Button toTheMain = findViewById(R.id.to_the_main);
        toTheMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
