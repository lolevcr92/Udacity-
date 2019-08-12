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
public class SitesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

        View rootView = inflater.inflate( R.layout.storage_list, container, false );

        final ArrayList<Storage> storages = new ArrayList<Storage>(  );

        storages.add(new Storage(R.drawable.site_cismigiu, getString(R.string.site_cismigiu), getString(R.string.site_cismigiu_address), "https://en.wikipedia.org/wiki/Ci%C8%99migiu_Gardens"));
        storages.add(new Storage(R.drawable.site_muzeultaranuluiroman, getString(R.string.site_muzeultaranului), getString(R.string.site_muzeultaranului_address), "http://www.muzeultaranuluiroman.ro/home.html"));
        storages.add(new Storage(R.drawable.site_casapoporului, getString(R.string.site_parlament), getString(R.string.site_parlament_address), "http://cic.cdep.ro/ro/vizitare/programe-si-tarife-de-vizitare"));
        storages.add(new Storage(R.drawable.site_cimitirulbellu, getString(R.string.site_belu), getString(R.string.site_belu_address), "http://accu.ro/cimitirul-bellu/"));
        storages.add(new Storage(R.drawable.site_antipa, getString(R.string.site_antipa), getString(R.string.site_antipa_address), "http://antipa.ro/en"));
        storages.add(new Storage(R.drawable.site_maccavillacrosse, getString(R.string.site_maccavilacrosse), getString(R.string.site_maccavilacrosse_address), "https://en.wikipedia.org/wiki/Pasajul_Macca-Vilacrosse"));
        storages.add(new Storage(R.drawable.site_mogosoaiapalace, getString(R.string.site_mogosoaia), getString(R.string.site_mogosoaia_address), "https://palatebrancovenesti.ro/"));
        storages.add(new Storage(R.drawable.site_astronomic, getString(R.string.site_observator), getString(R.string.site_observator_address), "http://www.muzeulbucurestiului.ro/observatorul-astronomic-vasile-urseanu.html"));
        storages.add(new Storage(R.drawable.site_vacaresti, getString(R.string.site_vacaresti), getString(R.string.site_vacaresti_address), "http://parcnaturalvacaresti.ro/english/"));
        storages.add(new Storage(R.drawable.site_ceausescu, getString(R.string.site_ceausescu), getString(R.string.site_ceausescu), "http://casaceausescu.ro/?page_id=2810"));



        StorageAdapter adapter = new StorageAdapter( getActivity(), storages );

        ListView listView = (ListView) rootView.findViewById( R.id.storage_list );

        listView.setAdapter(adapter);

        return rootView;
    }
}
