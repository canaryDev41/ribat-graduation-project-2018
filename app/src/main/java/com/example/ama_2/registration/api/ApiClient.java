package com.example.ama_2.registration.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ama_2 on 2/13/2018.
 */

public class ApiClient {

    private static final String BASE_URL = "http://10.42.0.1:8000/api/";

    private static ApiClient mInstance;
    private static Retrofit retrofit;

    private ApiClient(){

        retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

    }

    public static synchronized ApiClient getInstance(){

        if (mInstance == null){
            mInstance = new ApiClient();
        }

        return mInstance;

    }

    public ApiInterface getApi(){

        return retrofit.create(ApiInterface.class);

    }

}