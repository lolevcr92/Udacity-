package com.example.android.quakereport;

import android.support.v4.content.ContextCompat;
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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    public static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /** Sample JSON response for a USGS query */
    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Return a list of {@link Earthquake} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<Earthquake> extractEarthquakes(String stringUrl) {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.i( "ExtractEarthquakes", "starts" );

        /*Create URL object*/
        URL urlUSGS = createUrl( stringUrl );

        /*Create jsonResponse*/
        String jsonResponse = null;
        try{
            jsonResponse = makeHttpRequest( urlUSGS );
        } catch (IOException e){
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<Earthquake> earthquakes = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {
            JSONObject rawData = new JSONObject( jsonResponse );
            // build up a list of Earthquake objects with the corresponding data.
            JSONArray cutremure = rawData.getJSONArray( "features" );

            for (int i = 0; i < cutremure.length(); i++){
                JSONObject temporary = cutremure.getJSONObject( i );

                double magnitude = temporary.getJSONObject( "properties" ).getDouble( "mag" );
                String location = temporary.getJSONObject( "properties" ).getString( "place" );
                String date = temporary.getJSONObject( "properties" ).getString( "time" );
                String urlFeature = temporary.getJSONObject( "properties" ).getString("url");
                String nearOf, place;

                /*Formating magnitude*/
                DecimalFormat formatter = new DecimalFormat( "0.0" );
                String magnitudeDecimal = formatter.format(magnitude);
                /*Getting  color for earthquake magnitude*/
                int magnitudeColorResourceId = getMagnitudeColor( magnitudeDecimal );

                /*Formating location in 2 separet*/
                if (location.contains(" of ")){
                    int idx = location.indexOf( " of " );

                    nearOf = location.substring(0, idx + 4);
                    place = location.substring(idx + 4, location.length());

                } else {
                    nearOf = "Near of ";
                    place = location;
                }

                /*Formating time from milliseconds to a more readeble and common date-time type*/
                long timeInMilliseconds = Long.parseLong(date);
                Date dateObject = new Date(timeInMilliseconds);
                SimpleDateFormat dateFormatter = new SimpleDateFormat( "MMM DD, yyyy\n hh:mm aa" );
                String dateToDisplay = dateFormatter.format(dateObject);

                earthquakes.add(new Earthquake(nearOf,place,magnitudeDecimal,magnitudeColorResourceId, dateToDisplay, urlFeature ) );

            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }



    public static int getMagnitudeColor(String magnitude) {
        int magnitudeColorResourceId;
        switch (magnitude.substring( 0,1 )) {
            case "0":
            case "1":
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case "2":
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case "3":
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case "4":
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case "5":
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case "6":
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case "7":
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case "8":
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case "9":
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return magnitudeColorResourceId;
    }

    private static URL createUrl(String stringUrl){
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e){
            Log.e(LOG_TAG, "Error with creating URL", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException{
        String jsonResponse = "";

        if (url == null){ return jsonResponse;}

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout( 10000 );
            urlConnection.setConnectTimeout( 15000 );
            urlConnection.setRequestMethod( "GET" );
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e (LOG_TAG, "Problem retrieving the earthquake JSON results. ", e);
        } finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
            if (inputStream != null){
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream (InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder(  );
        if(inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader( inputStream, Charset.forName("UTF-8") );
            BufferedReader reader = new BufferedReader( inputStreamReader );
            String line = reader.readLine();
            while (line != null){
                output.append(line);
                line = reader.readLine();
            }
        }
        return  output.toString();
    }
}