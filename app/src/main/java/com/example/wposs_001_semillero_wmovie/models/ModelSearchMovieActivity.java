package com.example.wposs_001_semillero_wmovie.models;

import android.util.Log;

import com.example.wposs_001_semillero_wmovie.interfaces.InterfaceSearchMovieActivity;
import com.example.wposs_001_semillero_wmovie.interfaces.WMovieInterface;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelSearchMovieActivity implements InterfaceSearchMovieActivity.modelSearchMovieActivity {
    InterfaceSearchMovieActivity.presenterSearchMovieActivity presenterSearchMovieActivity;

    public ModelSearchMovieActivity(InterfaceSearchMovieActivity.presenterSearchMovieActivity presenterSearchMovieActivity) {
        this.presenterSearchMovieActivity = presenterSearchMovieActivity;
    }

    @Override
    public void retrofitResNameMovies(int nextpage, String nameMovie) {
        WMovieInterface wMovieInterface = Service.getwMovie();
        Call<SearchResMovie> searchResWMovieCall = wMovieInterface.searchMovie(
                Credentials.key_api, nameMovie, nextpage, "es-MX"
        );
        searchResWMovieCall.enqueue(new Callback<SearchResMovie>() {
            @Override
            public void onResponse(Call<SearchResMovie> call, Response<SearchResMovie> response) {
                presenterSearchMovieActivity.reloadLoadPage(true);
                if (response.code() == 200) {
                    ArrayList<Movie> movies = new ArrayList<>(response.body().getMovies());
                    retrofitResGenres(movies);
                } else {
                    try {
                        Log.v("Tag", "Error" + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchResMovie> call, Throwable t) {
                presenterSearchMovieActivity.reloadLoadPage(false);
                t.printStackTrace();
            }
        });
    }

    public void retrofitResGenres(ArrayList<Movie> movies) {
        WMovieInterface wMovieInterface = Service.getwMovie();
        Call<ResMovie> resWMovieCall = wMovieInterface.geGenres(
                Credentials.key_api, "es-MX"
        );

        resWMovieCall.enqueue(new Callback<ResMovie>() {
            @Override
            public void onResponse(Call<ResMovie> call, Response<ResMovie> response) {
                presenterSearchMovieActivity.reloadLoadPage(true);
                if (response.code() == 200) {
                    ArrayList<GenresMovies> genresMovies = new ArrayList<>(response.body().getGenres());
                    for (Movie movie : movies) {
                        for (int i = 0; i < movie.getGenre_ids().length; i++) {
                            for (GenresMovies generos : genresMovies) {
                                if (movie.getGenre_ids()[i].equals(generos.getId())) {
                                    movie.getGenre_ids()[i] = generos.getName();
                                }
                            }
                        }
                    }
                    presenterSearchMovieActivity.sendRetrofitResNameMovie(movies);
                } else {
                    try {
                        Log.v("Tag", "Error" + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResMovie> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
