package com.example.android.meetthehero;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Lolev Cristian on 2/4/2018.
 */

public class results extends AppCompatActivity {

    String hero, playerName;
    int scorePro;
    Intent intentFromPro;
    Button restart;
    MediaPlayer mp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.results );

//        Getting data from last layout (pro Batman/ Superman/ Iron Man/ Spider-Man)
        intentFromPro = getIntent();
        Bundle extras = intentFromPro.getExtras();
        hero = extras.getString( "hero" );
        playerName = extras.getString( "playerName" );
        scorePro = intentFromPro.getIntExtra( "scorePro", 0 );

//        Setting up the layout, including results and correct answers
        setHeroLogo();
        setValues();

//        Setting restart button
        restart = (Button) findViewById( R.id.retry );
        restart.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
                clickToRestart();
            }
        } );

        Log.i( "score from basic: ", String.valueOf( scorePro ));
    }



//    Setting Hero method
    private void setHeroLogo(){
        ImageView heroLogo = (ImageView) findViewById( R.id.chosen_hero );
        Resources res = getResources();

        if (hero.equals( "Batman" )){
            heroLogo.setImageDrawable( res.getDrawable( R.drawable.batmaname ) );
        } else if (hero.equals( "Superman" )){
            heroLogo.setImageDrawable( res.getDrawable( R.drawable.supermanname ) );
        } else if (hero.equals( "Iron Man" )){
            heroLogo.setImageDrawable( res.getDrawable( R.drawable.ironmaname ) );
        } else {
            heroLogo.setImageDrawable( res.getDrawable( R.drawable.spidername ) );
        }
    }

//    Setting values method
    private void setValues(){
        TextView name = (TextView) findViewById( R.id.name );
        name.setText( playerName );

        TextView finalScore = (TextView) findViewById( R.id.final_score );
        finalScore.setText( String.valueOf( scorePro ) + " out of 8!" );

        TextView ans1 = (TextView) findViewById( R.id.correct_1 );
        TextView ans2 = (TextView) findViewById( R.id.correct_2 );
        TextView ans3 = (TextView) findViewById( R.id.correct_3 );
        TextView ans4 = (TextView) findViewById( R.id.correct_4 );
        TextView ans5 = (TextView) findViewById( R.id.correct_5 );
        TextView ans6 = (TextView) findViewById( R.id.correct_6 );
        TextView ans7 = (TextView) findViewById( R.id.correct_7 );
        TextView ans8 = (TextView) findViewById( R.id.correct_8 );

        if (hero.equals( "Batman" )){
            ans1.setText( "Bruce Wayne");
            ans2.setText( "Utility belt; Strength and speed" );
            ans3.setText( "Gotham" );
            ans4.setText( "Selina Kyle" );
            ans5.setText( "yes" );
            ans6.setText( "Joker" );
            ans7.setText( "Batman\'s butler, legal guardian" );
            ans8.setText( "25" );
        } else if (hero.equals( "Superman" )){
            ans1.setText( "Clark Ken" );
            ans2.setText( "Heat vision; Strength and speed; Flight;" );
            ans3.setText( "Metropolis" );
            ans4.setText( "Lois Lane" );
            ans5.setText( "yes" );
            ans6.setText( "Lex Luthor" );
            ans7.setText( "Jonathan and Martha" );
            ans8.setText( "Hope" );
        } else if (hero.equals( "Iron Man" )){
            ans1.setText( "Bruce Wayne" );
            ans2.setText( "Utility belt; Strength and speed;" );
            ans3.setText( "Gotham" );
            ans4.setText( "Selina Kyle" );
            ans5.setText( "yes" );
            ans6.setText( "Joker" );
            ans7.setText( "Just A Rather Very Intelligent System" );
            ans8.setText( "Extremis" );
        } else {
            ans1.setText( "Peter Parker" );
            ans2.setText( "Web-shooters; Strength and speed" );
            ans3.setText( "New York" );
            ans4.setText( "Mary Jane" );
            ans5.setText( "yes" );
            ans6.setText( "Doctor Octopus" );
            ans7.setText( "Unknown" );
            ans8.setText( "Shot" );
        }
    }

//    Restart Method
    private void clickToRestart(){
        Intent intent = new Intent( this, MainActivity.class );
        startActivity( intent );
    }

}
