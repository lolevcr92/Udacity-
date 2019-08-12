package com.example.android.guardianews;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ArticlesLoader extends AsyncTaskLoader<List<Article>> {
    public static final String LOG_TAG = ArticlesLoader.class.getSimpleName();
    private String mUrl;

    public ArticlesLoader(Context context, String url) {
        super( context );
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Article> loadInBackground() {
        Log.i( LOG_TAG, "loadInBackground start" );

        if (mUrl == null) {
            return null;
        }
        ArrayList<Article> articles = QueryUtils.extractArticles( mUrl );
        return articles;
    }
}
