package com.example.wposs_001_semillero_wmovie.models;

import java.io.Serializable;

public class Movie implements Serializable {
    private String title;
    private String poster_path;
    private String backdrop_path;
    private String release_date;
    private int id;
    private float vote_average;
    private String overview;
    private String[] genre_ids;

    public Movie(String title, String psoter_path, String backdrop_path, String release_date, int movie_id, float vote_average, String movie_overview, String[] genre_ids) {
        this.title = title;
        this.poster_path = psoter_path;
        this.release_date = release_date;
        this.id = movie_id;
        this.vote_average = vote_average;
        this.overview = movie_overview;
        this.backdrop_path = backdrop_path;
        this.genre_ids = genre_ids;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String[] getGenre_ids() {
        return genre_ids;
    }

}
