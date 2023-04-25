package com.example.wposs_001_semillero_wmovie.interfaces;

import com.example.wposs_001_semillero_wmovie.entities.Movie;

import java.util.List;

public interface InterfaceMainActivity {
    interface ViewActivity {
        void valorList(List<Movie> movies);

        void valorLoad(boolean load);
    }

    interface PresenterActivity {
        void bringRetrofitResPopular(int loadPage);

        void sendRetrofitResPopular(List<Movie> list);

        void reloadLoadPage(boolean loadPage);

    }

    interface ModelActivity {
        void retrofitResPopular(int nextPage);
    }
}
