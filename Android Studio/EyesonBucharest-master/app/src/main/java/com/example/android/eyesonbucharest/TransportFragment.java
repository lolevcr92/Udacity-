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
public class TransportFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

        View rootView = inflater.inflate( R.layout.storage_list, container, false );

        final ArrayList<Storage> storages = new ArrayList<Storage>(  );

        storages.add(new Storage(R.drawable.tran_metro, getString(R.string.tran_metro), getString(R.string.tran_metro_type), "http://www.metrorex.ro/first_page_p1352-2"));
        storages.add(new Storage(R.drawable.tran_public, getString(R.string.tran_public), getString(R.string.tran_public_type), "http://www.ratb.ro/eng/index.php"));
        storages.add(new Storage(R.drawable.tran_taxify, getString(R.string.tran_taxify), getString(R.string.tran_taxify_type), "https://taxify.eu/cities/bucharest/"));
        storages.add(new Storage(R.drawable.tran_uber, getString(R.string.tran_uber), getString(R.string.tran_uber_type), "https://www.uber.com/en-RO/cities/bucharest/"));
        storages.add(new Storage(R.drawable.tran_train, getString(R.string.tran_train), getString(R.string.tran_train_type), "https://www1.cfrcalatori.ro/en/"));

        StorageAdapter adapter = new StorageAdapter( getActivity(), storages );

        ListView listView = (ListView) rootView.findViewById( R.id.storage_list );

        listView.setAdapter(adapter);

        return rootView;
    }
}
