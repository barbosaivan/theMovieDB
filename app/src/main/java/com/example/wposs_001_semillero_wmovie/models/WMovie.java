package com.example.wposs_001_semillero_wmovie.models;

import android.os.Parcel;
import android.os.Parcelable;

public class WMovie implements Parcelable {
    private String title;
    private String psoter_path;
    private String release_date;
    private int movie_id;
    private float vote_average;
    private  String movie_overview;

    public WMovie(String title, String psoter_path, String release_date, int movie_id, float vote_average, String movie_overview) {
        this.title = title;
        this.psoter_path = psoter_path;
        this.release_date = release_date;
        this.movie_id = movie_id;
        this.vote_average = vote_average;
        this.movie_overview = movie_overview;
    }

    protected WMovie(Parcel in) {
        title = in.readString();
        psoter_path = in.readString();
        release_date = in.readString();
        movie_id = in.readInt();
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

    public String getPsoter_path() {
        return psoter_path;
    }

    public void setPsoter_path(String psoter_path) {
        this.psoter_path = psoter_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
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
        dest.writeString(psoter_path);
        dest.writeString(release_date);
        dest.writeInt(movie_id);
        dest.writeFloat(vote_average);
        dest.writeString(movie_overview);
    }
}
