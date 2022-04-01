package com.example.worldcinema.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.worldcinema.Main.MainActivity;
import com.example.worldcinema.R;
import com.example.worldcinema.network.ApiHandler;
import com.example.worldcinema.network.ErrorUtils;
import com.example.worldcinema.network.models.Login.LoginBody;
import com.example.worldcinema.network.models.Login.LoginResponse;
import com.example.worldcinema.network.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AuthorizationScreen extends AppCompatActivity {

    Button Aut_Button_Enter;
    Button Aut_Button_Reg;
    EditText Aut_Test_Email, Aut_Test_Password;
    Intent intent1;

    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;
    private String token;

    ApiService service = ApiHandler.getInstance().getService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        // Использование настроек для для сохранение токена
        editor = getSharedPreferences("token", MODE_PRIVATE).edit();
        preferences = getSharedPreferences("token", MODE_PRIVATE);
        token = preferences.getString("token", "");
        intent1 = new Intent(this, MainActivity.class);
        if (token != ""){
            startActivity(intent1);
        }
        editor = preferences.edit();

        Aut_Button_Enter = (Button) findViewById(R.id.Aut_Button_Enter);
        Aut_Button_Reg = (Button) findViewById(R.id.Aut_Button_Reg);
        Aut_Test_Email = (EditText) findViewById(R.id.Aut_Test_Email);
        Aut_Test_Password = (EditText) findViewById(R.id.Aut_Test_Password);

        Aut_Button_Enter.setOnClickListener(view -> doLogin());
        Aut_Button_Reg.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), RegistrationScreen.class)));

    }
    private void doLogin(){
        AsyncTask.execute(()->{
            service.doLogin(getLoginData()).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    // onResponse - срабатывает всегда, вне зависимости успешно ли выполнился запрос
                    if (response.isSuccessful()){
                        editor.putString("token", response.body().getToken()).apply();
                        //Toast.makeText(getApplicationContext(), "невероятен + токен" + response.body().getToken(), Toast.LENGTH_SHORT).show();
                        editor.commit();
                        startActivity(intent1);
                    // Серверные ошибки (Bad request)
                    } else if (response.code() == 400) {
                        // Преобразование json ошибки в строку (ErrorUtils)
                        String serverErrorMessage = ErrorUtils.parseError(response).message();
                        Toast.makeText(getApplicationContext(), serverErrorMessage, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Плох",  Toast.LENGTH_SHORT).show();
                    }
                }
                // Ошибки на устройстве
                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        });
    }
    private LoginBody getLoginData(){
        return new LoginBody(Aut_Test_Email.getText().toString(), Aut_Test_Password.getText().toString());
    }
}
