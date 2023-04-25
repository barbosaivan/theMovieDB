package com.example.wposs_001_semillero_wmovie.view;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wposs_001_semillero_wmovie.R;
import com.example.wposs_001_semillero_wmovie.interfaces.InterfaceMainActivity;
import com.example.wposs_001_semillero_wmovie.entities.Movie;
import com.example.wposs_001_semillero_wmovie.presenter.PresenterMainActivity;
import com.example.wposs_001_semillero_wmovie.view.adapters.ListMovieAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewMainActivity extends AppCompatActivity implements InterfaceMainActivity.ViewActivity {
    private RecyclerView recyclerView;
    private ListMovieAdapter listMovieAdapter;
    private ProgressBar progressBar;
    private Button buttonRecharge;
    private boolean load;
    private int loadPage;
    List<Movie> movies;
    InterfaceMainActivity.PresenterActivity presenterActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenterActivity = new PresenterMainActivity(this);

        recyclerView = findViewById(R.id.listReciclerView);
        progressBar = findViewById(R.id.progressBar);
        buttonRecharge = findViewById(R.id.buttonRecargar);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        movies = new ArrayList<>();
        loadPage = 1;
        internetConnection();
    }

    public void init() {
        listMovieAdapter = new ListMovieAdapter(this, this::moveToDescription);
        recyclerView.setAdapter(listMovieAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {

                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (load && ((visibleItemCount + pastVisibleItems) >= totalItemCount)) {
                        progressBar.setVisibility(View.VISIBLE);
                        internetConnectionLoadingData();
                        load = false;
                        loadPage += 1;
                        presenterActivity.bringRetrofitResPopular(loadPage);

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
        listMovieAdapter.addListMovies(movies);
    }

    @Override
    public void valorLoad(boolean load) {
        this.load = load;
    }

    public void internetConnection() {
        if (isNetworkAvailable(this.getApplication())) {
            buttonRecharge.setVisibility(View.GONE);
            presenterActivity.bringRetrofitResPopular(loadPage);
            init();
        } else {
            buttonRecharge.setVisibility(View.GONE);
            Toast.makeText(this, getString(R.string.error_fail_conexion), Toast.LENGTH_LONG).show();
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                buttonRecharge.setVisibility(View.VISIBLE);
                buttonRecharge.setOnClickListener(v -> internetConnection());
            }, 4000);
        }
    }

    public void internetConnectionLoadingData() {
        if (isNetworkAvailable(this.getApplication()))
            Toast.makeText(this, getString(R.string.error_fail_conexion_refresh), Toast.LENGTH_LONG).show();
    }

    private boolean isNetworkAvailable(Application application) {
        ConnectivityManager connectivityManager = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network nw = connectivityManager.getActiveNetwork();
        if (nw == null) return false;
        NetworkCapabilities actNw = connectivityManager.getNetworkCapabilities(nw);
        return actNw != null && (actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.search) {
            Intent intent = new Intent(this, ViewSearchMovie.class);
            startActivity(intent);
        }
        if (itemId == R.id.logout) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}