package com.example.android.guardianews;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class QueryUtils {

    public static final String LOG_TAG = QueryUtils.class.getSimpleName();

    public static final int READ_TIMEOUT = 10000;
    public static final int CONNECT_TIMEOUT = 15000;
    public static final int HTTP_OK = 200;

    /*Private constructor because no one should ever create an object*/
    private QueryUtils() {
    }

    /*Return a list of Article objects that has been build up from parsing a JSON response*/
    public static ArrayList<Article> extractArticles(String stringUrl) {

        /*Create URL object*/
        URL urlGuardian = createUrl( stringUrl );

        /*Create JSON Response*/
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest( urlGuardian );
        } catch (IOException e) {
            Log.e( LOG_TAG, "Error closing input stream: ", e );
        }

        /*Create an empty ArrayList that we can start adding articles to*/
        ArrayList<Article> articles = new ArrayList<>();

        try {
            JSONObject rawData = new JSONObject( jsonResponse );
            JSONArray results = rawData.getJSONObject( "response" ).getJSONArray( "results" );

            for (int i = 0; i < results.length(); i++) {
                JSONObject temporaryResult = results.getJSONObject( i );

                String title = temporaryResult.getString( "webTitle" );
                String date = temporaryResult.getString( "webPublicationDate" ).replace( "T", " " ).replace( "Z", "" );
                String pillar = temporaryResult.getString( "pillarName" );
                String sectionName = temporaryResult.getString( "sectionName" );
                String articleUrl = temporaryResult.getString( "webUrl" );

                String imageUrl = null;

                try {
                    JSONObject fieldForImage = temporaryResult.getJSONObject( "fields" );
                    imageUrl = fieldForImage.getString( "thumbnail" );
                } catch (JSONException e) {
                    Log.i( LOG_TAG, "Article does not have image" );
                }


                JSONArray tags = temporaryResult.getJSONArray( "tags" );
                JSONObject firstTag = tags.getJSONObject( 0 );
                String author = firstTag.getString( "webTitle" );

                articles.add( new Article( title, date, pillar, sectionName, author, articleUrl, imageUrl ) );
            }
        } catch (JSONException e) {
            Log.e( LOG_TAG, "Problem parsing the articles JSON results. ", e );
        }
        return articles;
    }

    /*Creating URL from string*/
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL( stringUrl );
        } catch (MalformedURLException e) {
            Log.e( LOG_TAG, "Error with creating URL: ", e );
        }
        return url;
    }

    /*Create method for jsonRespons*/
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonRespons = "";

        if (url == null) {
            return jsonRespons;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout( READ_TIMEOUT );
            urlConnection.setConnectTimeout( CONNECT_TIMEOUT );
            urlConnection.setRequestMethod( "GET" );
            urlConnection.connect();

            if (urlConnection.getResponseCode() == HTTP_OK) {
                inputStream = urlConnection.getInputStream();
                jsonRespons = readFromStream( inputStream );
            } else {
                Log.e( LOG_TAG, "Error code: " + urlConnection.getResponseCode() );
            }
        } catch (IOException e) {
            Log.e( LOG_TAG, "Problem retrieving the articles JSON results. ", e );
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonRespons;
    }

    /*Create method for reading input stream*/
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader( inputStream, Charset.forName( "UTF-8" ) );
            BufferedReader reader = new BufferedReader( inputStreamReader );
            String line = reader.readLine();
            while (line != null) {
                output.append( line );
                line = reader.readLine();
            }
        }
        return output.toString();
    }

}
