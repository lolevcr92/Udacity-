package com.example.android.eyesonbucharest;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Lolev Cristian on 4/26/2018.
 */

public class StorageAdapter extends ArrayAdapter<Storage> {

    private int mColorResourceId;

    private static final String LOG_TAG = StorageAdapter.class.getSimpleName();

    /*Constructor*/
    public StorageAdapter (Activity context, ArrayList<Storage> storage){
        super(context, 0, storage);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        final Storage currentStorage = getItem( position );

        /*Views setter*/
        ImageView placeImage = (ImageView) listItemView.findViewById( R.id.place_image );

        /*Name text view identifier and setter*/
        TextView nameTextView = (TextView) listItemView.findViewById( R.id.place_name );
        nameTextView.setText( currentStorage.getName() );

        /*Location view identifier and setter*/
        TextView locationView = (TextView) listItemView.findViewById( R.id.place_location );
        locationView.setText( currentStorage.getLocation() );

        TextView moreButton = (TextView) listItemView.findViewById( R.id.place_more_button );
        ImageView playImage = (ImageView) listItemView.findViewById( R.id.play_play_image );

        LinearLayout layout = (LinearLayout) listItemView.findViewById( R.id.view_list_item );

        if (currentStorage.hasImage()){
            placeImage.setImageResource(currentStorage.getImageResourceId());
            placeImage.setVisibility( View.VISIBLE );
            moreButton.setVisibility( View.VISIBLE );
            playImage.setVisibility( View.INVISIBLE );

            moreButton.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri webpage = Uri.parse( currentStorage.getSite() );
                    Intent intent = new Intent( Intent.ACTION_VIEW, webpage );
                    getContext().startActivity(intent);
                }
            } );

        } else {
            placeImage.setVisibility(View.GONE);
            moreButton.setVisibility( View.GONE );
            playImage.setVisibility( View.VISIBLE );
            ViewGroup.LayoutParams params = layout.getLayoutParams();
            DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
            params.height = Math.round ( 1500/ (metrics.densityDpi/ 160f));
            layout.setLayoutParams(params);
        }

        return listItemView;
    }
}
