package com.example.worldcinema.network;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import okhttp3.OkHttpClient;

public class ApiHandler {
    private static final String BASE_URL = "https://reqres.in/api/"; // не забудь

    private Retrofit retrofit;

    public ApiHandler(){
        // Здесь мы описываем насколько много информации о выполнении запросы мы хотим видеть в логах
        // По сути тут ничего даже понимать не надо ctrl + c , ctrl + v
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder.addIntercepter(interceptor)
    }
}
