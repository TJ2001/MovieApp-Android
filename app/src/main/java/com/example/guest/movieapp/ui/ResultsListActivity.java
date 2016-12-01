package com.example.guest.movieapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.guest.movieapp.models.Movie;
import com.example.guest.movieapp.R;
import com.example.guest.movieapp.services.TMDB_Services;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ResultsListActivity extends AppCompatActivity {
    @Bind(R.id.listView) ListView mListView;
    ArrayList<Movie> mMovies = new ArrayList<>();
    public static final String TAG = ResultsListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");

        getMovies(title);
    }

    private void getMovies(String title) {
        final TMDB_Services MovieFINDER = new TMDB_Services();

        MovieFINDER.findMovies(title, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mMovies = MovieFINDER.processResults(response.body().string());

                ResultsListActivity.this.runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        String[] movieTitles = new String[mMovies.size()];
                        for (int i = 0; i < movieTitles.length; i ++) {
                            movieTitles[i] = mMovies.get(i).getTitle();
                        }

                        ArrayAdapter adapter = new ArrayAdapter(ResultsListActivity.this,
                                android.R.layout.simple_list_item_1, movieTitles);
                        mListView.setAdapter(adapter);
                    }
                });



            }
        });
    }
}
