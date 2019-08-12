package com.example.android.meetthehero;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by Lolev Cristian on 2/2/2018.
 */


public class basic extends AppCompatActivity {

    String playerName, hero;
    int scoreBasic;
    Intent intentFromPickHero;
    RadioButton ans11, ans12, ans13, ans14, ans31, ans32, ans33, ans34;
    Button nextToAdvanced;
    MediaPlayer mp;
    ToggleButtonGroupTableLayout questionOneGroup, questionThreeGroup;
    CheckBox laserBeam, utilityBelt, heatVision, webShooters, strengthSpeed, flight;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.basic );

//        Getting data from last layout (pickHero)
        intentFromPickHero = getIntent();
        Bundle extras = intentFromPickHero.getExtras();
        hero = extras.getString( "hero" );
        playerName = extras.getString( "playerName" );

        Log.i( "playerName",playerName.toString() );

//        Setting the layout and answers depending of the previous hero pick
        setHeroLogo();
        answersCreation();

//        Setting Button to next layout (advanced)
        nextToAdvanced = (Button) findViewById( R.id.next_to_advanced );
        nextToAdvanced.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextToAdvancedClicked();
            }
        } );
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState( savedInstanceState );
        savedInstanceState.putBoolean( "Clark Kent", ans11.isChecked() );
        savedInstanceState.putBoolean( "Bruce Wayne", ans12.isChecked() );
        savedInstanceState.putBoolean( "Peter Parker", ans13.isChecked() );
        savedInstanceState.putBoolean( "Tony Stark", ans14.isChecked() );
        savedInstanceState.putBoolean( "New York", ans31.isChecked() );
        savedInstanceState.putBoolean( "Metropolis", ans32.isChecked() );
        savedInstanceState.putBoolean( "Gotham", ans33.isChecked() );
        savedInstanceState.putBoolean( "Malibu", ans34.isChecked() );
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState( savedInstanceState );
        ans11.setChecked( savedInstanceState.getBoolean( "Clark Kent" ) );
        ans12.setChecked( savedInstanceState.getBoolean( "Bruce Wayne" ) );
        ans13.setChecked( savedInstanceState.getBoolean( "Peter Parker" ) );
        ans14.setChecked( savedInstanceState.getBoolean( "Tony Stark" ) );
        ans31.setChecked( savedInstanceState.getBoolean( "New York" ) );
        ans32.setChecked( savedInstanceState.getBoolean( "Metropolis" ) );
        ans33.setChecked( savedInstanceState.getBoolean( "Gotham" ) );
        ans34.setChecked( savedInstanceState.getBoolean( "Malibu" ) );
    }


//  Setting Hero Logo Method
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

//  Next Button Method
    private void nextToAdvancedClicked(){

        if (hero.equals( "Batman" )){
            checkBatmanAnswers();
        } else if (hero.equals( "Superman" )){
            checkSupermanAnswers();
        } else if (hero.equals( "Iron Man" )){
            checkIronManAnswers();
        } else {
            checkSpiderManAnswers();
        }

//        Playing 'next' sound
        mp = MediaPlayer.create( this,R.raw.page );
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

//        Connecting with next layout (Advanced
        Intent intent = new Intent( basic.this, advanced.class );
        Bundle extras = new Bundle(  );
        extras.putString("hero", hero);
        extras.putString( "playerName", playerName );
        intent.putExtras(extras);
        intent.putExtra( "scoreBasic", scoreBasic );
        startActivity( intent );

    }

//    Answers Creation Method
    private void answersCreation(){
        questionOneGroup = (ToggleButtonGroupTableLayout) findViewById(R.id.Question1_group  );

        ans11 = (RadioButton) findViewById( R.id.answer_Clark_Kent );
        ans12 = (RadioButton) findViewById( R.id.answer_Bruce_Wayne );
        ans13 = (RadioButton) findViewById( R.id.answer_Peter_Parker );
        ans14 = (RadioButton) findViewById( R.id.answer_Tony_Stark );
        ans31 = (RadioButton) findViewById( R.id.answer_New_York );
        ans32 = (RadioButton) findViewById( R.id.answer_Metropolis );
        ans33 = (RadioButton) findViewById( R.id.answer_Gotham );
        ans34 = (RadioButton) findViewById( R.id.answer_Malibu );
        laserBeam =(CheckBox) findViewById( R.id.Laser_beam );
        utilityBelt =(CheckBox) findViewById( R.id.Utility_belt );
        heatVision =(CheckBox) findViewById( R.id.Heat_vision );
        webShooters =(CheckBox) findViewById( R.id.Web_shooters );
        strengthSpeed =(CheckBox) findViewById( R.id.Strength_speed );
        flight =(CheckBox) findViewById( R.id.Flight );

        questionThreeGroup = (ToggleButtonGroupTableLayout) findViewById( R.id.Question3_group );
    }

