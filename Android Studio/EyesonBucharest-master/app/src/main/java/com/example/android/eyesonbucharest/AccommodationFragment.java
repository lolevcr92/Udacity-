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
public class AccommodationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

        View rootView = inflater.inflate( R.layout.storage_list, container, false );

        final ArrayList<Storage> storages = new ArrayList<Storage>(  );

        storages.add(new Storage(R.drawable.accom_embassy_nord, getString(R.string.accom_embassy), getString(R.string.accom_embassy_address), "http://embassy-nord.ro/"));
        storages.add(new Storage(R.drawable.accom_omegahouse, getString(R.string.accom_omegaHouse), getString(R.string.accom_omegaHouse_address), "https://www.omegahouse.ro/"));
        storages.add(new Storage(R.drawable.accom_cousins, getString(R.string.accom_counsins), getString(R.string.accom_counsins_address), "https://www.booking.com/hotel/ro/the-cousins-art-amp-host.ro.html"));
        storages.add(new Storage(R.drawable.accom_umbrella, getString(R.string.accom_umbrella), getString(R.string.accom_umbrella_address), "http://umbrellahostel.com/"));
        storages.add(new Storage(R.drawable.accom_puravidahostel, getString(R.string.accom_puraVidaHostel), getString(R.string.accom_puraVidaHostel_address), "http://sky.puravidahostels.ro/"));
        storages.add(new Storage(R.drawable.accom_liad, getString(R.string.accom_liad), getString(R.string.accom_liad_address), "http://liadhotel.com/"));
        storages.add(new Storage(R.drawable.accom_ibis, getString(R.string.accom_ibis), getString(R.string.accom_ibis_address), "http://www.ibishotels.ro/hotel-ibis-bucuresti-palatul-parlamentului"));
        storages.add(new Storage(R.drawable.accom_comfort, getString(R.string.accom_comfort), getString(R.string.accom_comfort_address), "http://www.relaxcomfort-suites.ro/"));
        storages.add(new Storage(R.drawable.accom_panorama, getString(R.string.accom_panorama), getString(R.string.accom_panorama_address), "https://www.airbnb.com/rooms/20048340"));
        storages.add(new Storage(R.drawable.accom_blackandwhite, getString(R.string.accom_blackAndWhite), getString(R.string.accom_blackAndWhite_address), "http://blackwhite1.business.site/"));
        storages.add(new Storage(R.drawable.accom_ave, getString(R.string.accom_ave), getString(R.string.accom_ave_address), "http://www.avehotel.ro/"));

        StorageAdapter adapter = new StorageAdapter( getActivity(), storages);

        ListView listView = (ListView) rootView.findViewById( R.id.storage_list );

        listView.setAdapter(adapter);

        return rootView;
    }
}
