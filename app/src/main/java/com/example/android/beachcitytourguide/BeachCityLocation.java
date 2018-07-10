package com.example.android.beachcitytourguide;

import android.os.Parcel;
import android.os.Parcelable;


public class BeachCityLocation implements Parcelable { //a class for storing the elements for each location in Beach City

    public static final Creator<BeachCityLocation> CREATOR = new Creator<BeachCityLocation>() {
        @Override
        public BeachCityLocation createFromParcel(Parcel in) {
            return new BeachCityLocation(in);
        }

        @Override
        public BeachCityLocation[] newArray(int size) {
            return new BeachCityLocation[size];
        }
    };
    private String mName;
    private int mPhoto;
    private String mInfoText;
    private int[] mSlideshow;

    /**
     * @param name      - the name/title of the location
     * @param photo     - the display photo that shows in the ListView
     * @param infoText  - the detailed text that ends up in the detailed view (EntryActivity)
     * @param slideshow - an array that become the slideshow for the detailed view (EntryActivity)
     */
    public BeachCityLocation(String name, int photo, String infoText, int[] slideshow) {
        mName = name;
        mPhoto = photo;
        mInfoText = infoText;
        mSlideshow = slideshow;
    }

    protected BeachCityLocation(Parcel in) {
        mName = in.readString();
        mPhoto = in.readInt();
        mInfoText = in.readString();
        mSlideshow = in.createIntArray();
    }

    public String getName() {
        return mName;
    }

    public int getPhoto() {
        return mPhoto;
    }

    public String getInfoText() {
        return mInfoText;
    }

    public int[] getSlideshow() {
        return mSlideshow;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeInt(mPhoto);
        dest.writeString(mInfoText);
        dest.writeIntArray(mSlideshow);
    }
}
