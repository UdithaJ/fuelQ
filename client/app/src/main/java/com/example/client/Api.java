package com.example.client;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {

    String BASE_URL = "http://10.0.2.2:8080/";


    @Headers({
            "Accept: application/json",
            "Connection: keep-alive"
    })
    @POST("User/AddUser")
    Call<Driver> registerDriver(@Body Driver driver);

    @Headers({
            "Accept: application/json",
            "Connection: keep-alive"
    })
    @GET("GetFuelTypes")
    Call<List<FuelType>> getFuelTypes();
}
