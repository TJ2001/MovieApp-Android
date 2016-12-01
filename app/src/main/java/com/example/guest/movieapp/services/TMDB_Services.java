package com.example.guest.movieapp.services;

import android.util.Log;

import com.example.guest.movieapp.Constants;
import com.example.guest.movieapp.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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

    public ArrayList<Movie> processResults (String Data) {
        ArrayList<Movie> movies = new ArrayList<>();

        try {
            JSONObject movieJSON = new JSONObject(Data);

            JSONArray results = movieJSON.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {
                JSONObject resultsJSON = results.getJSONObject(i);
                String Poster_path = Constants.imageURL + resultsJSON.getString("poster_path");
                String Title = resultsJSON.getString("title");
                String Overview = resultsJSON.getString("overview");
                String releaseDate = resultsJSON.getString("release_date");
                String voteAverage = resultsJSON.getString("vote_average");
                String movieId = resultsJSON.getString("id");
                ArrayList<String> genreId = new ArrayList<>();
                JSONArray genreList = resultsJSON.getJSONArray("genre_ids");

                for (int j = 0; j < genreList.length(); j++) {
                    genreId.add(genreList.getString(j));
                }
                Movie newMovie = new Movie(Poster_path, Overview, releaseDate, genreId, Title, voteAverage, movieId);
                movies.add(newMovie);
                Log.v(TAG, "new movies: " + newMovie.getTitle());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movies;
    }
}
