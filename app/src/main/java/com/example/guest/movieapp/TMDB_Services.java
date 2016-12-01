package com.example.guest.movieapp;

import android.util.Log;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class TMDB_Services {
    public static final String TAG = TMDB_Services.class.getSimpleName();

    public static void findMovies(String title, Callback callback){
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.API_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.QUERY_PARAMS, title);
        urlBuilder.addQueryParameter(Constants.INCLUDE_ADULT, "flase");
        urlBuilder.addQueryParameter(Constants.LANGUAGE, "en-US");
        urlBuilder.addQueryParameter(Constants.API_KEY, Constants.TMDB_API_KEY);
        String url = urlBuilder.build().toString();

        Log.v("movie Service", "url: " + url);

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
