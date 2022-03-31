package com.example.worldcinemawear.network.service;

import com.example.worldcinemawear.network.models.Login.LoginBody;
import com.example.worldcinemawear.network.models.Login.LoginResponse;
import com.example.worldcinemawear.network.models.Movie.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/auth/login") // тут описываем endpoint - эта та часть строки, которая остается если убрать из нее base url
    Call<LoginResponse> doLogin(@Body LoginBody registerBody);
    @GET("movies?filter=inTrend")
    Call<List<MovieResponse>> getMovies();
}
