package com.example.guest.movieapp;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by Guest on 12/1/16.
 */
@Parcel
public class Movie {

    private String mPoster_path;
    private String mOverview;
    private String mReleaseDate;
    private ArrayList<String> mGenreID;
    private String mTitle;
    private String mVoteAverage;


    public Movie() {}

    public Movie(String mPoster_path, String mOverview, String mReleaseDate, ArrayList<String> mGenreID, String mTitle, String mVoteAverage) {
        this.mPoster_path = mPoster_path;
        this.mOverview = mOverview;
        this.mReleaseDate = mReleaseDate;
        this.mGenreID = mGenreID;
        this.mTitle = mTitle;
        this.mVoteAverage = mVoteAverage;
    }


    public String getPoster_path() {
        return mPoster_path;
    }

    public String getOverview() {
        return mOverview;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public ArrayList<String> getGenreID() {
        return mGenreID;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getVoteAverage() {
        return mVoteAverage;
    }
}
