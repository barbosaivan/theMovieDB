package com.example.wposs_001_semillero_wmovie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.wposs_001_semillero_wmovie.interfaces.WMovieInterface;
import com.example.wposs_001_semillero_wmovie.models.Credentials;
import com.example.wposs_001_semillero_wmovie.models.SearchResWMovie;
import com.example.wposs_001_semillero_wmovie.models.Service;
import com.example.wposs_001_semillero_wmovie.models.WMovie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRetrofitRes();
            }
        });
    }

    private void getRetrofitRes() {
        WMovieInterface wMovieInterface = Service.getwMovie();
        Call<SearchResWMovie> searchResWMovieCall = wMovieInterface.searchMovie(
                Credentials.key_api, "Jack Reacher", "1"
        );

        searchResWMovieCall.enqueue(new Callback<SearchResWMovie>() {
            @Override
            public void onResponse(Call<SearchResWMovie> call, Response<SearchResWMovie> response) {
                if(response.code() ==200){
                    Log.v("Tag", "el respon"+response.body().toString());
                    List<WMovie> movies = new ArrayList<>(response.body().getMovies());

                    for(WMovie movie: movies){
                        Log.v("Tag", "El dato"+movie.getRelease_date());
                    }
                }else{
                    try {
                        Log.v("Tag","Error"+response.errorBody().string());
                    }catch (IOException e){
                            e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchResWMovie> call, Throwable t) {

            }
        });
    }
}