package com.example.worldcinema;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.os.AsyncTask;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.worldcinema.network.ApiHandler;
import com.example.worldcinema.network.models.LoginBody;
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
        Intent intent = new Intent (this, MainActivity.class);

        View.OnClickListener reg_btn_reg = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doRegister();
            }
        };
        View.OnClickListener reg_btn_enter = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        };
        Reg_Button_Reg.setOnClickListener(reg_btn_reg);
        Reg_Button_Enter.setOnClickListener(reg_btn_enter);





    }
    private void doRegister(){
        AsyncTask.execute(() -> {
            service.doRegister(getRegisterData()).enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                    // onResponse - срабатывает всегда, вне зависимости успешно ли выполнился запрос ( успешное выполение это как правило код 200)
                    // поэтому мы должны проверять успешно ли выполнился запрос
                    // и обработать уже серверные ошибки - 404 Not found, 400 Bad Request и так далее

                    if (response.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "СДАВАЙСЯ ТЫ ЗАРЕГИСТРИРОВАН + Токен:" + response.body().getToken(), Toast.LENGTH_SHORT).show();
                    } else if (response.code() == 400){
                        // 400 - это Bad request, такую ошибку сервер выдает когда мы неправильно пользуемся API
                        // поэтому мы на клиентской стороне должны обработать эту ошибку и показать пользователю
                        // чтобы он понял, что он делает не правильно
                        // к примеру не ввел email или пароль, но пытается залогиниться

                        // Чтобы преобразовать json ошибки в строку мы используем наш класс ErrorUtils
                        String serverErrorMessage = ErrorUtils.parseError(response).message();
                        Toast.makeText(getApplicationContext(), serverErrorMessage, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Лол Ошибка", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {
                    // в блоке onFailure обрабатываются ошибки, которые не связаны с сервером бэкэнда
                    // например если на устройстве нет доступа в Интернет
                    Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        });
    }

    private RegisterBody getRegisterData(){
        return new RegisterBody(Reg_Text_Email.getText().toString(), Reg_Text_Password.getText().toString(), Reg_Text_Name.getText().toString(), Reg_Text_SurName.getText().toString());
    }
}