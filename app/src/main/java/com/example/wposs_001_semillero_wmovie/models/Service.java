package com.example.wposs_001_semillero_wmovie.models;

import com.example.wposs_001_semillero_wmovie.interfaces.WMovieInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(Credentials.url_base)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static WMovieInterface wMovieInterface = retrofit.create(WMovieInterface.class);

    public static WMovieInterface getwMovie() {
        return wMovieInterface;
    }
}
