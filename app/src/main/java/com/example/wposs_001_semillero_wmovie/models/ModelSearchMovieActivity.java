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
        Call<SearchResWMovie> searchResWMovieCall = wMovieInterface.searchMovie(
                Credentials.key_api, nameMovie, nextpage
        );
        searchResWMovieCall.enqueue(new Callback<SearchResWMovie>() {
            @Override
            public void onResponse(Call<SearchResWMovie> call, Response<SearchResWMovie> response) {
                presenterSearchMovieActivity.reloadLoadPage(true);
                if (response.code() == 200) {
                    ArrayList<WMovie> movies = new ArrayList<>(response.body().getMovies());
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
            public void onFailure(Call<SearchResWMovie> call, Throwable t) {
                presenterSearchMovieActivity.reloadLoadPage(false);
                t.printStackTrace();
            }
        });
    }

    public void retrofitResGenres(ArrayList<WMovie> movies) {
        WMovieInterface wMovieInterface = Service.getwMovie();
        Call<ResWMovie> resWMovieCall = wMovieInterface.geGenres(
                Credentials.key_api, "es-MX"
        );

        resWMovieCall.enqueue(new Callback<ResWMovie>() {
            @Override
            public void onResponse(Call<ResWMovie> call, Response<ResWMovie> response) {
                presenterSearchMovieActivity.reloadLoadPage(true);
                if (response.code() == 200) {
                    ArrayList<GenresMovies> genresMovies = new ArrayList<>(response.body().getGenres());
                    for (WMovie movie : movies) {
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
            public void onFailure(Call<ResWMovie> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
