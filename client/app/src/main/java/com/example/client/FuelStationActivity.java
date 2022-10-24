package com.example.client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.client.databinding.FuelStationPageBinding;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FuelStationActivity extends Fragment {

    private @NonNull
    FuelStationPageBinding binding;
    String stationId = null;
    Integer defaultAmount = 100;
    TextInputLayout address, fsName, permitNo, petrolAmount, dieselAmount;
    View petrolBar, dieselBar;


    public FuelStationActivity() {
        // Required empty public constructor
    }


//    public static FuelStationActivity newInstance(String param1, String param2) {
//        FuelStationActivity fragment = new FuelStationActivity();
//        Bundle args = new Bundle();
//
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FuelStationPageBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        address = view.findViewById(R.id.fs_address_field);
        fsName = view.findViewById(R.id.fs_name_field);
        permitNo = view.findViewById(R.id.permit_field);
        petrolAmount = view.findViewById(R.id.petrol_field);
        dieselAmount = view.findViewById(R.id.diesel_field);
        petrolBar = view.findViewById(R.id.petrol_bar);
        dieselBar = view.findViewById(R.id.diesel_bar);

        petrolAmount.getEditText().addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                String editPetrolAmount =  petrolAmount.getEditText().getText().toString();
                ViewGroup.LayoutParams lp = petrolBar.getLayoutParams();
                lp.width = 150;
                lp.height = defaultAmount/100;
                if(editPetrolAmount.equals("")){
                    petrolAmount.getEditText().setText(defaultAmount.toString());
                }
                else{
                    lp.height = Integer.valueOf(editPetrolAmount) /10;
                }
                petrolBar.requestLayout();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getFuelStation("634bb8f3d23a3fd969a5a788");

        ViewGroup.LayoutParams lp = petrolBar.getLayoutParams();
        lp.width = 150;
        lp.height = 500;
        petrolBar.requestLayout();

        ViewGroup.LayoutParams lp2 = petrolBar.getLayoutParams();
        lp2.width = 150;
        lp2.height = 300;
        petrolBar.requestLayout();

        SharedPreferences reader = getActivity().getPreferences(Context.MODE_PRIVATE);
        stationId = reader.getString("stationId", "undefined");

        binding.updateFsDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String editAddress =  address.getEditText().getText().toString();
                String editName =  fsName.getEditText().getText().toString();
                String editPermitNo =  permitNo.getEditText().getText().toString();

                FuelStation fuelStation = new FuelStation(stationId, "", "", editName, editPermitNo, editAddress,"");
                updateFuelStation(stationId, fuelStation);
            }
        });

        binding.petrolAmountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String editPetrolAmount =  petrolAmount.getEditText().getText().toString();

                FuelInventory fuelInventory = new FuelInventory("", stationId, Config.petrolId, Integer.valueOf(editPetrolAmount));
                updateFuelInventory(stationId, fuelInventory);
            }
        });

        binding.dieselAmountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String editDieselAmount =  dieselAmount.getEditText().getText().toString();

                FuelInventory fuelInventory = new FuelInventory("", stationId, Config.petrolId, Integer.valueOf(editDieselAmount));
                updateFuelInventory(stationId, fuelInventory);
            }
        });

    }

    private Integer getFuelStation(String id){
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        HttpsTrustManager.allowAllSSL();

        Call<JsonObject> call = retrofitClient.getMyApi().getFuelStation(id);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                JsonObject fuelStation = response.body();

                String stationId = String.valueOf(fuelStation.get("id"));
                String stationName = String.valueOf(fuelStation.get("name"));
                String stationAddress = fuelStation.get("address").getAsString();
                String permitNumber = fuelStation.get("permitNumber").getAsString();

                address.getEditText().setText(stationAddress);
                fsName.getEditText().setText(stationName);
                permitNo.getEditText().setText(permitNumber);

                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("stationId", stationId );
                editor.apply();

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                Log.i("response", String.valueOf(t));

            }
        });

        return 200;
    }

    private Integer updateFuelStation(String id, FuelStation fuelStation){
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        HttpsTrustManager.allowAllSSL();

        Call<JsonObject> call = retrofitClient.getMyApi().updateFuelStation(id, fuelStation);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                JsonObject fuelStation = response.body();

                String stationName = String.valueOf(fuelStation.get("Id"));
                String stationAddress = fuelStation.get("address").getAsString();
                String permitNumber = fuelStation.get("permitNumber").getAsString();

                address.getEditText().setText(stationAddress);
                fsName.getEditText().setText(stationName);
                permitNo.getEditText().setText(permitNumber);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                Log.i("response", String.valueOf(t));

            }
        });

        return 200;
    }


    private Integer updateFuelInventory(String id, FuelInventory fuelInventory){
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        HttpsTrustManager.allowAllSSL();

        Call<JsonObject> call = retrofitClient.getMyApi().updateFuelInventory(id, fuelInventory);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                JsonObject fuelStation = response.body();

                String stationName = String.valueOf(fuelStation.get("Id"));
                String stationAddress = fuelStation.get("address").getAsString();
                String permitNumber = fuelStation.get("permitNumber").getAsString();

                address.getEditText().setText(stationAddress);
                fsName.getEditText().setText(stationName);
                permitNo.getEditText().setText(permitNumber);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                Log.i("response", String.valueOf(t));

            }
        });

        return 200;
    }
}