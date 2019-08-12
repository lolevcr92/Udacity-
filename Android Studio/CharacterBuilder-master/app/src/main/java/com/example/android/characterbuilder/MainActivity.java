package com.example.android.characterbuilder;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.PopupWindow;
import android.app.Dialog;


public class MainActivity extends AppCompatActivity {

    /**
     * Initial Attributes
     */
    int strengthOne = 2;
    int strengthTwo = 2;
    int agilityOne = 2;
    int agilityTwo = 2;
    int intelOne = 2;
    int intelTwo = 2;

    /**
     * Initial Stats
     */
    int cashOne = 999;
    int cashTwo = 999;
    int hpOne = 50;
    int hpTwo = 50;
    int attackOne = 10;
    int attackTwo = 10;
    int shieldOne = 10;
    int shieldTwo = 10;
    int magicOne = 2;
    int magicTwo = 2;

    /**
     * Initial points
     */
    int midPointsOne = 4;
    int midPointsTwo = 4;
    int latePointsOne = 4;
    int latePointsTwo = 4;
    int totalPointsOne = 8;
    int totalPointsTwo = 8;

    /**
     * Stats per attibute unit
     */
    final int strenghtToHp = 25;
    final int streghtToAttack = 5;
    final int agilityToAttack = 5;
    final int agilityToShield = 5;
    final int intelToShield = 5;
    final int intelToMagic = 2;

