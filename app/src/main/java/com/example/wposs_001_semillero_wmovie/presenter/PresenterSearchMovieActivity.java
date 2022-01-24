package com.example.wposs_001_semillero_wmovie.presenter;

import com.example.wposs_001_semillero_wmovie.interfaces.InterfaceSearchMovieActivity;
import com.example.wposs_001_semillero_wmovie.models.ModelSearchMovieActivity;
import com.example.wposs_001_semillero_wmovie.models.Movie;

import java.util.ArrayList;

public class PresenterSearchMovieActivity implements InterfaceSearchMovieActivity.presenterSearchMovieActivity {
    InterfaceSearchMovieActivity.modelSearchMovieActivity modelSearchMovieActivity;
    InterfaceSearchMovieActivity.ViewSearchMovieActivity viewSearchMovieActivity;

    public PresenterSearchMovieActivity(InterfaceSearchMovieActivity.ViewSearchMovieActivity viewSearchMovieActivity) {
        this.viewSearchMovieActivity = viewSearchMovieActivity;
        this.modelSearchMovieActivity = new ModelSearchMovieActivity(this);
    }

    @Override
    public void sendRetrofitResNameMovie(ArrayList<Movie> list) {
        viewSearchMovieActivity.valorList(list);
    }

    @Override
    public void bringRetrofitResNameMovie(int loadPage, String nameMovie) {
        modelSearchMovieActivity.retrofitResNameMovies(loadPage, nameMovie);
    }

    @Override
    public void reloadLoadPage(boolean loadPage) {
        viewSearchMovieActivity.valorLoad(loadPage);
    }
}
