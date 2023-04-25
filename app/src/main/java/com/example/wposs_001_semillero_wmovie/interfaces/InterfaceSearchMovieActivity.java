package com.example.wposs_001_semillero_wmovie.interfaces;

import com.example.wposs_001_semillero_wmovie.entities.Movie;

import java.util.List;

public interface InterfaceSearchMovieActivity {
    interface ViewSearchMovieActivity {
        void valorList(List<Movie> movies);

        void valorLoad(boolean load);
    }

    interface PresenterSearchMovieActivity {
        void bringRetrofitResNameMovie(int loadPage, String nameMovie);

        void sendRetrofitResNameMovie(List<Movie> listNameMovie);

        void reloadLoadPage(boolean loadPage);

    }

    interface ModelSearchMovieActivity {
        void retrofitResNameMovies(int nextPage, String nameMovie);
    }
}
