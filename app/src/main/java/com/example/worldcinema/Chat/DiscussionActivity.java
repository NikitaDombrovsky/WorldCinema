package com.example.worldcinema.Chat;

import static android.content.ContentValues.TAG;
import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worldcinema.R;
import com.example.worldcinema.network.ApiHandler;
import com.example.worldcinema.network.ErrorUtils;
import com.example.worldcinema.network.adapter.ChatAdapter;
import com.example.worldcinema.network.models.Chat.ChatResponse;
import com.example.worldcinema.network.service.ApiService;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscussionActivity extends AppCompatActivity {

    // НЕ РАБОТАЕТ!
    private ArrayList<ChatResponse> chatResponses;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ChatResponse chatResponse;
    private String token;
    private SharedPreferences sharedPreferences;
    private ChatAdapter chatAdapter;

    ApiService service = ApiHandler.getInstance().getService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);
        sharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        //sharedPreferences = getContext().getSharedPreferences("token", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false); // getContext()
        recyclerView = findViewById(R.id.Discussion_RecyclerView);
        getChats();

    }
    private void getChats() {
        AsyncTask.execute(() ->{
            service.getChats(token).enqueue(new Callback<List<ChatResponse>>() {
                @Override
                public void onResponse(Call<List<ChatResponse>> call, Response<List<ChatResponse>> response) {
                    if(response.isSuccessful()){
                        chatResponses = new ArrayList<>(response.body());
                        chatAdapter = new ChatAdapter(chatResponses, getApplicationContext()); //getContext()
                        recyclerView.setAdapter(chatAdapter);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        chatAdapter.notifyDataSetChanged();
                    } else if (response.code() == 400 ) {
                        String serverErrorMessage = ErrorUtils.parseError(response).message();
                        Log.d(TAG, serverErrorMessage.toString() + " || " + response.code());
                        Toast.makeText(getApplicationContext(), serverErrorMessage.toString(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Произошла неизвестная ошибка", Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void onFailure(Call<List<ChatResponse>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                }
            });
        });
    }
}
