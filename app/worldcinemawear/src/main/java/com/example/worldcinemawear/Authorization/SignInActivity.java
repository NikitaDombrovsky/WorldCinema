package com.example.worldcinemawear.Authorization;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.worldcinemawear.Launch.LaunchScreen;
import com.example.worldcinemawear.MainActivity;
import com.example.worldcinemawear.R;
import com.example.worldcinemawear.network.ApiHandler;
import com.example.worldcinemawear.network.ErrorUtils;
import com.example.worldcinemawear.network.models.Login.LoginBody;
import com.example.worldcinemawear.network.models.Login.LoginResponse;
import com.example.worldcinemawear.network.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends Activity {
    EditText wear_signin_password, wear_signin_email;
    Button wear_signin_enter;

    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;
    private String token;
    Intent intent;

    private boolean isSignIn = false;

    ApiService service = ApiHandler.getInstance().getService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_screen);
        editor = getSharedPreferences("token", MODE_PRIVATE).edit();
        preferences = getSharedPreferences("token", MODE_PRIVATE);
        token = preferences.getString("token", "");

        wear_signin_email = findViewById(R.id.wear_signin_email);
        wear_signin_password = findViewById(R.id.wear_signin_password);
        wear_signin_enter = findViewById(R.id.wear_signin_enter);

        intent = new Intent(this, MainActivity.class);
        wear_signin_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();
                //startActivity(intent);
            }
        });
    }
    private void doLogin(){
        AsyncTask.execute(()->{
            service.doLogin(getLoginData()).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful()){
                        editor.putString("token", response.body().getToken()).apply();
                        Toast.makeText(getApplicationContext(), "Неплох +прав +невероятен +база + токен" + response.body().getToken(), Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    } else if (response.code() == 400) {
                        String serverErrorMessage = ErrorUtils.parseError(response).message();
                        Toast.makeText(getApplicationContext(), serverErrorMessage, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Плох",  Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        });
    }
    private LoginBody getLoginData(){
        return new LoginBody(wear_signin_email.getText().toString(), wear_signin_password.getText().toString());
    }
}
