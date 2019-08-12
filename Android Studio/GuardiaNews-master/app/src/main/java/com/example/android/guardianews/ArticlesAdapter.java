package com.example.android.guardianews;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ArticlesAdapter extends ArrayAdapter<Article> {

    private static final String LOG_TAG = ArticlesAdapter.class.getSimpleName();

    /*Constructor*/
    public ArticlesAdapter(Activity context, ArrayList<Article> articles) {
        super( context, 0, articles );
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from( getContext() ).inflate( R.layout.list_item, parent, false );
        }

        final Article currentArticle = getItem( position );

        TextView titleTextView = (TextView) listItemView.findViewById( R.id.article_title );
        titleTextView.setText( currentArticle.getTitle() );

        TextView dateTextView = (TextView) listItemView.findViewById( R.id.article_date );
        dateTextView.setText( currentArticle.getDate() );

        TextView pillarTextView = (TextView) listItemView.findViewById( R.id.article_pillar );
        pillarTextView.setText( currentArticle.getPillar() );

        TextView sectionTextView = (TextView) listItemView.findViewById( R.id.article_section );
        sectionTextView.setText( currentArticle.getSectionName() );

        TextView authorTextView = (TextView) listItemView.findViewById( R.id.article_author );
        authorTextView.setText( currentArticle.getAuthor() );

        ImageView imageView = (ImageView) listItemView.findViewById( R.id.article_image );
        Picasso.with( getContext() ).load( currentArticle.getImageUrl() ).into( imageView );

        return listItemView;
    }
}

