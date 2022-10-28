package com.example.client;

import com.google.gson.JsonArray;
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

    @POST("User/joinQueue")
    Call<QueueClass> joinQueue(@Body QueueClass queue);

    @POST("FuelStation/registerFuelStation")
    Call<FuelStation> registerFuelStation(@Body FuelStation fuelStation);

    @POST("User/userLogin")
    Call<JsonObject> login(@Body User user);

    @GET("GetFuelTypes")
    Call<List<FuelType>> getFuelTypes();

    @GET("VehicleType/GetVehicleTypes")
    Call<JsonArray> getVehicleTypes();

    @GET("FuelStation/{id}")
    Call<JsonObject> getFuelStation(@Path("id") String id);

    @GET("FuelStation/{name}")
    Call<JsonObject> searchStationByName(@Path("name") String name);


    @GET("FuelStation/GetStationFuelAmount/{stationId}")
    Call<JsonArray> getFuelInventory(@Path("stationId") String stationId);

    @PUT("FuelStation/{id}")
    Call<JsonObject> updateFuelStation(@Path("id") String id, @Body FuelStation fuelStation);

    @PUT("FuelInventory/UpdateFuelAmount/{stationId}/{fuelTypeId}/{ammount}")
    Call<JsonObject> updateFuelInventory(@Path("stationId") String stationId, @Path("fuelTypeId") String fuelTypeId, @Path("ammount") Integer ammount);

    @PUT("VehicleType/UpdateAllowedFuelAmount/{vid}/{amount}")
    Call<Void> updateVehicleMaxFuelAmount(@Path("vid") String id, @Path("amount") Integer amount);


}

