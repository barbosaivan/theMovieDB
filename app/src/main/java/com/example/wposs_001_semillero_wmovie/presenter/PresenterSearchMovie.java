package com.example.wposs_001_semillero_wmovie.presenter;

import com.example.wposs_001_semillero_wmovie.interfaces.InterfaceSearchMovieActivity;
import com.example.wposs_001_semillero_wmovie.models.ModelSearchMovie;
import com.example.wposs_001_semillero_wmovie.entities.Movie;

import java.util.List;

public class PresenterSearchMovie implements InterfaceSearchMovieActivity.PresenterSearchMovieActivity {
    InterfaceSearchMovieActivity.ModelSearchMovieActivity modelSearchMovieActivity;
    InterfaceSearchMovieActivity.ViewSearchMovieActivity viewSearchMovieActivity;

    public PresenterSearchMovie(InterfaceSearchMovieActivity.ViewSearchMovieActivity viewSearchMovieActivity) {
        this.viewSearchMovieActivity = viewSearchMovieActivity;
        this.modelSearchMovieActivity = new ModelSearchMovie(this);
    }

    @Override
    public void sendRetrofitResNameMovie(List<Movie> list) {
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
