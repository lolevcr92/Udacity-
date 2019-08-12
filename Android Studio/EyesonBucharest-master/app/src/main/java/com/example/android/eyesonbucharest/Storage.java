package com.example.android.eyesonbucharest;

/**
 * Created by Lolev Cristian on 4/26/2018.
 */

public class Storage {

    private int mImageResourceId;
    private String mName;
    private String mLocation;
    private String mSite;

    private int mSoundResourceId;

    private boolean mHasImage = true;

    @Override
    public String toString() {
        return "Storage{" +
                "Field0 = '" + mImageResourceId + '\'' +
                ", Field1 = '" + mName + '\'' +
                ", Field2 = '" + mLocation + '\'' +
                ", Field3 = '" + mSite + '\'' +
                ", Field4 = '" + mSoundResourceId + '\'' +
                ", Field has Image = '" + mHasImage + '\'' +
                '}';
    }

    /*Constructor 1 - for Accomodation, Cultural Activities, Sightseeings, Traditional Restaurants & Transportation*/
    public Storage( int imageResourceId, String name, String location, String site) {
        mImageResourceId = imageResourceId;
        mName = name;
        mLocation = location;
        mSite = site;
    }


    /*Constructor 2 - Useful phrases*/
    public Storage (String english, String romana, int soundResourceId ){
        mHasImage = false;
        mName = english;
        mLocation = romana;
        mSoundResourceId = soundResourceId;
    }

//    Methods (getters here)
    /*for place image*/
    public int getImageResourceId(){
        return mImageResourceId;
    }

    /*name of the place (or english version in Useful phrases)*/
    public String getName(){
        return mName;
    }

    /*place location (or romanian version in Useful phrases)*/
    public String getLocation(){
        return mLocation;
    }

    /*place website*/
    public String getSite(){
        return mSite;
    }

    /*sound resource id*/
    public int getSoundResourceId(){
        return mSoundResourceId;
    }

    /*has Image*/
    public boolean hasImage(){
        return mHasImage;
    }

}
