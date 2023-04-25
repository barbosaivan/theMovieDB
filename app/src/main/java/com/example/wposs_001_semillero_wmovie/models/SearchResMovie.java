package com.example.wposs_001_semillero_wmovie.models;

import androidx.annotation.NonNull;

import com.example.wposs_001_semillero_wmovie.entities.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResMovie {

    @SerializedName("total_results")
    @Expose()
    private int totalCount;

    @SerializedName("results")
    @Expose()
    private List<Movie> movies;


    public List<Movie> getMovies() {
        return movies;
    }

    @NonNull
    @Override
    public String toString() {
        return "SearchResMovie{" +
                "total_count=" + totalCount +
                ", movies=" + movies +
                '}';
    }
}
