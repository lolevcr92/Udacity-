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
public class BasicLanguageFragment extends Fragment {

    private MediaPlayer mMediaPlayer;

    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int i) {
                    if (i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||  i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo( 0);
                    } else if (i == AudioManager.AUDIOFOCUS_GAIN){
                        mMediaPlayer.start();
                    } else if (i == AudioManager.AUDIOFOCUS_LOSS){
                        releaseMediaPlayer();
                    }
                }
            };

    private MediaPlayer.OnCompletionListener mComplitionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer(){
        if (mMediaPlayer != null){
            mMediaPlayer.release();

            mMediaPlayer = null;

            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener  );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

        View rootView = inflater.inflate( R.layout.storage_list, container, false );

        mAudioManager = (AudioManager) getActivity().getSystemService( Context.AUDIO_SERVICE );

        final ArrayList<Storage> storages = new ArrayList<Storage>(  );

        storages.add(new Storage(getString(R.string.language_1_en), getString(R.string.language_1_ro), R.raw.good_morning));
        storages.add(new Storage(getString(R.string.language_2_en), getString(R.string.language_2_ro), R.raw.good_night));
        storages.add(new Storage(getString(R.string.language_3_en), getString(R.string.language_3_ro), R.raw.my_name_is));
        storages.add(new Storage(getString(R.string.language_4_en), getString(R.string.language_4_ro), R.raw.tourist));
        storages.add(new Storage(getString(R.string.language_5_en), getString(R.string.language_5_ro), R.raw.how_do_i_get));
        storages.add(new Storage(getString(R.string.language_6_en), getString(R.string.language_6_ro), R.raw.what_time));
        storages.add(new Storage(getString(R.string.language_7_en), getString(R.string.language_7_ro), R.raw.where_is_bus_station));
        storages.add(new Storage(getString(R.string.language_8_en), getString(R.string.language_8_ro), R.raw.where_is_subway));
        storages.add(new Storage(getString(R.string.language_9_en), getString(R.string.language_9_ro), R.raw.time_opens));
        storages.add(new Storage(getString(R.string.language_10_en), getString(R.string.language_10_ro), R.raw.time_closes));
        storages.add(new Storage(getString(R.string.language_11_en), getString(R.string.language_11_ro), R.raw.how_much));
        storages.add(new Storage(getString(R.string.language_12_en), getString(R.string.language_12_ro), R.raw.keep_the_change));
        storages.add(new Storage(getString(R.string.language_13_en), getString(R.string.language_13_ro), R.raw.yes_no));
        storages.add(new Storage(getString(R.string.language_14_en), getString(R.string.language_14_ro), R.raw.numbers));
        storages.add(new Storage(getString(R.string.language_15_en), getString(R.string.language_15_ro), R.raw.hundred_thousand));

        StorageAdapter adapter = new StorageAdapter( getActivity(), storages );

        ListView listView = (ListView) rootView.findViewById( R.id.storage_list );

        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();

                Storage storage = storages.get(i);

                int result = mAudioManager.requestAudioFocus( mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT );

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){

                    mMediaPlayer = MediaPlayer.create (getActivity(), storage.getSoundResourceId());

                    mMediaPlayer.start();

                    mMediaPlayer.setOnCompletionListener( mComplitionListener );
                }
            }
        } );

        listView.setAdapter(adapter);

        return rootView;
    }
}
