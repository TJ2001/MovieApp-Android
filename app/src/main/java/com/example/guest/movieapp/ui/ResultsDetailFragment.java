package com.example.guest.movieapp.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.guest.movieapp.R;
import com.example.guest.movieapp.models.Cast;
import com.example.guest.movieapp.models.Movie;
import com.example.guest.movieapp.services.TMDB_Services;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ResultsDetailFragment extends Fragment {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 150;

    @Bind(R.id.movieImageView) ImageView mPoster;
    @Bind(R.id.movieTitleTextView) TextView mTitle;
    @Bind(R.id.ratingTextView) TextView mRating;
    @Bind(R.id.genreTextView) TextView mGenre;
    @Bind(R.id.overViewView) TextView mOverview;
    @Bind(R.id.castView) ListView mCost;
    @Bind(R.id.saveMovieButton) Button mButton;

    private ArrayList<Cast> mCast;

    public static final String TAG = ResultsDetailFragment.class.getSimpleName();

    private Movie mMovie;

    public ResultsDetailFragment() {
        // Required empty public constructor
    }


    public static ResultsDetailFragment newInstance(Movie movie) {
        ResultsDetailFragment ResultsDetailFragment = new ResultsDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("movie", Parcels.wrap(movie));
        ResultsDetailFragment.setArguments(args);
        return ResultsDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovie = Parcels.unwrap(getArguments().getParcelable("movie"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mMovie.getPoster_path()).resize(MAX_WIDTH, MAX_HEIGHT).centerCrop().into(mPoster);

        mTitle.setText(mMovie.getTitle());
        mGenre.setText(mMovie.getGenreID().toString());
        mRating.setText(mMovie.getVoteAverage() + "/10");
        mOverview.setText(mMovie.getOverview());


        final TMDB_Services MovieFINDER = new TMDB_Services();

        Log.v(TAG, "ID:      " + mMovie.getMovieID());

        MovieFINDER.findCast(mMovie.getMovieID(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                mCast = MovieFINDER.castResults(response.body().string());

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String[] castName = new String[mCast.size()];
                        for (int i = 0; i < castName.length; i ++) {
                            castName[i] = mCast.get(i).getPerson();
                        }
                        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, castName);
                        mCost.setAdapter(adapter);
                    }
                });



            }
        });


        return view;
    }


}
