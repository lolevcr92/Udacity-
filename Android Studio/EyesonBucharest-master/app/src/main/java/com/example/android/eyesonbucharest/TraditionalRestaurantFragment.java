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
public class TraditionalRestaurantFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

        View rootView = inflater.inflate( R.layout.storage_list, container, false );

        final ArrayList<Storage> storages = new ArrayList<Storage>(  );

        storages.add(new Storage(R.drawable.rest_zexe, getString(R.string.rest_zexe), getString(R.string.rest_zexe_address), "https://www.zexe.ro/"));
        storages.add(new Storage(R.drawable.rest_oldsibiu, getString(R.string.rest_oldsibiu), getString(R.string.rest_oldsibiu_address), "http://www.theoldsibiu.ro/index.html"));
        storages.add(new Storage(R.drawable.rest_lacrimisisfinti, getString(R.string.rest_lacrimisisfinti), getString(R.string.rest_lacrimisisfinti_address), "https://www.lacrimisisfinti.com/"));
        storages.add(new Storage(R.drawable.rest_carucubere, getString(R.string.rest_carucubere), getString(R.string.rest_carucubere), "https://www.carucubere.ro/"));
        storages.add(new Storage(R.drawable.rest_lacopac, getString(R.string.rest_lacopac), getString(R.string.rest_lacopac_address), "http://www.lacopac.ro/"));
        storages.add(new Storage(R.drawable.rest_hanulluimanuc, getString(R.string.rest_hanulluimanuc), getString(R.string.rest_hanulluimanuc_address), "https://hanulluimanuc.ro/"));
        storages.add(new Storage(R.drawable.rest_citygrill, getString(R.string.rest_citygrill), getString(R.string.rest_citygrill_address), "https://citygrill.ro/ro/restaurante/covaci"));
        storages.add(new Storage(R.drawable.rest_mahala, getString(R.string.rest_mahala), getString(R.string.rest_mahala), "http://www.restaurant-mahala.ro/"));
        storages.add(new Storage(R.drawable.rest_cuptorulculemne, getString(R.string.rest_cuptorulculemne), getString(R.string.rest_cuptorulculemne_address), "https://www.cuptorulculemne.com/"));
        storages.add(new Storage(R.drawable.rest_cocos, getString(R.string.rest_cocos), getString(R.string.rest_cocos_address), "http://www.restaurantdoicocosi.ro/"));

        StorageAdapter adapter = new StorageAdapter( getActivity(), storages );

        ListView listView = (ListView) rootView.findViewById( R.id.storage_list );

        listView.setAdapter(adapter);

        return rootView;
    }
}
