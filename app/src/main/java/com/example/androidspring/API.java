package com.example.androidspring;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface API {

// Two apis to post for register and login
    @POST("register")
    Call<ResponseBody> createUser (
            @Body User user   // Creating an object for the User class
    );

    @POST("login")
    Call<ResponseBody> checkUser (
            @Body User user
    );

}
