package com.example.data.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fatahfadhlurrohman on Fri, 04 Sep 2020
 */
public class LoginRequest {

    @SerializedName("email") private String email;

    @SerializedName("password") private String password;

    public LoginRequest(final String email, final String password) {
        this.email = email;
        this.password = password;
    }
}
