package com.example.worldcinemawear;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worldcinemawear.Compilation.CompilationActivity;
import com.example.worldcinemawear.databinding.ActivityMainBinding;
import com.example.worldcinemawear.network.ApiHandler;
import com.example.worldcinemawear.network.models.Adapter.MovieAdapter;
import com.example.worldcinemawear.network.models.Movie.MovieResponse;
import com.example.worldcinemawear.network.service.ApiService;

import java.util.ArrayList;

public class MainActivity extends Activity {

//    private ArrayList<MovieResponse> movieResponses;
//    private RecyclerView recyclerView;
//    private LinearLayoutManager linearLayoutManager;
//    private MovieAdapter movieAdapter;
//    ApiService service = ApiHandler.getInstance().getService(); ////##################
    private LinearLayout wear_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wear_button = findViewById(R.id.wear_button);
        wear_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CompilationActivity.class));
            }
        });

    }
}