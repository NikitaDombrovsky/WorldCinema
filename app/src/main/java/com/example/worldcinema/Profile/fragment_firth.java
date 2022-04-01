package com.example.worldcinema.Profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.worldcinema.Login.AuthorizationScreen;
import com.example.worldcinema.R;
import com.example.worldcinema.network.ApiHandler;
import com.example.worldcinema.network.models.Profile.ProfileResponse;
import com.example.worldcinema.network.service.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class fragment_firth extends Fragment {

    private SharedPreferences sharedPreferences;
    private ApiService service = ApiHandler.getInstance().getService();
    private String token ;
    private TextView Profile_Text_Name, Profile_Text_Email;
    RelativeLayout Profile_Button_Discussion, Profile_Button_History, Profile_Button_Setting;
    Button Profile_Button_Exit;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_firth, container,false);
        // Получение токена из приложения
        sharedPreferences = getContext().getSharedPreferences("token", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");

        Profile_Text_Name = v.findViewById(R.id.Profile_Text_Name);
        Profile_Text_Email = v.findViewById(R.id.Profile_Text_Email);
        Profile_Button_Exit = v.findViewById(R.id.Profile_Button_Exit);
        Profile_Button_Discussion = v.findViewById(R.id.Profile_Button_Discussion);
        Profile_Button_History = v.findViewById(R.id.Profile_Button_History);
        Profile_Button_Setting = v.findViewById(R.id.Profile_Button_Setting);
        Button Button = v.findViewById(R.id.Profile_Button_Exit);
        Button.setOnClickListener(view -> {
            sharedPreferences.edit().remove("token").commit();
            startActivity(new Intent(getContext(), AuthorizationScreen.class));
        });
        // Все равно не реализовано
//        Profile_Button_Discussion.setOnClickListener(view -> { Toast.makeText(getContext(), "!", Toast.LENGTH_SHORT).show();startActivity(new Intent(getContext(), DiscussionActivity.class)); });
//        Profile_Button_History.setOnClickListener(view -> Toast.makeText(getContext(), "?", Toast.LENGTH_SHORT).show());
//        Profile_Button_Setting.setOnClickListener(view -> Toast.makeText(getContext(), "-", Toast.LENGTH_SHORT).show());
        getUserInfo();
        return v;

    }
    // Идентично doLogin из AuthorizationScreen
    private void getUserInfo(){
        AsyncTask.execute(() ->{
            service.getData(token).enqueue(new Callback<List<ProfileResponse>>() {
                @Override
                public void onResponse(Call<List<ProfileResponse>> call, Response<List<ProfileResponse>> response) {
                    Profile_Text_Email.setText(response.body().get(0).getEmail());
                    Profile_Text_Name.setText(response.body().get(0).getFirstName() + " " + response.body().get(0).getLastName());
                    // Парсинг Иконки не работает и проблема вроде в api
                    //Picasso.with(getContext()).load("http://cinema.areas.su/up/images/" + response.body().get(0).getAvatar()).into(Profile_Img);
                }

                @Override
                public void onFailure(Call<List<ProfileResponse>> call, Throwable t) {
                    Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        });
    }
}