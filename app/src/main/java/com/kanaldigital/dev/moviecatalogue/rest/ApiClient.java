package com.kanaldigital.dev.moviecatalogue.rest;

import com.kanaldigital.dev.moviecatalogue.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
