package com.example.client;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {

    String BASE_URL = "http://10.0.2.2:8080/";


    @POST("User/registerDriver")
    Call<Driver> registerDriver(@Body Driver driver);

    @POST("FuelStation/registerFuelStation")
    Call<FuelStation> registerFuelStation(@Body FuelStation fuelStation);

    @POST("User/userLogin")
    Call<JsonObject> login(@Body User user);

    @GET("GetFuelTypes")
    Call<List<FuelType>> getFuelTypes();

    @GET("FuelStation/{id}")
    Call<JsonObject> getFuelStation(@Path("id") String id);

    @PUT("FuelStation/{id}")
    Call<JsonObject> updateFuelStation(@Path("id") String id, FuelStation fuelStation);

    @PUT("FuelInventory/{id}")
    Call<JsonObject> updateFuelInventory(@Path("id") String id, FuelInventory fuelInventory);


}

