package com.example.mvc_application.networking;

import com.example.mvc_application.model.Country;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface CountriesAPI {
    @GET("all")
    Single<List<Country>> getCountries();
}
