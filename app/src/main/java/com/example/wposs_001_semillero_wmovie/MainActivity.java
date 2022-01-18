package com.example.wposs_001_semillero_wmovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;

import com.example.wposs_001_semillero_wmovie.interfaces.WMovieInterface;
import com.example.wposs_001_semillero_wmovie.models.Credentials;
import com.example.wposs_001_semillero_wmovie.models.ResWMovie;
import com.example.wposs_001_semillero_wmovie.models.Service;
import com.example.wposs_001_semillero_wmovie.models.WMovie;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListMovieAdapter listMovieAdapter;
    private boolean load;
    private int nextPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        recyclerView = (RecyclerView) findViewById(R.id.listReciclerView);
        listMovieAdapter = new ListMovieAdapter(this);
        recyclerView.setAdapter(listMovieAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {

                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (load) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            Log.i("Tagt", "llegamos al final");

                            load = false;
                            nextPage += 1;
                            getRetrofitResPopular(nextPage);
                        }
                    }
                }
            }
        });
        nextPage = 1;
        getRetrofitResPopular(nextPage);
    }

    private void getRetrofitResPopular(int nextPage) {
        WMovieInterface wMovieInterface = Service.getwMovie();
        Call<ResWMovie> resWMovieCall = wMovieInterface.getPopularMovies(
                Credentials.key_api, nextPage
        );

        resWMovieCall.enqueue(new Callback<ResWMovie>() {
            @Override
            public void onResponse(Call<ResWMovie> call, Response<ResWMovie> response) {
                load = true;
                if (response.code() == 200) {
                    ArrayList<WMovie> movies = new ArrayList<>(response.body().getMovie());
                    listMovieAdapter.adicionarListaPokemon(movies);
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
                load = true;
                t.printStackTrace();
            }
        });
    }

    //Se cometan metodos usados para consulta por nombre y id dados por la API
    /*
    private void getRetrofitRes() {
        WMovieInterface wMovieInterface = Service.getwMovie();
        Call<SearchResWMovie> searchResWMovieCall = wMovieInterface.searchMovie(
                Credentials.key_api, "action", "1"
        );
        searchResWMovieCall.enqueue(new Callback<SearchResWMovie>() {
            @Override
            public void onResponse(Call<SearchResWMovie> call, Response<SearchResWMovie> response) {
                if (response.code() == 200) {
                    Log.v("Tag", "el response" + response.body().toString());
                    List<WMovie> movies = new ArrayList<>(response.body().getMovies());

                    for (WMovie movie : movies) {
                        Log.v("Tag", "La fecha " + movie.getRelease_date());
                        Log.v("Tag", "El titulo" + movie.getTitle());
                    }
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
                t.printStackTrace();
            }
        });
    }

    private void getRetrofitResID() {
        WMovieInterface wMovieInterface = Service.getwMovie();
        Call<WMovie> responseCall = wMovieInterface.getMovie(9593, Credentials.key_api);

        responseCall.enqueue(new Callback<WMovie>() {
            @Override
            public void onResponse(Call<WMovie> call, Response<WMovie> response) {

                if (response.code() == 200) {
                    WMovie wMovie = response.body();
                    Log.v("Tag", "The Response " + wMovie.getTitle());
                } else {
                    try {
                        Log.v("Tag", "Error" + response.errorBody().string());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<WMovie> call, Throwable t) {
            }
        });
    }
     */
}