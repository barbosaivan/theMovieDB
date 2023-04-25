package com.example.wposs_001_semillero_wmovie.entities;

import java.io.Serializable;

public class Movie implements Serializable {
    private final String title;
    private final String poster_path;
    private final String backdrop_path;
    private final String release_date;
    private int id;
    private final float vote_average;
    private final String overview;
    private final String[] genre_ids;

    public Movie(String title, String posterPath, String backdropPath, String releaseDate, String movieOverview, String[] genreIds, int movieId, float voteAverage) {
        this.title = title;
        this.poster_path = posterPath;
        this.release_date = releaseDate;
        this.id = movieId;
        this.vote_average = voteAverage;
        this.overview = movieOverview;
        this.backdrop_path = backdropPath;
        this.genre_ids = genreIds;
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
