package com.example.wposs_001_semillero_wmovie.view;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wposs_001_semillero_wmovie.R;
import com.example.wposs_001_semillero_wmovie.interfaces.InterfaceSearchMovieActivity;
import com.example.wposs_001_semillero_wmovie.models.WMovie;
import com.example.wposs_001_semillero_wmovie.presenter.PresenterSearchMovieActivity;
import com.example.wposs_001_semillero_wmovie.view.adapters.ListSearchMovieAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class ViewSearchMovieActivity extends AppCompatActivity implements InterfaceSearchMovieActivity.ViewSearchMovieActivity {
    private RecyclerView recyclerView;
    private ListSearchMovieAdapter listNameMovieAdapter;
    private ProgressBar progressBar;
    private boolean load;
    private int loadPage;
    ArrayList<WMovie> movies;
    InterfaceSearchMovieActivity.presenterSearchMovieActivity presenterSearchMovieActivity;
    private EditText nameMovie;
    Button buttonBackSearchMovie, buttonSearchMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);
        Objects.requireNonNull(getSupportActionBar()).hide();

        presenterSearchMovieActivity = new PresenterSearchMovieActivity(this);

        nameMovie = (EditText) findViewById(R.id.editTexNameMovie);
        buttonBackSearchMovie = (Button) findViewById(R.id.buttonBackSearchMovie);
        buttonSearchMovie = (Button) findViewById(R.id.buttonSearchMovie);
        recyclerView = (RecyclerView) findViewById(R.id.listRecyclerViewSearchNameMovie);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);

        movies = new ArrayList<>();

        buttonBackSearchMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        buttonSearchMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                loadPage = 1;
                internetConnection();
            }
        });
    }

    public void init() {
        listNameMovieAdapter = new ListSearchMovieAdapter(this, new ListSearchMovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(WMovie item) {
                moveTodescription(item);
            }
        });
        recyclerView.setAdapter(listNameMovieAdapter);
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
                            progressBar.setVisibility(View.VISIBLE);
                            internetConnectionLoadingData();
                            load = false;
                            loadPage += 1;
                            presenterSearchMovieActivity.bringRetrofitResNameMovie(loadPage, nameMovie.getText().toString());
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

        if (movies.size() > 0) {
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
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            presenterSearchMovieActivity.bringRetrofitResNameMovie(loadPage, nameMovie.getText().toString());
            init();
        } else {
            Toast.makeText(this, "Sin Conexion a Internet", Toast.LENGTH_LONG).show();
        }
    }

    public void internetConnectionLoadingData() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
        } else {
            Toast.makeText(this, "Sin Conexion a Internet\nRefresque de nuevo", Toast.LENGTH_LONG).show();
        }
    }

}