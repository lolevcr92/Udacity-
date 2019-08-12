package com.example.android.meetthehero;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.squareup.picasso.Picasso;

/**
 * Created by Lolev Cristian on 2/2/2018.
 */

public class pickHero extends AppCompatActivity {

    String playerName;
    Intent intentFromMain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.pick_hero );

//        Creating Hero ImageButtons
        ImageButton buttonBatman = (ImageButton) findViewById( R.id.batman_button );
        ImageButton buttonSuperman = (ImageButton) findViewById( R.id.superman_button );
        ImageButton buttonIronman = (ImageButton) findViewById( R.id.ironman_button );
        ImageButton buttonSpiderman = (ImageButton) findViewById( R.id.spiderman_button );

//        Getting connected with MainActivity (transfering data)
        intentFromMain = getIntent();
        playerName = intentFromMain.getStringExtra( "playerName" );

        Log.i("check name: ", playerName.toString());

//        Setting each Hero ImageButtons
        buttonBatman.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickBatman();
            }
        } );
        buttonSuperman.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickSuperman();
            }
        } );
        buttonIronman.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickIronman();
            }
        } );
        buttonSpiderman.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickSpiderman();
            }
        } );


    }

//    Picking Batman method + connecting with next layout (basic)
    private void pickBatman(){
        String hero = "Batman";
        Intent intent = new Intent(pickHero.this, basic.class );
        Bundle extras = new Bundle(  );
        extras.putString("hero", hero);
        extras.putString( "playerName", playerName );
        intent.putExtras(extras);
        startActivity( intent );
    }

//   Picking Superman method + connecting with next layout (basic)
    private void pickSuperman(){
        String hero = "Superman";
        Intent intent = new Intent(pickHero.this, basic.class );
        Bundle extras = new Bundle(  );
        extras.putString("hero", hero);
        extras.putString( "playerName", playerName );
        intent.putExtras(extras);
        startActivity( intent );
    }

//    Picking Iron Man method + connecting with next layout (basic)
    private void pickIronman(){
        String hero = "Iron Man";
        Intent intent = new Intent(pickHero.this, basic.class );
        Bundle extras = new Bundle(  );
        extras.putString("hero", hero);
        extras.putString( "playerName", playerName );
        intent.putExtras(extras);
        startActivity( intent );
    }

//    Picking Spider-Man method + connecting with next layout (basic)
    private void pickSpiderman(){
        String hero = "Spider-Man";
        Intent intent = new Intent(pickHero.this, basic.class );
        Bundle extras = new Bundle(  );
        extras.putString("hero", hero);
        extras.putString( "playerName", playerName );
        intent.putExtras(extras);
        startActivity( intent );
    }
}
