package com.example.worldcinema.network.models.Login;

import com.google.gson.annotations.SerializedName;

// Описывает что нам приходит с бека сервера
public class LoginResponse {
    @SerializedName("token")
    private String token;

    public String getToken(){return token;}
    public void setToken(String token) {this.token = token;}
}
