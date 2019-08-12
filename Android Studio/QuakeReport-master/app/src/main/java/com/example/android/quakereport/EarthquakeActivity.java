/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earthquake>>{

    /** Adapter for the list of earthquakes */
    private Adapter mAdapter;

    private static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=1&limit=10";
    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private TextView emptyState;
    private ListView earthquakeListView;
    private ProgressBar loadingSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a new {@link ArrayAdapter} of earthquakes
        mAdapter = new Adapter(this, new ArrayList<Earthquake>(  ));


        emptyState = (TextView) findViewById( R.id.main_view_empty );
        earthquakeListView = (ListView) findViewById( R.id.main_view );
        earthquakeListView.setEmptyView( emptyState );
        loadingSpinner = (ProgressBar) findViewById( R.id.loading_spinner );
        loadingSpinner.setVisibility( View.VISIBLE );

        earthquakeListView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Earthquake quake = mAdapter.getItem( i );

                Uri webpage = Uri.parse(quake.getUrl());
                Intent intent = new Intent( Intent.ACTION_VIEW, webpage );
                if (intent.resolveActivity( getPackageManager() ) != null){
                    startActivity( intent );
                }

            }
        } );

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);

        /*Get a reference to the ConnectivityManager to check state of network connectivity*/
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService( Context.CONNECTIVITY_SERVICE );
        /*Get details on the currently active default data network*/
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        /*If there is a network connection, fetch data*/
        if (networkInfo != null && networkInfo.isConnected()){
            LoaderManager loader = getLoaderManager();
            Log.i("InitLoader is next","starts");
            loader.initLoader( 1,null,this  );
        }else{
            View loadingIndicator = findViewById( R.id.loading_spinner );
            loadingIndicator.setVisibility( View.GONE );

            emptyState.setText("Nu e internet");
        }
    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int i, Bundle bundle) {
        Log.i( "onCrealoader: ", "starts" );
        return new EarthquakeLoader( EarthquakeActivity.this, USGS_REQUEST_URL );
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquakes) {
        loadingSpinner.setVisibility( View.GONE );
        emptyState.setText( "No earthquakes available" );
        Log.i("onLoadeFinished: ", "starts");
        mAdapter.clear();
            /*If there is a vald list of earthquakes, then add them to the adapter's data set. this will trigger the listview to update*/
        if (earthquakes != null && !earthquakes.isEmpty()) {
            mAdapter.addAll( earthquakes );
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        Log.i( "onLoaderReset: ", "starts" );
        mAdapter.clear();
    }


}
