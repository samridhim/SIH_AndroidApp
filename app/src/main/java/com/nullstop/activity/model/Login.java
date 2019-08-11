package com.nullstop.activity.model;

import com.google.gson.annotations.SerializedName;

public class Login {
    @SerializedName("email")
    public String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @SerializedName("password")
    public String password;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //
}
