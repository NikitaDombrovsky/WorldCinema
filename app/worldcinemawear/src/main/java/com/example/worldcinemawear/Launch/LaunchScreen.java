package com.example.worldcinemawear.Launch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.worldcinemawear.Authorization.SignInActivity;
import com.example.worldcinemawear.R;


public class LaunchScreen extends Activity {
    //private TextView mTextView;
   // private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.launch_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LaunchScreen.this.startActivity(new Intent(LaunchScreen.this, SignInActivity.class));
                LaunchScreen.this.finish();
            }
        }, 1000);

        //mTextView = binding.text;
    }
}
