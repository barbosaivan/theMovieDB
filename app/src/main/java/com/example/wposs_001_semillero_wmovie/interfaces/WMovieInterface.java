package com.example.wposs_001_semillero_wmovie.interfaces;

import com.example.wposs_001_semillero_wmovie.models.ResMovie;
import com.example.wposs_001_semillero_wmovie.models.SearchResMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WMovieInterface {
    @GET("/3/search/movie")
    Call<SearchResMovie> searchMovie(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") int page,
            @Query("language") String language
    );

    @GET("/3/movie/popular?")
    Call<ResMovie> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("page") int page,
            @Query("language") String language
    );

    @GET("/3/genre/movie/list?")
    Call<ResMovie> geGenres(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

}
