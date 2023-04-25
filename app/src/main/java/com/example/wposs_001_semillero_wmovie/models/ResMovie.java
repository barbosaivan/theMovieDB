package com.example.wposs_001_semillero_wmovie.models;

import androidx.annotation.NonNull;

import com.example.wposs_001_semillero_wmovie.entities.GenresMovies;
import com.example.wposs_001_semillero_wmovie.entities.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResMovie {
    @SerializedName("results")
    @Expose
    private ArrayList<Movie> movie;

    @Expose
    private ArrayList<GenresMovies> genres;

    public List<Movie> getMovie() {
        return movie;
    }

    public List<GenresMovies> getGenres() {
        return genres;
    }

    @NonNull
    @Override
    public String toString() {
        return "ResMovie{" +
                "movie=" + movie +
                ", genres=" + genres +
                '}';
    }
}
