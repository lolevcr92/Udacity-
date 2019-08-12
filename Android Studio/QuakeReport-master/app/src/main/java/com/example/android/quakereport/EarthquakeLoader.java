package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lolev Cristian on 5/22/2018.
 */

public class EarthquakeLoader  extends AsyncTaskLoader<List<Earthquake>>{
    private String mUrl;
    public EarthquakeLoader(Context context, String url) {
        super( context );
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        Log.i("loadInBackground: ", "Starts");
        if (mUrl == null) {
            return null;
        }
        ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes(mUrl);
        return earthquakes;
    }
}
