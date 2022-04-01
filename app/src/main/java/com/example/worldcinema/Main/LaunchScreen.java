package com.example.worldcinema.Main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.worldcinema.Login.AuthorizationScreen;
import com.example.worldcinema.R;

public class LaunchScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_screen);
        // Костыльная загрузка через таймер
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LaunchScreen.this.startActivity(new Intent(LaunchScreen.this, AuthorizationScreen.class));
                LaunchScreen.this.finish();
            }
        }, 1000);
    }
}
