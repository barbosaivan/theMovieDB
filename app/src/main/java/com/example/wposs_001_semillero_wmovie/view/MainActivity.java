package com.example.wposs_001_semillero_wmovie.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.wposs_001_semillero_wmovie.R;
import com.example.wposs_001_semillero_wmovie.interfaces.InterfaceMainActivity;
import com.example.wposs_001_semillero_wmovie.models.WMovie;
import com.example.wposs_001_semillero_wmovie.presenter.PresenterMainActivity;
import com.example.wposs_001_semillero_wmovie.view.adapters.ListMovieAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements InterfaceMainActivity.ViewActivity {
    private RecyclerView recyclerView;
    private ListMovieAdapter listMovieAdapter;
    private boolean load;
    private int loadPage;
    ArrayList<WMovie> movies;
    InterfaceMainActivity.presenterActivity presenterActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenterActivity = new PresenterMainActivity(this);

        movies = new ArrayList<>();
        loadPage = 1;
        presenterActivity.bringRetrofitResPopular(loadPage);
        init();
    }


    public void init() {
        recyclerView = (RecyclerView) findViewById(R.id.listReciclerView);
        listMovieAdapter = new ListMovieAdapter(this, new ListMovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(WMovie item) {
                moveTodescription(item);
            }
        });
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

                            load = false;
                            loadPage += 1;
                            presenterActivity.bringRetrofitResPopular(loadPage);
                        }
                    }
                }
            }
        });
    }

    private void moveTodescription(WMovie item) {
        Intent intent = new Intent(this, DescriptionMovie.class);
        intent.putExtra("listMovie", item);
        startActivity(intent);
    }

    @Override
    public void valorList(ArrayList<WMovie> movies) {
        this.movies = movies;
        listMovieAdapter.adicionarListaPokemon(movies);
    }

    @Override
    public void valorLoad(boolean load) {
        this.load = load;
    }
    
    //Se cometan metodos usados para consulta por nombre por la API
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
     */
}