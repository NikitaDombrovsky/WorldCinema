package com.example.worldcinema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.worldcinema.network.ApiHandler;
import com.example.worldcinema.network.ErrorUtils;
import com.example.worldcinema.network.models.LoginBody;
import com.example.worldcinema.network.models.LoginResponse;
import com.example.worldcinema.network.service.ApiService;

import org.jetbrains.annotations.Async;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AuthorizationScreen extends AppCompatActivity {

    Button Aut_Button_Enter;
    Button Aut_Button_Reg;
    EditText Aut_Test_Email, Aut_Test_Password;
    Intent intent1;

    ApiService service = ApiHandler.getInstance().getService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        Aut_Button_Enter = (Button) findViewById(R.id.Aut_Button_Enter);
        Aut_Button_Reg = (Button) findViewById(R.id.Aut_Button_Reg);
        Aut_Test_Email = (EditText) findViewById(R.id.Aut_Test_Email);
        Aut_Test_Password = (EditText) findViewById(R.id.Aut_Test_Password);
        Intent intent = new Intent(this, RegistrationScreen.class);
        intent1 = new Intent(this, MainActivity.class);

        // Вроде рабочий листенер !!!!!!!!!!!!!!!
        View.OnClickListener aut_btn_enter = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();
            }
        };
        View.OnClickListener aut_btn_reg = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        };
        Aut_Button_Enter.setOnClickListener(aut_btn_enter);
        Aut_Button_Reg.setOnClickListener(aut_btn_reg);
    }
    private void doLogin(){
        AsyncTask.execute(()->{
            service.doLogin(getLoginData()).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "Неплох +прав +невероятен +база + токен" + response.body().getToken(), Toast.LENGTH_SHORT).show();
                        startActivity(intent1);
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
        return new LoginBody(Aut_Test_Email.getText().toString(), Aut_Test_Password.getText().toString());
    }
}
