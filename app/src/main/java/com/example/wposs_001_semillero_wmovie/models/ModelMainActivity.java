package com.example.wposs_001_semillero_wmovie.models;

import android.util.Log;

import com.example.wposs_001_semillero_wmovie.interfaces.InterfaceMainActivity;
import com.example.wposs_001_semillero_wmovie.interfaces.WMovieInterface;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelMainActivity implements InterfaceMainActivity.modelActivity {
    InterfaceMainActivity.presenterActivity presenterActivity;

    public ModelMainActivity(InterfaceMainActivity.presenterActivity presenterActivity) {
        this.presenterActivity = presenterActivity;
    }


    @Override
    public void RetrofitResPopular(int nextPage) {
        WMovieInterface wMovieInterface = Service.getwMovie();
        Call<ResWMovie> resWMovieCall = wMovieInterface.getPopularMovies(
                Credentials.key_api, nextPage, "es-MX"
        );

        resWMovieCall.enqueue(new Callback<ResWMovie>() {
            @Override
            public void onResponse(Call<ResWMovie> call, Response<ResWMovie> response) {
                presenterActivity.reloadLoadPage(true);
                if (response.code() == 200) {
                    ArrayList<WMovie> movies = new ArrayList<>(response.body().getMovie());
                    presenterActivity.sendRetrofitResPopular(movies);
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
                presenterActivity.reloadLoadPage(true);
                t.printStackTrace();
            }
        });
    }

}
