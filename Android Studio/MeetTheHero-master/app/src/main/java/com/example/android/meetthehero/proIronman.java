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
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * Created by Lolev Cristian on 2/4/2018.
 */

public class proIronman extends AppCompatActivity{

    String hero, playerName;
    int scoreAdvanced, scorePro;
    Intent intentFromAdvanced;
    Button finish;
    MediaPlayer mp;
    ToggleButtonGroupTableLayout questionSevenGroup, questionEightGroup;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.pro_ironman );

//        Getting data from last layout(advanced)
        intentFromAdvanced = getIntent();
        Bundle extras = intentFromAdvanced.getExtras();
        hero = extras.getString( "hero" );
        playerName = extras.getString( "playerName" );
        scoreAdvanced = intentFromAdvanced.getIntExtra( "scoreAdvanced", 0 );

//        Setting up the layout
        setHeroLogo();

//        Setting up finish button
        finish = (Button) findViewById( R.id.finish_button_ironman );
        finish.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishClicked();
            }
        } );

        Log.i( "score from advanced: ", String.valueOf( scoreAdvanced ));
        Log.i("hero: ", hero.toString());
        Log.i("playername ", playerName.toString());
    }

//    Setting Hero Logo Method
    private void setHeroLogo(){
        ImageView heroLogo = (ImageView) findViewById( R.id.chosen_hero );
        Resources res = getResources();
        heroLogo.setImageDrawable( res.getDrawable( R.drawable.ironmaname) );
    }

//    Finish button method
    protected void finishClicked(){
        questionSevenGroup = (ToggleButtonGroupTableLayout) findViewById( R.id.Question7_group_ironman );
        questionEightGroup = (ToggleButtonGroupTableLayout) findViewById( R.id.Question8_group_ironman );
        int selectedQuestion7 = questionSevenGroup.getCheckedRadioButtonId();
        int selectedQuestion8 = questionEightGroup.getCheckedRadioButtonId();
        RadioButton correctAnswerQuestion7 = (RadioButton) findViewById( R.id.answer_very );
        RadioButton correctAnswerQuestion8 = (RadioButton) findViewById( R.id.answer_extremis );

        scorePro = scoreAdvanced;

        if (selectedQuestion7 == correctAnswerQuestion7.getId()){scorePro++;}
        if (selectedQuestion8 == correctAnswerQuestion8.getId()){scorePro++;}

        String stringScorePro = Integer.toString(scorePro);
        Log.i("Iron Man result: ", stringScorePro);

        Toast.makeText( this, "Scored " + stringScorePro + " out of 8!", Toast.LENGTH_LONG ).show();

//        Connecting with next layout (results)
        Intent intent = new Intent( this, results.class );
        Bundle extras = new Bundle(  );
        extras.putString("hero", hero);
        extras.putString( "playerName", playerName );
        intent.putExtras(extras);
        intent.putExtra( "scorePro", scorePro );
        startActivity(intent);

//      Playing finish sound
        tadam();
    }

    //    Finish sound method
    private void tadam(){
        mp = MediaPlayer.create( this, R.raw.tadam );
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
