package com.example.wposs_001_semillero_wmovie.models;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.wposs_001_semillero_wmovie.entities.GenresMovies;
import com.example.wposs_001_semillero_wmovie.entities.Movie;
import com.example.wposs_001_semillero_wmovie.interfaces.InterfaceSearchMovieActivity;
import com.example.wposs_001_semillero_wmovie.interfaces.WMovieInterface;
import com.example.wposs_001_semillero_wmovie.utils.CredentialsApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelSearchMovie implements InterfaceSearchMovieActivity.ModelSearchMovieActivity {
    InterfaceSearchMovieActivity.PresenterSearchMovieActivity presenterSearchMovieActivity;

    public ModelSearchMovie(InterfaceSearchMovieActivity.PresenterSearchMovieActivity presenterSearchMovieActivity) {
        this.presenterSearchMovieActivity = presenterSearchMovieActivity;
    }

    @Override
    public void retrofitResNameMovies(int nextPage, String nameMovie) {
        WMovieInterface wMovieInterface = Service.getWMovie();
        Call<SearchResMovie> searchResWMovieCall = wMovieInterface.searchMovie(
                CredentialsApi.KEY_API, nameMovie, nextPage, "es-MX"
        );
        searchResWMovieCall.enqueue(new Callback<SearchResMovie>() {
            @Override
            public void onResponse(@NonNull Call<SearchResMovie> call, @NonNull Response<SearchResMovie> response) {
                presenterSearchMovieActivity.reloadLoadPage(true);
                if (response.code() == 200) {
                    ArrayList<Movie> movies = new ArrayList<>(Objects.requireNonNull(response.body()).getMovies());
                    retrofitResGenres(movies);
                } else {
                    try {
                        Log.v("Tag", "Error" + Objects.requireNonNull(response.errorBody()).string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<SearchResMovie> call, @NonNull Throwable t) {
                presenterSearchMovieActivity.reloadLoadPage(false);
                t.printStackTrace();
            }
        });
    }

    public void retrofitResGenres(List<Movie> movies) {
        WMovieInterface wMovieInterface = Service.getWMovie();
        Call<ResMovie> resWMovieCall = wMovieInterface.geGenres(
                CredentialsApi.KEY_API, "es-MX"
        );

        resWMovieCall.enqueue(new Callback<ResMovie>() {
            @Override
            public void onResponse(@NonNull Call<ResMovie> call, @NonNull Response<ResMovie> response) {
                presenterSearchMovieActivity.reloadLoadPage(true);
                if (response.code() == 200) {
                    ArrayList<GenresMovies> genresMovies = new ArrayList<>(Objects.requireNonNull(response.body()).getGenres());
                    presenterSearchMovieActivity.sendRetrofitResNameMovie(orderGenres(movies, genresMovies));
                } else {
                    try {
                        Log.v("Tag", "Error" + Objects.requireNonNull(response.errorBody()).string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResMovie> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private List<Movie> orderGenres(List<Movie> movies, ArrayList<GenresMovies> genresMovies) {
        for (Movie movie : movies) {
            for (int i = 0; i < movie.getGenre_ids().length; i++) {
                for (GenresMovies genres : genresMovies) {
                    if (movie.getGenre_ids()[i].equals(genres.getId())) {
                        movie.getGenre_ids()[i] = genres.getName();
                    }
                }
            }
        }
        return movies;
    }
}
