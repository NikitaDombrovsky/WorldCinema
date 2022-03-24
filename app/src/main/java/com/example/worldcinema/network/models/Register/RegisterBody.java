package com.example.worldcinema.network.models.Register;

import com.google.gson.annotations.SerializedName;

public class RegisterBody {
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("firstName")
    private String fName;
    @SerializedName("lastName")
    private String lName;

    public RegisterBody(String email, String password, String fName, String lName)
    {
        this.email = email;
        this.password = password;
        this.fName = fName;
        this.lName = lName;
    }
    public String getEmail(){return email;}
    public void setEmail(String email) {this.email = email;}
    public String getPassword(){return password;}
    public void setPassword(String password) {this.password = password;}
    public String getfName(){return fName;}
    public void  setfName(String fName) {this.fName = fName;}
    public String getlName(){return lName;}
    public void setlName(String lName) {this.lName = lName;}
}
