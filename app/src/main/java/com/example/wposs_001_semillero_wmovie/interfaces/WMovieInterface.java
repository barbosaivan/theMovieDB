package com.example.wposs_001_semillero_wmovie.interfaces;

import retrofit2.Call;

import com.example.wposs_001_semillero_wmovie.models.ResWMovie;
import com.example.wposs_001_semillero_wmovie.models.SearchResWMovie;
import com.example.wposs_001_semillero_wmovie.models.WMovie;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WMovieInterface {
    @GET("/3/search/movie")
    Call<SearchResWMovie> searchMovie(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") String page
    );

    @GET("3/movie/{movie_id}?")
    Call<WMovie> getMovie(
        @Path("movie_id") int movie_id,
        @Query("api_key") String api_key
    );

    @GET("/3/movie/popular?")
    Call<ResWMovie> getPopularMovies(
            @Query("api_key") String api_key,
            @Query("page") int page
    );

    @GET("/3/movie/popular?")
    Call<ResWMovie> getTotalMovies(
            @Query("api_key") String api_key,
            @Query("page") int page
    );

}
