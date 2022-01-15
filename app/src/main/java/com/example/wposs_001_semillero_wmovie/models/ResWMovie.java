package com.example.wposs_001_semillero_wmovie.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResWMovie {
    @SerializedName("results")
    @Expose
    private WMovie movie;

    public WMovie getMovie(){
        return movie;
    }

    @Override
    public String toString() {
        return "ResWMovie{" +
                "movie=" + movie +
                '}';
    }
}
