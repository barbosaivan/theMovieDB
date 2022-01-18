package com.example.wposs_001_semillero_wmovie.models;

import android.os.Parcel;
import android.os.Parcelable;

public class WMovie implements Parcelable {
    private String title;
    private String poster_path;
    private String release_date;
    private int id;
    private float vote_average;
    private  String movie_overview;

    public WMovie(String title, String psoter_path, String release_date, int movie_id, float vote_average, String movie_overview) {
        this.title = title;
        this.poster_path = psoter_path;
        this.release_date = release_date;
        this.id = movie_id;
        this.vote_average = vote_average;
        this.movie_overview = movie_overview;
    }

    protected WMovie(Parcel in) {
        title = in.readString();
        poster_path = in.readString();
        release_date = in.readString();
        id = in.readInt();
        vote_average = in.readFloat();
        movie_overview = in.readString();
    }

    public static final Creator<WMovie> CREATOR = new Creator<WMovie>() {
        @Override
        public WMovie createFromParcel(Parcel in) {
            return new WMovie(in);
        }

        @Override
        public WMovie[] newArray(int size) {
            return new WMovie[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
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

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public String getMovie_overview() {
        return movie_overview;
    }

    public void setMovie_overview(String movie_overview) {
        this.movie_overview = movie_overview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(poster_path);
        dest.writeString(release_date);
        dest.writeInt(id);
        dest.writeFloat(vote_average);
        dest.writeString(movie_overview);
    }
}
