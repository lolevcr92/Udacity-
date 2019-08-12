package com.example.android.meetthehero;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by Lolev Cristian on 2/2/2018.
 */

public class advanced extends AppCompatActivity {

    String hero, playerName;
    int scoreBasic, scoreAdvanced;
    EditText editText;
    Intent intentFromBasic;
    Button nextToPro;
    ImageButton playLine;
    MediaPlayer line, mp;
    ToggleButtonGroupTableLayout questionFourGroup, questionSixGroup;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.advanced );

//        Getting data from last layout (basic)
        intentFromBasic = getIntent();
        Bundle extras = intentFromBasic.getExtras();
        hero = extras.getString( "hero" );
        playerName = extras.getString( "playerName" );
        hero = intentFromBasic.getStringExtra( "hero" );
        scoreBasic = intentFromBasic.getIntExtra( "scoreBasic", 0 );

//        Setting the layout and answers depending of the previous hero pick
        setHeroLogo();
        answersCreation();

//        Setting Button to next layout (pro Batman/Superman/Iron Man/Spider-Man)
        nextToPro = (Button) findViewById( R.id.next_to_pro );
        nextToPro.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextToProClicked();
            }
        } );

//        Set up sound for Question 5
        playLine = (ImageButton) findViewById( R.id.play_line );
        playLine.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                line.start();
            }
        } );

        Log.i( "score from basic: ", String.valueOf( scoreBasic ) );
        Log.i( "hero: ", hero.toString() );
        Log.i( "playername ", playerName.toString() );

    }

//    Setting Hero Logo Method
    private void setHeroLogo() {
        ImageView heroLogo = (ImageView) findViewById( R.id.chosen_hero );
        Resources res = getResources();

        if (hero.equals( "Batman" )) {
            heroLogo.setImageDrawable( res.getDrawable( R.drawable.batmaname ) );
        } else if (hero.equals( "Superman" )) {
            heroLogo.setImageDrawable( res.getDrawable( R.drawable.supermanname ) );
        } else if (hero.equals( "Iron Man" )) {
            heroLogo.setImageDrawable( res.getDrawable( R.drawable.ironmaname ) );
        } else {
            heroLogo.setImageDrawable( res.getDrawable( R.drawable.spidername ) );
        }
    }

