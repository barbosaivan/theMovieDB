package com.example.wposs_001_semillero_wmovie.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.wposs_001_semillero_wmovie.R;
import com.example.wposs_001_semillero_wmovie.models.WMovie;

import java.util.Objects;

public class DescriptionMovie extends AppCompatActivity {

    TextView titleDescription, overviewDescription, textViewSeeMore;
    ImageView posterMovieDescription;
    Button buttonBack;
    private boolean hideOverride;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_movie);
        Objects.requireNonNull(getSupportActionBar()).hide();

        WMovie movies = (WMovie) getIntent().getSerializableExtra("listMovie");
        titleDescription = findViewById(R.id.textViewTitleDescription);
        overviewDescription = findViewById(R.id.textViewOverviewDescription);
        posterMovieDescription = findViewById(R.id.imageViewMovieDescription);
        buttonBack = (Button) findViewById(R.id.buttonBack);
        textViewSeeMore = (TextView) findViewById(R.id.textviewSeeMore);
        hideOverride = false;

        titleDescription.setText(movies.getTitle());
        overviewDescription.setText(String.valueOf(movies.getOverview()));
        Glide.with(this).
                load("https://image.tmdb.org/t/p/w500/" + movies.getPoster_path())
                .centerCrop().crossFade().diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(posterMovieDescription);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        textViewSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hideOverride == false) {
                    overviewDescription.setLines(overviewDescription.getLineCount());
                    hideOverride = true;
                    textViewSeeMore.setText(R.string.see_less);
                }else{
                overviewDescription.setLines(4);
                    hideOverride = false;
                    textViewSeeMore.setText(R.string.see_more);
                }
            }
        });
    }
}