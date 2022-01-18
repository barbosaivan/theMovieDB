package com.example.wposs_001_semillero_wmovie.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResWMovie {
    @SerializedName("results")
    @Expose
    private ArrayList<WMovie> movie;

    public ArrayList<WMovie> getMovie(){
        return movie;
    }

    @Override
    public String toString() {
        return "ResWMovie{" +
                "movie=" + movie +
                '}';
    }
}
