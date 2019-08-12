package com.example.android.meetthehero;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp;
    String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

//        Pick Hero button
        Button goPickHero = (Button) findViewById( R.id.pick_hero_button );
        goPickHero.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goPickHero();
            }
        } );

    }

//    Pick Hero Method
    private void goPickHero() {

        EditText editText = (EditText) findViewById( R.id.name );
        Editable name = editText.getText();
        playerName = name.toString();

//        Connecting with pickHero layout
        Intent intent = new Intent( MainActivity.this, pickHero.class );
        intent.putExtra( "playerName", playerName );

        playPickHeroSound();

        startActivity( intent );
    }

//    Pick Hero Sound Method
    private void playPickHeroSound() {

        mp = MediaPlayer.create( this, R.raw.pickhero );

        mp.setOnPreparedListener( new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mp.start();
            }
        } );

        mp.setOnCompletionListener( new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mp.release();
            }
        } );

    }

}


