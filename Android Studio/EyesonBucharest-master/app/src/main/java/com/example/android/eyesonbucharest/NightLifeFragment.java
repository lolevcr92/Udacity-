package com.example.android.eyesonbucharest;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NightLifeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

        View rootView = inflater.inflate( R.layout.storage_list, container, false );

        final ArrayList<Storage> storages = new ArrayList<Storage>(  );

        storages.add(new Storage(R.drawable.club_control, getString(R.string.club_control), getString(R.string.club_control_address), "http://www.control-club.ro/contact/"));
        storages.add(new Storage(R.drawable.club_expirat, getString(R.string.club_expirat), getString(R.string.club_expirata_address), "http://expirat.org/"));
        storages.add(new Storage(R.drawable.club_fire, getString(R.string.club_fire), getString(R.string.club_fire), "http://www.fire.ro/"));
        storages.add(new Storage(R.drawable.club_silverchurch, getString(R.string.club_silverchurch), getString(R.string.club_silverchurch_address), "http://silver.church/"));
        storages.add(new Storage(R.drawable.club_shoteria, getString(R.string.club_shoteria), getString(R.string.club_shoteria_address), "http://www.shoteria.ro/"));
        storages.add(new Storage(R.drawable.club_bistro, getString(R.string.club_bistro), getString(R.string.club_bistro), "https://www.facebook.com/JaiBistrotBucuresti"));
        storages.add(new Storage(R.drawable.club_eden, getString(R.string.club_eden), getString(R.string.club_eden_address), "https://www.facebook.com/gradinaeden107/"));
        storages.add(new Storage(R.drawable.club_interbelic, getString(R.string.club_interbelic), getString(R.string.club_interbelic_address), "http://www.interbelic.ro/"));
        storages.add(new Storage(R.drawable.club_nuba, getString(R.string.club_nuba), getString(R.string.club_nuba), "https://nuba.ro/summer"));
        storages.add(new Storage(R.drawable.club_e3, getString(R.string.club_e3), getString(R.string.club_e3_address), "http://byentourage.ro/"));


        StorageAdapter adapter = new StorageAdapter( getActivity(), storages );

        ListView listView = (ListView) rootView.findViewById( R.id.storage_list );

        listView.setAdapter(adapter);

        return rootView;
    }
}
