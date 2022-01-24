package com.example.wposs_001_semillero_wmovie.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResMovie {
    @SerializedName("results")
    @Expose
    private ArrayList<Movie> movie;

    @Expose
    private ArrayList<GenresMovies> genres;

    public ArrayList<Movie> getMovie() {
        return movie;
    }

    public ArrayList<GenresMovies> getGenres() {
        return genres;
    }

    @Override
    public String toString() {
        return "ResMovie{" +
                "movie=" + movie +
                ", genres=" + genres +
                '}';
    }
}
