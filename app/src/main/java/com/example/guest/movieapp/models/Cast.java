package com.example.guest.movieapp.models;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by Guest on 12/1/16.
 */
@Parcel
public class Cast {

    private String mPerson;



    private String mCastId;


    public Cast() {}

    public Cast(String mPerson, String mCastId) {
        this.mPerson = mPerson;
        this.mCastId = mCastId;
    }

    public String getCastId() {
        return mCastId;
    }

    public String getPerson() {
        return mPerson;
    }
}