//    Next Button Method
    private void nextToProClicked() {
        Intent intent;

        if (hero.equals( "Batman" )) {
            checkBatmanAnswers();
            intent = new Intent( this, proBatman.class );
            Bundle extras = new Bundle();
            extras.putString( "hero", hero );
            extras.putString( "playerName", playerName );
            intent.putExtras( extras );
            intent.putExtra( "scoreAdvanced", scoreAdvanced );
            startActivity( intent );
        } else if (hero.equals( "Superman" )) {
            checkSupermanAnswers();
            intent = new Intent( this, proSuperman.class );
            Bundle extras = new Bundle();
            extras.putString( "hero", hero );
            extras.putString( "playerName", playerName );
            intent.putExtras( extras );
            intent.putExtra( "scoreAdvanced", scoreAdvanced );
            startActivity( intent );
        } else if (hero.equals( "Iron Man" )) {
            checkIronManAnswers();
            intent = new Intent( this, proIronman.class );
            Bundle extras = new Bundle();
            extras.putString( "hero", hero );
            extras.putString( "playerName", playerName );
            intent.putExtras( extras );
            intent.putExtra( "scoreAdvanced", scoreAdvanced );
            startActivity( intent );
        } else {
            checkSpiderManAnswers();
            intent = new Intent( this, proSpiderman.class );
            Bundle extras = new Bundle();
            extras.putString( "hero", hero );
            extras.putString( "playerName", playerName );
            intent.putExtras( extras );
            intent.putExtra( "scoreAdvanced", scoreAdvanced );
            startActivity( intent );
        }

//        Playing 'next' sound
        mp = MediaPlayer.create( this, R.raw.page );
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

//    Answers Creation Method
    private void answersCreation() {
        questionFourGroup = (ToggleButtonGroupTableLayout) findViewById( R.id.Question4_group );
        editText = (EditText) findViewById( R.id.Question5_answer );
        questionSixGroup = (ToggleButtonGroupTableLayout) findViewById( R.id.Question6_group );

        if (hero.equals( "Batman" )) {
            line = MediaPlayer.create( this, R.raw.batmanquote );
        } else if (hero.equals( "Superman" )) {
            line = MediaPlayer.create( this, R.raw.supermanquote );
        } else if (hero.equals( "Iron Man" )) {
            line = MediaPlayer.create( this, R.raw.ironmanquote );
        } else if (hero.equals( "Spider-Man" )) {
            line = MediaPlayer.create( this, R.raw.spidermanquote );
        }
    }

//    Checking answers method - Batman
    private void checkBatmanAnswers() {
        int selectedQuestion4 = questionFourGroup.getCheckedRadioButtonId();
        int selectedQuestion6 = questionSixGroup.getCheckedRadioButtonId();
        String answerQuestion5 = editText.getText().toString();
        RadioButton correctAnswerQuestion4 = (RadioButton) findViewById( R.id.answer_Selina_Kyle );
        RadioButton correctAnswerQuestion6 = (RadioButton) findViewById( R.id.answer_Joker );

        scoreAdvanced = scoreBasic;

        if (selectedQuestion4 == correctAnswerQuestion4.getId()) {
            scoreAdvanced++;
        }
        if (answerQuestion5.equals( "yes" )) {
            scoreAdvanced++;
        }
        if (selectedQuestion6 == correctAnswerQuestion6.getId()) {
            scoreAdvanced++;
        }

        String check = Integer.toString( scoreAdvanced );
        Log.i( "rezultat: ", check );

    }

//    Checking answers method - Superman
    private void checkSupermanAnswers() {
        int selectedQuestion4 = questionFourGroup.getCheckedRadioButtonId();
        int selectedQuestion6 = questionSixGroup.getCheckedRadioButtonId();
        String answerQuestion5 = editText.getText().toString();
        RadioButton correctAnswerQuestion4 = (RadioButton) findViewById( R.id.answer_Lois_Lane );
        RadioButton correctAnswerQuestion6 = (RadioButton) findViewById( R.id.answer_Lex_Luthor );

        scoreAdvanced = scoreBasic;

        if (selectedQuestion4 == correctAnswerQuestion4.getId()) {
            scoreAdvanced++;
        }
        if (answerQuestion5.equals( "yes" )) {
            scoreAdvanced++;
        }
        if (selectedQuestion6 == correctAnswerQuestion6.getId()) {
            scoreAdvanced++;
        }

        String check = Integer.toString( scoreAdvanced );
        Log.i( "rezultat: ", check );

    }

//    Checking answers method - Iron Man
    private void checkIronManAnswers() {
        int selectedQuestion4 = questionFourGroup.getCheckedRadioButtonId();
        int selectedQuestion6 = questionSixGroup.getCheckedRadioButtonId();
        String answerQuestion5 = editText.getText().toString();
        RadioButton correctAnswerQuestion4 = (RadioButton) findViewById( R.id.answer_Pepper_Pots );
        RadioButton correctAnswerQuestion6 = (RadioButton) findViewById( R.id.answer_Mandarin );

        scoreAdvanced = scoreBasic;

        if (selectedQuestion4 == correctAnswerQuestion4.getId()) {
            scoreAdvanced++;
        }
        if (answerQuestion5.equals( "yes" )) {
            scoreAdvanced++;
        }
        if (selectedQuestion6 == correctAnswerQuestion6.getId()) {
            scoreAdvanced++;
        }

        String check = Integer.toString( scoreAdvanced );
        Log.i( "rezultat: ", check );

    }

//    Checking answers method - Spider-Man
    private void checkSpiderManAnswers() {
        int selectedQuestion4 = questionFourGroup.getCheckedRadioButtonId();
        int selectedQuestion6 = questionSixGroup.getCheckedRadioButtonId();
        String answerQuestion5 = editText.getText().toString();
        RadioButton correctAnswerQuestion4 = (RadioButton) findViewById( R.id.answer_Mary_Jane );
        RadioButton correctAnswerQuestion6 = (RadioButton) findViewById( R.id.answer_Doctor_Octopus );

        scoreAdvanced = scoreBasic;

        if (selectedQuestion4 == correctAnswerQuestion4.getId()) {
            scoreAdvanced++;
        }
        if (answerQuestion5.equals( "yes" )) {
            scoreAdvanced++;
        }
        if (selectedQuestion6 == correctAnswerQuestion6.getId()) {
            scoreAdvanced++;
        }

        String check = Integer.toString( scoreAdvanced );
        Log.i( "rezultat: ", check );

    }
}
