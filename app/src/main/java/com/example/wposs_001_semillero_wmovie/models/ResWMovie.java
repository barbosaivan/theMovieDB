package com.example.wposs_001_semillero_wmovie.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResWMovie {
    @SerializedName("results")
    @Expose
    private ArrayList<WMovie> movie;

    @Expose
    private ArrayList<GenresMovies> genres;

    public ArrayList<WMovie> getMovie() {
        return movie;
    }

    public ArrayList<GenresMovies> getGenres() {
        return genres;
    }

    @Override
    public String toString() {
        return "ResWMovie{" +
                "movie=" + movie +
                ", genres=" + genres +
                '}';
    }
}
