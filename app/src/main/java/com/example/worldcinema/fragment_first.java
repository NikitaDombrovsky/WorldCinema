package com.example.worldcinema;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.constraintlayout.helper.widget.Carousel;
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

import com.example.worldcinema.network.ErrorUtils;
import com.example.worldcinema.network.Movie.MovieCoverHandler;
import com.example.worldcinema.network.Movie.MovieHandler;
import com.example.worldcinema.network.adapter.CoversAdapter;
import com.example.worldcinema.network.adapter.MovieAdapter;
import com.example.worldcinema.network.models.MovieData.MovieCoverResponse;
import com.example.worldcinema.network.models.MovieData.MovieResponse;
import com.example.worldcinema.network.service.ApiService;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class fragment_first extends Fragment {

    private static final String TAG = "HomeFragment";
    private TextInputEditText editEmail, editPassword;
    private ArrayList<MovieCoverResponse> movieCoverResponses;
    private ArrayList<MovieResponse> movieResponses;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private CoversAdapter coversAdapter;
    private MovieAdapter movieAdapter;

    private boolean isSignIn = false;
    ApiService service = MovieCoverHandler.getInstance().getService();
    ApiService serviceMovie = MovieHandler.getInstance().getService();
    public fragment_first() {

    }

    public static fragment_first newInstance(String param1, String param2) {
        fragment_first fragment = new fragment_first();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_first, container, false);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        getMovies();
        return view;
    }

    private void getCovers(){
        AsyncTask.execute(() -> {
            service.getCovers().enqueue(new Callback<MovieCoverResponse>() {
                @Override
                public void onResponse(Call<MovieCoverResponse> call, Response<MovieCoverResponse> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(getContext(), "EHFF!"+response.body().getBackgroundImage(), Toast.LENGTH_SHORT).show();
                        movieCoverResponses.add(response.body());
                        movieAdapter.notifyDataSetChanged();
                    } else if (response.code() == 400) {
                        String serverErrorMessage = ErrorUtils.parseError(response).message();
                        Log.d(TAG, serverErrorMessage.toString() + " || " + response.code());
                        Toast.makeText(getContext(), serverErrorMessage.toString(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Произошла неизвестная ошибка", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<MovieCoverResponse> call, Throwable t) {
                    Toast.makeText(getContext(),t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void getMovies() {
        AsyncTask.execute(() -> {
            serviceMovie.getMovies().enqueue(new Callback<List<MovieResponse>>() {
                @Override
                public void onResponse(Call<List<MovieResponse>> call, Response<List<MovieResponse>> response) {
                    if(response.isSuccessful()){
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
                        Toast.makeText(getContext(), "Произошла неизвестная ошибка", Toast.LENGTH_SHORT).show();
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
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public fragment_first() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment fragment_first.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static fragment_first newInstance(String param1, String param2) {
//        fragment_first fragment = new fragment_first();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_first, container, false);
//    }
