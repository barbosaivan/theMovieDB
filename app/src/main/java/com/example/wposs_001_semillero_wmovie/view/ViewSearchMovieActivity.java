package com.example.wposs_001_semillero_wmovie.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.wposs_001_semillero_wmovie.R;

import java.util.Objects;

public class ViewSearchMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);
        Objects.requireNonNull(getSupportActionBar()).hide();
    }
}