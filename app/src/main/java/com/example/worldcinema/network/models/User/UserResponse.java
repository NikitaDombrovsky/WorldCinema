package com.example.worldcinema.network.models.User;

import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("token")
    private String token;

    public String getToken(){return token;}
    public void setToken(String token) {this.token = token;}
}
