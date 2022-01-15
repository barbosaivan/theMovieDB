package com.example.wposs_001_semillero_wmovie.interfaces;

import retrofit2.Call;

import com.example.wposs_001_semillero_wmovie.models.SearchResWMovie;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WMovieInterface {
    @GET("/3/search/movie")
    Call<SearchResWMovie> searchMovie(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") String page
    );
}
