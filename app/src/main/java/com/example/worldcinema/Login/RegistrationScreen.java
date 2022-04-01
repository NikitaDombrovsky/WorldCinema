package com.example.worldcinema.Login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.os.AsyncTask;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.worldcinema.R;
import com.example.worldcinema.network.ApiHandler;
import com.example.worldcinema.network.service.ApiService;
import com.example.worldcinema.network.ErrorUtils;
import com.example.worldcinema.network.models.Register.RegisterBody;
import com.example.worldcinema.network.models.Register.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationScreen extends AppCompatActivity {
    Button Reg_Button_Reg;
    Button Reg_Button_Enter;
    EditText Reg_Text_Name, Reg_Text_SurName,Reg_Text_Email, Reg_Text_Password,Reg_Text_ResetPassword;

    ApiService service = ApiHandler.getInstance().getService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Reg_Button_Reg = (Button) findViewById(R.id.Reg_Button_Reg);
        Reg_Button_Enter = (Button) findViewById(R.id.Reg_Button_Enter);

        Reg_Text_Name = findViewById(R.id.Reg_Text_Name);
        Reg_Text_SurName = findViewById(R.id.Reg_Text_SurName);
        Reg_Text_Email = findViewById(R.id.Reg_Text_Email);
        Reg_Text_Password = findViewById(R.id.Reg_Text_Password);
        Reg_Text_ResetPassword = findViewById(R.id.Reg_Text_ResetPassword);

        Reg_Button_Reg.setOnClickListener(view -> doRegister());
        Reg_Button_Enter.setOnClickListener(view -> finish());
    }
    // Идентично doLogin из AuthorizationScreen
    private void doRegister(){
        AsyncTask.execute(() -> {
            service.doRegister(getRegisterData()).enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "Успешная регистрация!", Toast.LENGTH_SHORT).show();
                    } else if (response.code() == 400){
                        // TODO Успешно регистрирует но выдает ошибку
                        String serverErrorMessage = ErrorUtils.parseError(response).message();
                        Toast.makeText(getApplicationContext(), serverErrorMessage, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        });
    }

    private RegisterBody getRegisterData(){
        return new RegisterBody(Reg_Text_Email.getText().toString(), Reg_Text_Password.getText().toString(), Reg_Text_Name.getText().toString(), Reg_Text_SurName.getText().toString());
    }
}