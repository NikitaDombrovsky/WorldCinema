package com.example.worldcinema.network.service;

import com.example.worldcinema.network.models.Login.LoginBody;
import com.example.worldcinema.network.models.Login.LoginResponse;
import com.example.worldcinema.network.models.MovieData.MovieResponse;
import com.example.worldcinema.network.models.Profile.ProfileResponse;
import com.example.worldcinema.network.models.Register.RegisterBody;
import com.example.worldcinema.network.models.Register.RegisterResponse;
import com.example.worldcinema.network.models.Chat.ChatResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface ApiService {
    // endpointы
    @POST("/auth/login")
    Call<LoginResponse> doLogin(@Body LoginBody registerBody);
    @POST("/auth/register")
    Call<RegisterResponse> doRegister (@Body RegisterBody registerBody);
    @GET("movies?filter=inTrend")
    Call<List<MovieResponse>> getMovies();
    @GET("/user")
    Call<List<ProfileResponse>> getData(@Header("Authorization") String token);
    @GET("/user/chats")
    Call<List<ChatResponse>> getChats(@Header("Authorization") String token);
}
