package com.example.worldcinemawear.Authorization;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;

import com.example.worldcinemawear.Launch.LaunchScreen;
import com.example.worldcinemawear.R;

public class SignInActivity extends Activity {
    EditText wear_signin_password, wear_signin_email;
    Button wear_signin_enter;

    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;
    private String token;

    private boolean isSignIn = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.signin_screen);

    }
}
