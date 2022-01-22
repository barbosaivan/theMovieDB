package com.example.wposs_001_semillero_wmovie.presenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

import com.example.wposs_001_semillero_wmovie.view.ViewMainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemClock.sleep(3000);
        Intent intent = new Intent(this, ViewMainActivity.class);
        startActivity(intent);
        finish();
    }
}