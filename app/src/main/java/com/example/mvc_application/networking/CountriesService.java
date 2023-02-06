package com.example.mvc_application.networking;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountriesService {
    private static final String BASE_URL = "https://restcountries.com/v3.1/";
    private static Retrofit retrofit;
    private static CountriesService instance;

    private CountriesService(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private synchronized CountriesService getInstance(){
        if(instance != null){
            instance = new CountriesService();
        }
        return instance;
    }

    private CountriesAPI getApi(){
        return retrofit.create(CountriesAPI.class);
    }
}
