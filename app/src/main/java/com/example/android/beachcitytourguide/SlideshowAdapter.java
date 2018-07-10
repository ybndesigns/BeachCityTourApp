package com.example.android.beachcitytourguide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class SlideshowAdapter extends PagerAdapter { //To use ViewPager as a sliding picture gallery

    private Context context;
    private LayoutInflater layoutInflater;
    private int[] mImages;


    public SlideshowAdapter(Context context, int[] images) {
        this.context = context;
        this.mImages = images;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mImages.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.slideshow_layout, container, false);
        ImageView imageView = view.findViewById(R.id.slideshow_image);
        imageView.setImageResource(mImages[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
