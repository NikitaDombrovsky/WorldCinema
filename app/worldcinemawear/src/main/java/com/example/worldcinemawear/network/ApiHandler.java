package com.example.worldcinemawear.network;

import com.example.worldcinemawear.network.service.ApiService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHandler {
    private static ApiHandler mInstance;

    // private static final String BASE_URL = "https://reqres.in/api/"; // не забудь
    private static final String BASE_URL = "http://cinema.areas.su/";
    // private static final String BASE_URL = "http://cinema.areas.su/auth/";

    private Retrofit retrofit;

    public ApiHandler() {
        // Здесь мы описываем насколько много информации о выполнении запросы мы хотим видеть в логах
        // По сути тут ничего даже понимать не надо ctrl + c , ctrl + v
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);
        // Здесь мы конфигугируем штуку которая будет выполнять наши запросы
        // по сути мы выставляем различные настройки
        // если хотите переопределить какие-то уже готовые реализации на свои собственные ( как например обработка ошибок) то указываете это тут
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ErrorUtils.retrofit = retrofit;
        // говорим ретрофиту что будем использовать GsonConverterFactory чтобы конвертировать json в наши java-классы

    }
    // через этот метод будем получать экземпляр нашего ApiHandler
    public static ApiHandler getInstance() {
        if (mInstance == null) {
            mInstance = new ApiHandler();
        }
        return mInstance;
    }

    public ApiService getService() {return  retrofit.create(ApiService.class);}
}