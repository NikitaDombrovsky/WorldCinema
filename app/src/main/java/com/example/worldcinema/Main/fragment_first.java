package com.example.worldcinema.Main;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.worldcinema.R;
import com.example.worldcinema.network.ErrorUtils;
import com.example.worldcinema.network.Movie.MovieHandler;
import com.example.worldcinema.network.adapter.MovieAdapter;
import com.example.worldcinema.network.models.MovieData.MovieResponse;
import com.example.worldcinema.network.service.ApiService;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class fragment_first extends Fragment {

    private static final String TAG = "fragment_first";
    private ArrayList<MovieResponse> movieResponses;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MovieAdapter movieAdapter;

    ApiService serviceMovie = MovieHandler.getInstance().getService();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_first, container, false);
        // Настройка RecyclerView
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        getMovies();
        return view;
    }

    private void getMovies() {
        AsyncTask.execute(() -> {
            serviceMovie.getMovies().enqueue(new Callback<List<MovieResponse>>() {
                @Override
                public void onResponse(Call<List<MovieResponse>> call, Response<List<MovieResponse>> response) {
                    if(response.isSuccessful()){
                        // Вызов адаптера для заполнения recyclerView
                        movieResponses = new ArrayList<>(response.body());
                        movieAdapter = new MovieAdapter(movieResponses, getContext());
                        recyclerView.setAdapter(movieAdapter);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        movieAdapter.notifyDataSetChanged();
                    } else if (response.code() == 400) {
                        String serverErrorMessage = ErrorUtils.parseError(response).message();
                        Log.d(TAG, serverErrorMessage.toString() + " || " + response.code());
                        Toast.makeText(getContext(), serverErrorMessage.toString(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Ошибка", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<MovieResponse>> call, Throwable t) {
                    Toast.makeText(getContext(),t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                }
            });
        });
    }
}

