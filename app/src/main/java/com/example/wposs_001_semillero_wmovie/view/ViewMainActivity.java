package com.example.wposs_001_semillero_wmovie.view;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wposs_001_semillero_wmovie.R;
import com.example.wposs_001_semillero_wmovie.interfaces.InterfaceMainActivity;
import com.example.wposs_001_semillero_wmovie.models.Movie;
import com.example.wposs_001_semillero_wmovie.presenter.PresenterMainActivity;
import com.example.wposs_001_semillero_wmovie.view.adapters.ListMovieAdapter;

import java.util.ArrayList;

public class ViewMainActivity extends AppCompatActivity implements InterfaceMainActivity.ViewActivity {
    private RecyclerView recyclerView;
    private ListMovieAdapter listMovieAdapter;
    private ProgressBar progressBar;
    private Button buttonRecargar;
    private boolean load;
    private int loadPage;
    ArrayList<Movie> movies;
    InterfaceMainActivity.presenterActivity presenterActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenterActivity = new PresenterMainActivity(this);

        recyclerView = (RecyclerView) findViewById(R.id.listReciclerView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        buttonRecargar = (Button) findViewById(R.id.buttonRecargar);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        movies = new ArrayList<>();
        loadPage = 1;
        internetConnection();
    }

    public void init() {
        listMovieAdapter = new ListMovieAdapter(this, new ListMovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie item) {
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
                            progressBar.setVisibility(View.VISIBLE);
                            internetConnectionLoadingData();
                            load = false;
                            loadPage += 1;
                            presenterActivity.bringRetrofitResPopular(loadPage);
                        }
                    }
                }
            }
        });
    }

    private void moveTodescription(Movie item) {
        Intent intent = new Intent(this, DescriptionMovie.class);
        intent.putExtra("listMovie", item);
        startActivity(intent);
    }

    @Override
    public void valorList(ArrayList<Movie> movies) {
        this.movies = movies;

        if (movies.size() > 0) {
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
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            buttonRecargar.setVisibility(View.GONE);
            presenterActivity.bringRetrofitResPopular(loadPage);
            init();
        } else {
            buttonRecargar.setVisibility(View.GONE);
            Toast.makeText(this, "Sin Conexion a Internet", Toast.LENGTH_LONG).show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    buttonRecargar.setVisibility(View.VISIBLE);
                    buttonRecargar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            internetConnection();
                        }
                    });
                }
            }, 6000);
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

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id_item = item.getItemId();
        if (id_item == R.id.search) {
            Intent intent = new Intent(this, ViewSearchMovieActivity.class);
            startActivity(intent);
        }
        if (id_item == R.id.logout) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}