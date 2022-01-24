package com.example.wposs_001_semillero_wmovie.presenter;

import com.example.wposs_001_semillero_wmovie.interfaces.InterfaceMainActivity;
import com.example.wposs_001_semillero_wmovie.models.ModelMainActivity;
import com.example.wposs_001_semillero_wmovie.models.Movie;

import java.util.ArrayList;

public class PresenterMainActivity implements InterfaceMainActivity.presenterActivity {
    InterfaceMainActivity.modelActivity modelActivity;
    InterfaceMainActivity.ViewActivity viewActivity;

    public PresenterMainActivity(InterfaceMainActivity.ViewActivity viewActivity) {
        this.viewActivity = viewActivity;
        this.modelActivity = new ModelMainActivity(this);
    }

    @Override
    public void sendRetrofitResPopular(ArrayList<Movie> list) {
        viewActivity.valorList(list);
    }

    @Override
    public void bringRetrofitResPopular(int loadPage) {
        modelActivity.RetrofitResPopular(loadPage);
    }

    @Override
    public void reloadLoadPage(boolean loadPage) {
        viewActivity.valorLoad(loadPage);
    }

}
