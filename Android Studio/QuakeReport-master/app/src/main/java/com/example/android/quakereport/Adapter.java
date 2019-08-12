package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Lolev Cristian on 5/17/2018.
 */

public class Adapter extends ArrayAdapter<Earthquake> {

    private static final String LOG_TAG = Adapter.class.getSimpleName();

    /*Constructor*/
    public Adapter (Activity context, ArrayList<Earthquake> quakes){
        super(context, 0, quakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        final Earthquake currentQuake = getItem( position );

        TextView magnitudeTextView = (TextView) listItemView.findViewById( R.id.magnitude_view );
        magnitudeTextView.setText( String.valueOf(currentQuake.getMagniture()  ) );

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();
        int magnitudeColor = ContextCompat.getColor( getContext(),currentQuake.getMagnitudeColorResourceId()  );
        magnitudeCircle.setColor( magnitudeColor );

        TextView nearOfTextView = (TextView) listItemView.findViewById( R.id.near_view);
        nearOfTextView.setText( currentQuake.getNearOf() );

        TextView locationTextView = (TextView) listItemView.findViewById( R.id.location_view );
        locationTextView.setText( currentQuake.getLocation() );

        TextView dateTextView = (TextView) listItemView.findViewById( R.id.date_view );
        dateTextView.setText( currentQuake.getDate() );

        return listItemView;
    }
}
