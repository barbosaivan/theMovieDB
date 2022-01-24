package com.example.wposs_001_semillero_wmovie.interfaces;

import com.example.wposs_001_semillero_wmovie.models.Movie;

import java.util.ArrayList;

public interface InterfaceSearchMovieActivity {
    interface ViewSearchMovieActivity {
        public void valorList(ArrayList<Movie> movies);

        public void valorLoad(boolean load);
    }

    interface presenterSearchMovieActivity {
        public void bringRetrofitResNameMovie(int loadPage, String nameMovie);

        public void sendRetrofitResNameMovie(ArrayList<Movie> listNameMovie);

        public void reloadLoadPage(boolean loadPage);

    }

    interface modelSearchMovieActivity {
        public void retrofitResNameMovies(int nextPage, String nameMovie);
    }
}
