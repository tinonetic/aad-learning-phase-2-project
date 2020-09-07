package com.tinonetic.gadsleaderboard.api;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class ApiClient {
    // we don't want it instantiated
    private  ApiClient() {}

    private static final String TAG = ApiClient.class.getSimpleName();
    public static final String BASE_API_URL = "https://gadsapi.herokuapp.com";
    public static final Uri BASE_URI = Uri.parse(BASE_API_URL);

    // builds the URL based on the BASE_API_URL and the given category
    public static URL buildUrl(String resource){
        URL url = null;
        Uri uri = Uri.withAppendedPath(BASE_URI,resource).buildUpon()
                .build();
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getJson(URL url) throws IOException {
        Log.d(TAG, "START getJson() with URL:" + url.toString());

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            Log.i(TAG, "getting Input String for URL:" + url.toString());

            InputStream stream = connection.getInputStream();
            Scanner scanner = new Scanner(stream);
            //read everything
            scanner.useDelimiter("\\A");

            boolean hasData = scanner.hasNext();
            if (hasData) {
                return scanner.next();
            } else {
                return null;
            }
        }catch (Exception e){
            Log.e("Error", e.toString());
            return null;
        }
        finally {
            connection.disconnect();

            Log.d(TAG, "FINISH getJson() with URL:" + url.toString());
        }
    }
}
