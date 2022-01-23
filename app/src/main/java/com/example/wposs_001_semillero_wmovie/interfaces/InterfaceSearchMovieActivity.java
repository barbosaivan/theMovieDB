package com.example.wposs_001_semillero_wmovie.interfaces;

import com.example.wposs_001_semillero_wmovie.models.WMovie;

import java.util.ArrayList;

public interface InterfaceSearchMovieActivity {
    interface ViewSearchMovieActivity {
        public void valorList(ArrayList<WMovie> movies);

        public void valorLoad(boolean load);
    }

    interface presenterSearchMovieActivity {
        public void bringRetrofitResNameMovie(int loadPage, String nameMovie);

        public void sendRetrofitResNameMovie(ArrayList<WMovie> listNameMovie);

        public void reloadLoadPage(boolean loadPage);

    }

    interface modelSearchMovieActivity {
        public void retrofitResNameMovies(int nextPage, String nameMovie);
    }
}
