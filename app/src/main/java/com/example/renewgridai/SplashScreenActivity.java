package com.example.renewgridai;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content view to a custom splash screen layout
        setContentView(R.layout.activity_splash);

        // Delay for a few seconds and then launch the main activity
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(this.getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish(); // Finish splash activity so the user cannot return to it
        }, 1500);
    }
}