//    Checking answers method - Superman
    private void checkSupermanAnswers(){
        scoreBasic = 0;
        int selectedQuestion1 = questionOneGroup.getCheckedRadioButtonId();
        int selectedQuestion3 = questionThreeGroup.getCheckedRadioButtonId();
        RadioButton corectAnswerQuestion1 = (RadioButton) findViewById( R.id.answer_Clark_Kent );
        RadioButton corectAnswerQuestion3 = (RadioButton) findViewById( R.id.answer_Metropolis );

        if(selectedQuestion1 == corectAnswerQuestion1.getId()){
            scoreBasic = scoreBasic + 1;
        }
        if(selectedQuestion3 == corectAnswerQuestion3.getId()){
            scoreBasic = scoreBasic + 1;
        }
        if(heatVision.isChecked() && strengthSpeed.isChecked() && flight.isChecked() && !laserBeam.isChecked() && !utilityBelt.isChecked() && !webShooters.isChecked()){
            scoreBasic = scoreBasic + 1;
        }

        String check = Integer.toString( scoreBasic );
        Log.i( "rezultat: ", check );
    }

//    Checking answers method - Batman
    private void checkBatmanAnswers(){
        int selectedQuestion1 = questionOneGroup.getCheckedRadioButtonId();
        int selectedQuestion3 = questionThreeGroup.getCheckedRadioButtonId();
        RadioButton corectAnswerQuestion1 = (RadioButton) findViewById( R.id.answer_Bruce_Wayne );
        RadioButton corectAnswerQuestion3 = (RadioButton) findViewById( R.id.answer_Gotham );

        if(selectedQuestion1 == corectAnswerQuestion1.getId()){
            scoreBasic = scoreBasic + 1;
        }
        if(selectedQuestion3 == corectAnswerQuestion3.getId()){
            scoreBasic = scoreBasic + 1;
        }
        if(utilityBelt.isChecked() && strengthSpeed.isChecked() && !laserBeam.isChecked() && !webShooters.isChecked() && !heatVision.isChecked() && !flight.isChecked()){
            scoreBasic = scoreBasic + 1;
        }

        String check = Integer.toString( scoreBasic );
        Log.i( "rezultat: ", check );
    }

//    Checking answers method - Iron Man
    private void checkIronManAnswers(){
        int selectedQuestion1 = questionOneGroup.getCheckedRadioButtonId();
        int selectedQuestion3 = questionThreeGroup.getCheckedRadioButtonId();
        RadioButton corectAnswerQuestion1 = (RadioButton) findViewById( R.id.answer_Tony_Stark );
        RadioButton corectAnswerQuestion3 = (RadioButton) findViewById( R.id.answer_Malibu );

        if(selectedQuestion1 == corectAnswerQuestion1.getId()){
            scoreBasic = scoreBasic + 1;
        }
        if(selectedQuestion3 == corectAnswerQuestion3.getId()){
            scoreBasic = scoreBasic + 1;
        }
        if(laserBeam.isChecked() && strengthSpeed.isChecked() && flight.isChecked() && !webShooters.isChecked() && !utilityBelt.isChecked() && !heatVision.isChecked() ){
            scoreBasic = scoreBasic + 1;
        }

        String check = Integer.toString( scoreBasic );
        Log.i( "rezultat: ", check );
    }

//    Checking answers method - Spider-Man
    private void checkSpiderManAnswers(){
        int selectedQuestion1 = questionOneGroup.getCheckedRadioButtonId();
        int selectedQuestion3 = questionThreeGroup.getCheckedRadioButtonId();
        RadioButton corectAnswerQuestion1 = (RadioButton) findViewById( R.id.answer_Peter_Parker );
        RadioButton corectAnswerQuestion3 = (RadioButton) findViewById( R.id.answer_New_York );

        if(selectedQuestion1 == corectAnswerQuestion1.getId()){
            scoreBasic = scoreBasic + 1;
        }
        if(selectedQuestion3 == corectAnswerQuestion3.getId()){
            scoreBasic = scoreBasic + 1;
        }
        if(webShooters.isChecked() && strengthSpeed.isChecked() && !laserBeam.isChecked() && !utilityBelt.isChecked() && !heatVision.isChecked() && !flight.isChecked()){
            scoreBasic = scoreBasic + 1;
        }

        String check = Integer.toString( scoreBasic );
        Log.i( "rezultat: ", check );
    }
}
