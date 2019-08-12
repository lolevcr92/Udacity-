package com.example.android.quakereport;

/**
 * Created by Lolev Cristian on 5/17/2018.
 */

public class Earthquake {

    private int mMagnitudeColorResourceId;
    private String mMagnitude;
    private String mDate;
    private String mNearOf;
    private String mLocation;
    private String mUrl;

    public Earthquake( String nearOf, String location, String magnitude, int magnitudeColorResourceId, String date, String url){
        mMagnitudeColorResourceId = magnitudeColorResourceId;
        mNearOf = nearOf;
        mLocation = location;
        mMagnitude = magnitude;
        mDate = date;
        mUrl = url;
    }

    public String getNearOf(){
        return mNearOf;
    }

    public String getLocation(){
        return mLocation;
    }

    public String getMagniture(){
        return mMagnitude;
    }

    public int getMagnitudeColorResourceId(){
        return mMagnitudeColorResourceId;
    }

    public String getDate(){
        return mDate;
    }

    public String getUrl(){
        return mUrl;
    }
}
