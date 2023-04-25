package com.example.wposs_001_semillero_wmovie.view;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wposs_001_semillero_wmovie.R;
import com.example.wposs_001_semillero_wmovie.interfaces.InterfaceSearchMovieActivity;
import com.example.wposs_001_semillero_wmovie.entities.Movie;
import com.example.wposs_001_semillero_wmovie.presenter.PresenterSearchMovie;
import com.example.wposs_001_semillero_wmovie.view.adapters.ListSearchMovieAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ViewSearchMovie extends AppCompatActivity implements InterfaceSearchMovieActivity.ViewSearchMovieActivity {
    private RecyclerView recyclerView;
    private ListSearchMovieAdapter listNameMovieAdapter;
    private ProgressBar progressBar;
    private boolean load;
    private int loadPage;
    List<Movie> movies;
    InterfaceSearchMovieActivity.PresenterSearchMovieActivity presenterSearchMovieActivity;
    private EditText nameMovie;
    Button buttonBackSearchMovie;
    Button buttonSearchMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);
        Objects.requireNonNull(getSupportActionBar()).hide();

        presenterSearchMovieActivity = new PresenterSearchMovie(this);

        nameMovie = findViewById(R.id.editTexNameMovie);
        buttonBackSearchMovie = findViewById(R.id.buttonBackSearchMovie);
        buttonSearchMovie = findViewById(R.id.buttonSearchMovie);
        recyclerView = findViewById(R.id.listRecyclerViewSearchNameMovie);
        progressBar = findViewById(R.id.progressBar);

        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);

        movies = new ArrayList<>();

        buttonBackSearchMovie.setOnClickListener(v -> finish());
        buttonSearchMovie.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            loadPage = 1;
            internetConnection();
        });
    }

    public void init() {
        listNameMovieAdapter = new ListSearchMovieAdapter(this, this::moveToDescription);
        recyclerView.setAdapter(listNameMovieAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(Objects.requireNonNull(recyclerView), dx, dy);

                if (dy > 0) {

                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (load && ((visibleItemCount + pastVisibleItems) >= totalItemCount)) {
                            progressBar.setVisibility(View.VISIBLE);
                            internetConnectionLoadingData();
                            load = false;
                            loadPage += 1;
                            presenterSearchMovieActivity.bringRetrofitResNameMovie(loadPage, nameMovie.getText().toString());

                    }
                }
            }
        });
    }

    private void moveToDescription(Movie item) {
        Intent intent = new Intent(this, DescriptionMovie.class);
        intent.putExtra("listMovie", item);
        startActivity(intent);
    }

    @Override
    public void valorList(List<Movie> movies) {
        this.movies = movies;

        if (!movies.isEmpty()) {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        listNameMovieAdapter.addListMovies(movies);
    }

    @Override
    public void valorLoad(boolean load) {
        this.load = load;
        progressBar.setVisibility(View.GONE);
    }

    public void internetConnection() {
        if (isNetworkAvailable(this.getApplication())) {
            presenterSearchMovieActivity.bringRetrofitResNameMovie(loadPage, nameMovie.getText().toString());
            init();
        } else {
            Toast.makeText(this, getString(R.string.error_fail_conexion), Toast.LENGTH_LONG).show();
        }
    }

    public void internetConnectionLoadingData() {
        if (!isNetworkAvailable(this.getApplication()))
            Toast.makeText(this, getString(R.string.error_fail_conexion_refresh), Toast.LENGTH_LONG).show();
    }

    private boolean isNetworkAvailable(Application application) {
        ConnectivityManager connectivityManager = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network nw = connectivityManager.getActiveNetwork();
        if (nw == null) return false;
        NetworkCapabilities actNw = connectivityManager.getNetworkCapabilities(nw);
        return actNw != null && (actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH));
    }

}