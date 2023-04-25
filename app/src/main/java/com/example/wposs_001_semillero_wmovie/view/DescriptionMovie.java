package com.example.wposs_001_semillero_wmovie.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.wposs_001_semillero_wmovie.R;
import com.example.wposs_001_semillero_wmovie.entities.Movie;

import java.util.Objects;

public class DescriptionMovie extends AppCompatActivity {

    TextView titleDescription;
    TextView overviewDescription;
    TextView textViewSeeMore;
    TextView textViewGenreDescription;
    ImageView posterMovieDescription;
    ImageView imageViewCast;
    Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_movie);
        Objects.requireNonNull(getSupportActionBar()).hide();
        final boolean[] hideOverride = {false};
        titleDescription = findViewById(R.id.textViewTitleDescription);
        overviewDescription = findViewById(R.id.textViewOverviewDescription);
        posterMovieDescription = findViewById(R.id.imageViewMovieDescription);
        buttonBack = findViewById(R.id.buttonBack);
        textViewSeeMore = findViewById(R.id.textviewSeeMore);
        imageViewCast = findViewById(R.id.imageViewCast);
        textViewGenreDescription = findViewById(R.id.textViewGenresDescription);


        final Movie[] movies = {(Movie) getIntent().getSerializableExtra("listMovie")};
        titleDescription.setText(movies[0].getTitle());
        overviewDescription.setText(String.valueOf(movies[0].getOverview()));
        if (movies[0].getGenre_ids().length > 0) {
            textViewGenreDescription.setText(movies[0].getGenre_ids()[0]);
        } else {
            textViewGenreDescription.setText(R.string.msg_gnere_default);
        }
        Glide.with(this).
                load("https://image.tmdb.org/t/p/w500/" + movies[0].getPoster_path())
                .centerCrop().crossFade().diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(posterMovieDescription);

        Glide.with(this).
                load("https://image.tmdb.org/t/p/w500/" + movies[0].getBackdrop_path())
                .centerCrop().crossFade().diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageViewCast);

        buttonBack.setOnClickListener(v -> finish());

        textViewSeeMore.setOnClickListener(view -> {
            if (overviewDescription.getLineCount() > 4) {
                if (!hideOverride[0]) {
                    overviewDescription.setLines(overviewDescription.getLineCount());
                    hideOverride[0] = true;
                    textViewSeeMore.setText(R.string.see_less);
                } else {
                    overviewDescription.setLines(4);
                    hideOverride[0] = false;
                    textViewSeeMore.setText(R.string.see_more);
                }
            } else {
                textViewSeeMore.setVisibility(View.GONE);
            }
        });
    }
}