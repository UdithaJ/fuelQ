package com.example.client;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.client.databinding.AdminPageBinding;
import com.example.client.databinding.FuelStationPageBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminActivity extends Fragment {

    private @NonNull
    AdminPageBinding binding;
    TextInputLayout bikeAmount, carAmount, busAmount, threeWheelAmount;


    public AdminActivity() {
    }

    public static AdminActivity newInstance(String param1, String param2) {
        AdminActivity fragment = new AdminActivity();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getVehicleTypes();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = AdminPageBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        bikeAmount = view.findViewById(R.id.bike_max_amount_field);
        carAmount = view.findViewById(R.id.car_max_amount_field);
        busAmount = view.findViewById(R.id.bus_max_amount_field);
        threeWheelAmount = view.findViewById(R.id.three_wheel_max_amount_field);

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.bikeMaxAmountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editBikeAmount =  bikeAmount.getEditText().getText().toString();
                updateVehicleMaxFuelAmount(Config.bike, Integer.valueOf(editBikeAmount));
            }
        });

        binding.carMaxAmountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editCarAmount =  carAmount.getEditText().getText().toString();
                updateVehicleMaxFuelAmount(Config.car, Integer.valueOf(editCarAmount));
            }
        });

        binding.busMaxAmountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editBusAmount =  busAmount.getEditText().getText().toString();
                updateVehicleMaxFuelAmount(Config.bus, Integer.valueOf(editBusAmount));
            }
        });

        binding.threeWheelMaxAmountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editThreeWheelAmount =  threeWheelAmount.getEditText().getText().toString();
                updateVehicleMaxFuelAmount(Config.threeWheel, Integer.valueOf(editThreeWheelAmount));
            }
        });


    }

    private void getVehicleTypes(){
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        HttpsTrustManager.allowAllSSL();

        Call<JsonArray> call = retrofitClient.getMyApi().getVehicleTypes();

        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {

                JsonArray vehicleTypes = response.body();

                JsonObject car = (JsonObject) vehicleTypes.get(0);
                JsonObject bus = (JsonObject) vehicleTypes.get(1);
                JsonObject bike = (JsonObject) vehicleTypes.get(2);
                JsonObject threeWheel = (JsonObject) vehicleTypes.get(3);

                bikeAmount.getEditText().setText(bike.get("maxAmmount").getAsString());
                carAmount.getEditText().setText(car.get("maxAmmount").getAsString());
                busAmount.getEditText().setText(bus.get("maxAmmount").getAsString());
                threeWheelAmount.getEditText().setText(threeWheel.get("maxAmmount").getAsString());

            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {

                Log.i("response", String.valueOf(t));

            }
        });

    }

    private void updateVehicleMaxFuelAmount(String id, Integer amount){
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        HttpsTrustManager.allowAllSSL();

        Call<Void> call = retrofitClient.getMyApi().updateVehicleMaxFuelAmount(id, amount);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

//                JsonObject res = response.body();
//
//                Log.i("response", String.valueOf(res));

                Snackbar.make(getActivity().findViewById(android.R.id.content),
                        "Fuel Amount Updated", Snackbar.LENGTH_LONG).show();


            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                Log.i("response", String.valueOf(t));

            }
        });

    }
}