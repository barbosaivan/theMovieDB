package com.example.wposs_001_semillero_wmovie.models;

import com.example.wposs_001_semillero_wmovie.interfaces.WMovieInterface;
import com.example.wposs_001_semillero_wmovie.utils.CredentialsApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {

    private Service() {
        throw new IllegalStateException("Utility class");
    }
    private static final Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(CredentialsApi.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create());

    private static final Retrofit retrofit = retrofitBuilder.build();

    private static final WMovieInterface wMovieInterface = retrofit.create(WMovieInterface.class);

    public static WMovieInterface getWMovie() {
        return wMovieInterface;
    }
}
