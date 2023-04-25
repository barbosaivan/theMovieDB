package com.example.wposs_001_semillero_wmovie.presenter;

import com.example.wposs_001_semillero_wmovie.interfaces.InterfaceMainActivity;
import com.example.wposs_001_semillero_wmovie.models.ModelMainActivity;
import com.example.wposs_001_semillero_wmovie.entities.Movie;

import java.util.List;

public class PresenterMainActivity implements InterfaceMainActivity.PresenterActivity {
    InterfaceMainActivity.ModelActivity modelActivity;
    InterfaceMainActivity.ViewActivity viewActivity;

    public PresenterMainActivity(InterfaceMainActivity.ViewActivity viewActivity) {
        this.viewActivity = viewActivity;
        this.modelActivity = new ModelMainActivity(this);
    }

    @Override
    public void sendRetrofitResPopular(List<Movie> list) {
        viewActivity.valorList(list);
    }

    @Override
    public void bringRetrofitResPopular(int loadPage) {
        modelActivity.retrofitResPopular(loadPage);
    }

    @Override
    public void reloadLoadPage(boolean loadPage) {
        viewActivity.valorLoad(loadPage);
    }

}
