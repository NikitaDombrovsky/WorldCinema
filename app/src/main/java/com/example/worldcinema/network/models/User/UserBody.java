package com.example.worldcinema.network.models.User;

import com.google.gson.annotations.SerializedName;

public class UserBody {

    @SerializedName("userId")
    private String userId;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("email")
    private String email;
    @SerializedName("avatar")
    private String avatar;

    public UserBody(String userId, String firstName, String lastName, String email, String avatar)
    {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.avatar = avatar;
    }

    public String getUserId(){return userId;}
    public void setUserId(String userId) {this.userId = userId;}
    public String getFirstName(){return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public String getLastName(){return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public String getEmail(){return email;}
    public void setEmail(String email) {this.email = email;}
    public String getAvatar(){return avatar;}
    public void setAvatar(String avatar) {this.avatar = avatar;}


}
