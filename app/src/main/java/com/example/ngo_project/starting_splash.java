package com.example.ngo_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class starting_splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_splash);


        Intent intent = new Intent(this, MainActivity.class);
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        };
    handler.postDelayed(runnable, 1750);
    }
}