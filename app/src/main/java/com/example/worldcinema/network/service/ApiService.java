package com.example.worldcinema.network.service;

import com.example.worldcinema.network.models.LoginBody;
import com.example.worldcinema.network.models.LoginResponse;
import com.example.worldcinema.network.models.MovieData.MovieCoverResponse;
import com.example.worldcinema.network.models.MovieData.MovieResponse;
import com.example.worldcinema.network.models.Profile.ProfileResponse;
import com.example.worldcinema.network.models.Register.RegisterBody;
import com.example.worldcinema.network.models.Register.RegisterResponse;
import com.example.worldcinema.network.models.Chat.ChatResponse;
import com.example.worldcinema.network.models.User.UserBody;
import com.example.worldcinema.network.models.User.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Этот интерфейс представляет собой описание всех запросов с бэка которые вы хотите выполнить
 * По сути сюда вы переписываете запросы которые нам дадут в Swagger
 */

/*
 * Response классы - классы в которых описано что приходит нам с бэка
 * Body классы - классы в которых описано что мы отправляем нам бэк ( к примеру чтобы залогиниться нам надо отправить email и password )
 * */
public interface ApiService {
    @POST("/auth/login") // тут описываем endpoint - эта та часть строки, которая остается если убрать из нее base url
    Call<LoginResponse> doLogin(@Body LoginBody registerBody);
    @POST("/auth/register")
    Call<RegisterResponse> doRegister (@Body RegisterBody registerBody);
    @GET("cover")
    Call<MovieCoverResponse> getCovers();
    @GET("movies?filter=inTrend")
    Call<List<MovieResponse>> getMovies();
    @GET("/user")
    Call<List<ProfileResponse>> getData(@Header("Authorization") String token);
    @GET("/user/chats")
    Call<List<ChatResponse>> getChats(@Header("Authorization") String token);
//    @GET("/user")
//    Call<UserResponse> getUser();
//    @GET("/user")
//    Call<UserResponse> getUser (@Body UserBody userBody);
}
