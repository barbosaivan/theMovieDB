package com.example.wposs_001_semillero_wmovie.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResWMovie {

    @SerializedName("total_results")
    @Expose()
    private int total_count;

    @SerializedName("results")
    @Expose()
    private List<WMovie> movies;

    public int getTotal_count(){
        return total_count;
    }

    public List<WMovie> getMovies(){
        return  movies;
    }

    @Override
    public String toString() {
        return "SearchResWMovie{" +
                "total_count=" + total_count +
                ", movies=" + movies +
                '}';
    }
}
