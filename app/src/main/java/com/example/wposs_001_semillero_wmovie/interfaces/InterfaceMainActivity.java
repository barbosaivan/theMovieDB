package com.example.wposs_001_semillero_wmovie.interfaces;

import com.example.wposs_001_semillero_wmovie.models.WMovie;

import java.util.ArrayList;

public interface InterfaceMainActivity {
    interface ViewActivity {
        public void valorList(ArrayList<WMovie> movies);

        public void valorLoad(boolean load);
    }

    interface presenterActivity {
        public void bringRetrofitResPopular(int loadPage);

        public void sendRetrofitResPopular(ArrayList<WMovie> list);

        public void reloadLoadPage(boolean loadPage);

    }

    interface modelActivity {
        public void RetrofitResPopular(int nextPage);
    }
}