    final int goldPerPoint = 50;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        displayOne( cashOne, hpOne, attackOne, shieldOne, magicOne,
                strengthOne, agilityOne, intelOne, midPointsOne, latePointsOne );
        displayTwo( cashTwo, hpTwo, attackTwo, shieldTwo, magicTwo,
                strengthTwo, agilityTwo, intelTwo, midPointsTwo, latePointsTwo );
    }


    /**
     * Display methods players
     */

    public void displayOne(int cash, int hp, int attack, int shield,
                           int magic, int strength, int agility, int intel, int mid, int late) {
        TextView cashView = (TextView) findViewById( R.id.cash_view_one );
        TextView hpView = (TextView) findViewById( R.id.hp_view_one );
        TextView attackView = (TextView) findViewById( R.id.attack_view_one );
        TextView shieldView = (TextView) findViewById( R.id.shield_view_one );
        TextView magicView = (TextView) findViewById( R.id.magic_view_one );
        ProgressBar strengthBar = (ProgressBar) findViewById( R.id.stregth_view_bar_one );
        ProgressBar agilityBar = (ProgressBar) findViewById( R.id.agility_view_bar_one );
        ProgressBar intelBar = (ProgressBar) findViewById( R.id.intel_view_bar_one );
        TextView midView = (TextView) findViewById( R.id.available_mid_one );
        TextView lateView = (TextView) findViewById( R.id.available_late_one );

        cashView.setText( String.valueOf( cash ) );
        hpView.setText( String.valueOf( hp ) );
        attackView.setText( String.valueOf( attack ) );
        shieldView.setText( String.valueOf( shield ) );
        magicView.setText( String.valueOf( magic ) );
        strengthBar.setProgress( strength );
        agilityBar.setProgress( agility );
        intelBar.setProgress( intel );
        midView.setText( String.valueOf( mid ) );
        lateView.setText( String.valueOf( late ) );

    }

    public void displayTwo(int cash, int hp, int attack, int shield,
                           int magic, int strength, int agility, int intel, int mid, int late) {
        TextView cashView = (TextView) findViewById( R.id.cash_view_two );
        TextView hpView = (TextView) findViewById( R.id.hp_view_two );
        TextView attackView = (TextView) findViewById( R.id.attack_view_two );
        TextView shieldView = (TextView) findViewById( R.id.shield_view_two );
        TextView magicView = (TextView) findViewById( R.id.magic_view_two );
        ProgressBar strengthBar = (ProgressBar) findViewById( R.id.stregth_view_bar_two );
        ProgressBar agilityBar = (ProgressBar) findViewById( R.id.agility_view_bar_two );
        ProgressBar intelBar = (ProgressBar) findViewById( R.id.intel_view_bar_two );
        TextView midView = (TextView) findViewById( R.id.available_mid_two );
        TextView lateView = (TextView) findViewById( R.id.available_late_two );

        cashView.setText( String.valueOf( cash ) );
        hpView.setText( String.valueOf( hp ) );
        attackView.setText( String.valueOf( attack ) );
        shieldView.setText( String.valueOf( shield ) );
        magicView.setText( String.valueOf( magic ) );
        strengthBar.setProgress( strength );
        agilityBar.setProgress( agility );
        intelBar.setProgress( intel );
        midView.setText( String.valueOf( mid ) );
        lateView.setText( String.valueOf( late ) );
    }

    /**
     *  + & - buttons for mid player One
     */

        /**
        *    Mid game + & - methods for One
        */

        protected void midStrengthPlusOne(View v) {
        if ((strengthOne  < 8) && (midPointsOne > 0) ){

            strengthOne = strengthOne + 1;
            cashOne = cashOne - goldPerPoint;
            hpOne = hpOne + strenghtToHp;
            attackOne = attackOne + streghtToAttack;
            totalPointsOne = totalPointsOne - 1;
            midPointsOne = midPointsOne - 1;

            displayOne( cashOne, hpOne, attackOne, shieldOne, magicOne,
                    strengthOne, agilityOne, intelOne, midPointsOne, latePointsOne );
        } else {
            return;
        }
    }

    protected void midStrengthMinusOne(View v) {
        if ((strengthOne  > 2) && (midPointsOne < 4) ){

            strengthOne = strengthOne - 1;
            cashOne = cashOne + goldPerPoint;
            hpOne = hpOne - strenghtToHp;
            attackOne = attackOne - streghtToAttack;
            totalPointsOne = totalPointsOne + 1;
            midPointsOne = midPointsOne + 1;

            displayOne( cashOne, hpOne, attackOne, shieldOne, magicOne,
                    strengthOne, agilityOne, intelOne, midPointsOne, latePointsOne );
        } else {
            return;
        }
    }

    protected void midAgilityPlusOne(View v) {
        if ((agilityOne  < 8) && (midPointsOne > 0) ){

            agilityOne = agilityOne + 1;
            cashOne = cashOne - goldPerPoint;
            attackOne = attackOne + agilityToAttack;
            shieldOne = shieldOne + agilityToShield;
            totalPointsOne = totalPointsOne - 1;
            midPointsOne = midPointsOne - 1;

            displayOne( cashOne, hpOne, attackOne, shieldOne, magicOne,
                    strengthOne, agilityOne, intelOne, midPointsOne, latePointsOne );
        } else {
            return;
        }
    }

    protected void midAgilityMinusOne(View v) {
        if ((agilityOne  > 2) && (midPointsOne < 4) ){

            agilityOne = agilityOne - 1;
            cashOne = cashOne + goldPerPoint;
            attackOne = attackOne - agilityToAttack;
            shieldOne = shieldOne - agilityToShield;
            totalPointsOne = totalPointsOne + 1;
            midPointsOne = midPointsOne + 1;

            displayOne( cashOne, hpOne, attackOne, shieldOne, magicOne,
                    strengthOne, agilityOne, intelOne, midPointsOne, latePointsOne );
        } else {
            return;
        }
    }

    protected void midIntelPlusOne(View v) {
        if ((intelOne  < 8) && (midPointsOne > 0) ){

            intelOne = intelOne + 1;
            cashOne = cashOne - goldPerPoint;
            shieldOne = shieldOne + intelToShield;
            magicOne = magicOne + intelToMagic;
            totalPointsOne = totalPointsOne - 1;
            midPointsOne = midPointsOne - 1;

            displayOne( cashOne, hpOne, attackOne, shieldOne, magicOne,
                    strengthOne, agilityOne, intelOne, midPointsOne, latePointsOne );
        } else {
            return;
        }
    }

    protected void midIntelMinusOne(View v) {
        if ((intelOne  > 2) && (midPointsOne < 4) ){

            intelOne = intelOne - 1;
            cashOne = cashOne + goldPerPoint;
            shieldOne = shieldOne - intelToShield;
            magicOne = magicOne - intelToMagic;
            totalPointsOne = totalPointsOne + 1;
            midPointsOne = midPointsOne + 1;

            displayOne( cashOne, hpOne, attackOne, shieldOne, magicOne,
                    strengthOne, agilityOne, intelOne, midPointsOne, latePointsOne );
        } else {
            return;
        }
    }

        /**
        *    Mid game + & minus methods Two
        */

    protected void midStrengthPlusTwo(View v) {
        if ((strengthTwo  < 8) && (midPointsTwo > 0) ){

            strengthTwo = strengthTwo + 1;
            cashTwo = cashTwo - goldPerPoint;
            hpTwo = hpTwo + strenghtToHp;
            attackTwo = attackTwo + streghtToAttack;
            totalPointsTwo = totalPointsTwo - 1;
            midPointsTwo = midPointsTwo - 1;

            displayTwo( cashTwo, hpTwo, attackTwo, shieldTwo, magicTwo,
                    strengthTwo, agilityTwo, intelTwo, midPointsTwo, latePointsTwo );
        } else {
            return;
        }
    }

    protected void midStrengthMinusTwo(View v) {
        if ((strengthTwo  > 2) && (midPointsTwo < 4) ){

            strengthTwo = strengthTwo - 1;
            cashTwo = cashTwo + goldPerPoint;
            hpTwo = hpTwo - strenghtToHp;
            attackTwo = attackTwo - streghtToAttack;
            totalPointsTwo = totalPointsTwo + 1;
            midPointsTwo = midPointsTwo + 1;

            displayTwo( cashTwo, hpTwo, attackTwo, shieldTwo, magicTwo,
                    strengthTwo, agilityTwo, intelTwo, midPointsTwo, latePointsTwo );
        } else {
            return;
        }
    }
    protected void midAgilityPlusTwo(View v) {
        if ((agilityTwo  < 8) && (midPointsTwo > 0) ){

            agilityTwo = agilityTwo + 1;
            cashTwo = cashTwo - goldPerPoint;
            attackTwo = attackTwo + agilityToAttack;
            shieldTwo = shieldTwo + agilityToShield;
            totalPointsTwo = totalPointsTwo - 1;
            midPointsTwo = midPointsTwo - 1;

            displayTwo( cashTwo, hpTwo, attackTwo, shieldTwo, magicTwo,
                    strengthTwo, agilityTwo, intelTwo, midPointsTwo, latePointsTwo );
        } else {
            return;
        }
    }

    protected void midAgilityMinusTwo(View v) {
        if ((agilityTwo  > 2) && (midPointsTwo < 4) ){

            agilityTwo = agilityTwo - 1;
            cashTwo = cashTwo + goldPerPoint;
            attackTwo = attackTwo - agilityToAttack;
            shieldTwo = shieldTwo - agilityToShield;
            totalPointsTwo = totalPointsTwo + 1;
            midPointsTwo = midPointsTwo + 1;

            displayTwo( cashTwo, hpTwo, attackTwo, shieldTwo, magicTwo,
                    strengthTwo, agilityTwo, intelTwo, midPointsTwo, latePointsTwo );
        } else {
            return;
        }
    }

    protected void midIntelPlusTwo(View v) {
        if ((intelTwo  < 8) && (midPointsTwo > 0) ){

            intelTwo = intelTwo + 1;
            cashTwo = cashTwo - goldPerPoint;
            shieldTwo = shieldTwo + intelToShield;
            magicTwo = magicTwo + intelToMagic;
            totalPointsTwo = totalPointsTwo - 1;
            midPointsTwo = midPointsTwo - 1;

            displayTwo( cashTwo, hpTwo, attackTwo, shieldTwo, magicTwo,
                    strengthTwo, agilityTwo, intelTwo, midPointsTwo, latePointsTwo );
        } else {
            return;
        }
    }

    protected void midIntelMinusTwo(View v) {
        if ((intelTwo  > 2) && (midPointsTwo < 4) ){

            intelTwo = intelTwo - 1;
            cashTwo = cashTwo + goldPerPoint;
            shieldTwo = shieldTwo - intelToShield;
            magicTwo = magicTwo - intelToMagic;
            totalPointsTwo = totalPointsTwo + 1;
            midPointsTwo = midPointsTwo + 1;

            displayTwo( cashTwo, hpTwo, attackTwo, shieldTwo, magicTwo,
                    strengthTwo, agilityTwo, intelTwo, midPointsTwo, latePointsTwo );
        } else {
            return;
        }
    }

    /**
     *  + & - buttons for Late player One
     */

        /**
         *    Late game + & - methods for One
         */
        protected void lateStrengthPlusOne(View v) {
        if ((strengthOne  < 8) && (latePointsOne > 0) && (midPointsOne == 0) ){

            strengthOne = strengthOne + 1;
            cashOne = cashOne - goldPerPoint;
            hpOne = hpOne + strenghtToHp;
            attackOne = attackOne + streghtToAttack;
            totalPointsOne = totalPointsOne - 1;
            latePointsOne = latePointsOne - 1;

            displayOne( cashOne, hpOne, attackOne, shieldOne, magicOne,
                    strengthOne, agilityOne, intelOne, latePointsOne, latePointsOne );
        } else {
            return;
        }
    }

    protected void lateStrengthMinusOne(View v) {
        if ((strengthOne  > 2) && (latePointsOne < 4) && (midPointsOne == 0)){

            strengthOne = strengthOne - 1;
            cashOne = cashOne + goldPerPoint;
            hpOne = hpOne - strenghtToHp;
            attackOne = attackOne - streghtToAttack;
            totalPointsOne = totalPointsOne + 1;
            latePointsOne = latePointsOne + 1;

            displayOne( cashOne, hpOne, attackOne, shieldOne, magicOne,
                    strengthOne, agilityOne, intelOne, latePointsOne, latePointsOne );
        } else {
            return;
        }
    }

    protected void lateAgilityPlusOne(View v) {
        if ((agilityOne  < 8) && (latePointsOne > 0) && (midPointsOne == 0)){

            agilityOne = agilityOne + 1;
            cashOne = cashOne - goldPerPoint;
            attackOne = attackOne + agilityToAttack;
            shieldOne = shieldOne + agilityToShield;
            totalPointsOne = totalPointsOne - 1;
            latePointsOne = latePointsOne - 1;

            displayOne( cashOne, hpOne, attackOne, shieldOne, magicOne,
                    strengthOne, agilityOne, intelOne, latePointsOne, latePointsOne );
        } else {
            return;
        }
    }

    protected void lateAgilityMinusOne(View v) {
        if ((agilityOne  > 2) && (latePointsOne < 4) && (midPointsOne == 0)){

            agilityOne = agilityOne - 1;
            cashOne = cashOne + goldPerPoint;
            attackOne = attackOne - agilityToAttack;
            shieldOne = shieldOne - agilityToShield;
            totalPointsOne = totalPointsOne + 1;
            latePointsOne = latePointsOne + 1;

            displayOne( cashOne, hpOne, attackOne, shieldOne, magicOne,
                    strengthOne, agilityOne, intelOne, latePointsOne, latePointsOne );
        } else {
            return;
        }
    }

    protected void lateIntelPlusOne(View v) {
        if ((intelOne  < 8) && (latePointsOne > 0) && (midPointsOne == 0)){

            intelOne = intelOne + 1;
            cashOne = cashOne - goldPerPoint;
            shieldOne = shieldOne + intelToShield;
            magicOne = magicOne + intelToMagic;
            totalPointsOne = totalPointsOne - 1;
            latePointsOne = latePointsOne - 1;

            displayOne( cashOne, hpOne, attackOne, shieldOne, magicOne,
                    strengthOne, agilityOne, intelOne, latePointsOne, latePointsOne );
        } else {
            return;
        }
    }

    protected void lateIntelMinusOne(View v) {
        if ((intelOne  > 2) && (latePointsOne < 4) && (midPointsOne == 0)){

            intelOne = intelOne - 1;
            cashOne = cashOne + goldPerPoint;
            shieldOne = shieldOne - intelToShield;
            magicOne = magicOne - intelToMagic;
            totalPointsOne = totalPointsOne + 1;
            latePointsOne = latePointsOne + 1;

            displayOne( cashOne, hpOne, attackOne, shieldOne, magicOne,
                    strengthOne, agilityOne, intelOne, latePointsOne, latePointsOne );
        } else {
            return;
        }
    }

        /**
         *    Late game + & - methods for One
         */
        protected void lateStrengthPlusTwo(View v) {
            if ((strengthTwo  < 8) && (latePointsTwo > 0) && (midPointsTwo == 0) ){

                strengthTwo = strengthTwo + 1;
                cashTwo = cashTwo - goldPerPoint;
                hpTwo = hpTwo + strenghtToHp;
                attackTwo = attackTwo + streghtToAttack;
                totalPointsTwo = totalPointsTwo - 1;
                latePointsTwo = latePointsTwo - 1;

                displayTwo( cashTwo, hpTwo, attackTwo, shieldTwo, magicTwo,
                        strengthTwo, agilityTwo, intelTwo, latePointsTwo, latePointsTwo );
            } else {
                return;
            }
        }

    protected void lateStrengthMinusTwo(View v) {
        if ((strengthTwo  > 2) && (latePointsTwo < 4) && (midPointsTwo == 0)){

            strengthTwo = strengthTwo - 1;
            cashTwo = cashTwo + goldPerPoint;
            hpTwo = hpTwo - strenghtToHp;
            attackTwo = attackTwo - streghtToAttack;
            totalPointsTwo = totalPointsTwo + 1;
            latePointsTwo = latePointsTwo + 1;

            displayTwo( cashTwo, hpTwo, attackTwo, shieldTwo, magicTwo,
                    strengthTwo, agilityTwo, intelTwo, latePointsTwo, latePointsTwo );
        } else {
            return;
        }
    }

    protected void lateAgilityPlusTwo(View v) {
        if ((agilityTwo  < 8) && (latePointsTwo > 0) && (midPointsTwo == 0)){

            agilityTwo = agilityTwo + 1;
            cashTwo = cashTwo - goldPerPoint;
            attackTwo = attackTwo + agilityToAttack;
            shieldTwo = shieldTwo + agilityToShield;
            totalPointsTwo = totalPointsTwo - 1;
            latePointsTwo = latePointsTwo - 1;

            displayTwo( cashTwo, hpTwo, attackTwo, shieldTwo, magicTwo,
                    strengthTwo, agilityTwo, intelTwo, latePointsTwo, latePointsTwo );
        } else {
            return;
        }
    }

    protected void lateAgilityMinusTwo(View v) {
        if ((agilityTwo  > 2) && (latePointsTwo < 4) && (midPointsTwo == 0)){

            agilityTwo = agilityTwo - 1;
            cashTwo = cashTwo + goldPerPoint;
            attackTwo = attackTwo - agilityToAttack;
            shieldTwo = shieldTwo - agilityToShield;
            totalPointsTwo = totalPointsTwo + 1;
            latePointsTwo = latePointsTwo + 1;

            displayTwo( cashTwo, hpTwo, attackTwo, shieldTwo, magicTwo,
                    strengthTwo, agilityTwo, intelTwo, latePointsTwo, latePointsTwo );
        } else {
            return;
        }
    }

    protected void lateIntelPlusTwo(View v) {
        if ((intelTwo  < 8) && (latePointsTwo > 0) && (midPointsTwo == 0)){

            intelTwo = intelTwo + 1;
            cashTwo = cashTwo - goldPerPoint;
            shieldTwo = shieldTwo + intelToShield;
            magicTwo = magicTwo + intelToMagic;
            totalPointsTwo = totalPointsTwo - 1;
            latePointsTwo = latePointsTwo - 1;

            displayTwo( cashTwo, hpTwo, attackTwo, shieldTwo, magicTwo,
                    strengthTwo, agilityTwo, intelTwo, latePointsTwo, latePointsTwo );
        } else {
            return;
        }
    }

    protected void lateIntelMinusTwo(View v) {
        if ((intelTwo  > 2) && (latePointsTwo < 4) && (midPointsTwo == 0)){

            intelTwo = intelTwo - 1;
            cashTwo = cashTwo + goldPerPoint;
            shieldTwo = shieldTwo - intelToShield;
            magicTwo = magicTwo - intelToMagic;
            totalPointsTwo = totalPointsTwo + 1;
            latePointsTwo = latePointsTwo + 1;

            displayTwo( cashTwo, hpTwo, attackTwo, shieldTwo, magicTwo,
                    strengthTwo, agilityTwo, intelTwo, latePointsTwo, latePointsTwo );
        } else {
            return;
        }
    }


    /**
     *   Reset method
     */
    protected void reset(View v){
        strengthOne = 2;
        strengthTwo = 2;
        agilityOne = 2;
        agilityTwo = 2;
        intelOne = 2;
        intelTwo = 2;
        cashOne = 999;
        cashTwo = 999;
        hpOne = 50;
        hpTwo = 50;
        attackOne = 10;
        attackTwo = 10;
        shieldOne = 10;
        shieldTwo = 10;
        magicOne = 2;
        magicTwo = 2;
        midPointsOne = 4;
        midPointsTwo = 4;
        latePointsOne = 4;
        latePointsTwo = 4;
        totalPointsOne = 8;
        totalPointsTwo = 8;

        displayOne( cashOne, hpOne, attackOne, shieldOne, magicOne,
                strengthOne, agilityOne, intelOne, midPointsOne, latePointsOne );
        displayTwo( cashTwo, hpTwo, attackTwo, shieldTwo, magicTwo,
                strengthTwo, agilityTwo, intelTwo, midPointsTwo, latePointsTwo );
    }

    /**
     *   Info method
     */
    protected void infoPush(View v){
        startActivity( new Intent( MainActivity.this,InfoPop.class ) );

    }

    /**
     *  Figth method
     */
    protected void figth(View v){
        if (strengthOne>strengthTwo){
            startActivity( new Intent( MainActivity.this,OneWinPop.class ) );
        }else{
            startActivity( new Intent( MainActivity.this,TwoWinPop.class ) );
        }
    }

}

