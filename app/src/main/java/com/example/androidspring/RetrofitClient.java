package com.example.androidspring;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {
//Go to settings and copy the IP version 4 address
    private static final String BASE_URL = "http://10.100.3.151:8080/api/"; // change to the neutral one in case you are using your own network(hotspoting )
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private RetrofitClient () {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public API getAPI () {
        return retrofit.create(API.class);
    }
}
