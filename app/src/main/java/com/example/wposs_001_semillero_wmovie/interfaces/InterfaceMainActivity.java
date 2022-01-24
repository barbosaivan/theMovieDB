package com.example.wposs_001_semillero_wmovie.interfaces;

import com.example.wposs_001_semillero_wmovie.models.Movie;

import java.util.ArrayList;

public interface InterfaceMainActivity {
    interface ViewActivity {
        public void valorList(ArrayList<Movie> movies);

        public void valorLoad(boolean load);
    }

    interface presenterActivity {
        public void bringRetrofitResPopular(int loadPage);

        public void sendRetrofitResPopular(ArrayList<Movie> list);

        public void reloadLoadPage(boolean loadPage);

    }

    interface modelActivity {
        public void RetrofitResPopular(int nextPage);
    }
}
