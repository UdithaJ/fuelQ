package com.example.client;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {

    String BASE_URL = "http://10.0.2.2:8080/";


    @POST("User/registerDriver")
    Call<Driver> registerDriver(@Body Driver driver);


    @POST("User/AddFuelStation")
    Call<FuelStation> registerFuelStation(@Body FuelStation fuelStation);


    @GET("GetFuelTypes")
    Call<List<FuelType>> getFuelTypes();


    @POST("User/userLogin")
    Call<JsonObject> login(@Body User user);
}

