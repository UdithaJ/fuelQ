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
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonArray;
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
    Integer minAmount = 0;
    Integer maxAmount = 6000;
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

        SharedPreferences reader = getActivity().getPreferences(Context.MODE_PRIVATE);
        stationId = reader.getString("stationId", "undefined");

        petrolAmount.getEditText().addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                String editPetrolAmount =  petrolAmount.getEditText().getText().toString();
                ViewGroup.LayoutParams lp = petrolBar.getLayoutParams();
                lp.width = 150;
                lp.height = 0;
                if(editPetrolAmount.equals("")){
                    //petrolAmount.getEditText().setText(minAmount.toString());
                }
                else{
                    Integer amount = Integer.valueOf(editPetrolAmount);

                    if (amount > maxAmount ){
                        lp.height = maxAmount/10;
                        petrolAmount.getEditText().setText(maxAmount.toString());
                    }
                    else{
                        lp.height = amount/10;
                    }

                }
                petrolBar.requestLayout();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        dieselAmount.getEditText().addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                String editDieselAmount =  dieselAmount.getEditText().getText().toString();
                ViewGroup.LayoutParams lp = dieselBar.getLayoutParams();
                lp.width = 150;
                lp.height = minAmount/100;
                if(editDieselAmount.equals("") || Integer.valueOf(editDieselAmount) < minAmount){
                    //dieselAmount.getEditText().setText(minAmount.toString());
                }
                else{
                    Integer amount = Integer.valueOf(editDieselAmount);

                    if (amount > maxAmount ){
                        lp.height = maxAmount/10;
                        dieselAmount.getEditText().setText(maxAmount.toString());
                    }
                    else{
                        lp.height = amount/10;
                    }

                }
                dieselBar.requestLayout();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getFuelStation(stationId.replaceAll("^\"|\"$", ""));
        getFuelInventory(stationId.replaceAll("^\"|\"$", ""));

        ViewGroup.LayoutParams lp = petrolBar.getLayoutParams();
        lp.width = 150;
        lp.height = 500;
        petrolBar.requestLayout();

        ViewGroup.LayoutParams lp2 = petrolBar.getLayoutParams();
        lp2.width = 150;
        lp2.height = 300;
        petrolBar.requestLayout();

        binding.updateFsDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String editAddress =  address.getEditText().getText().toString();
                String editName =  fsName.getEditText().getText().toString();
                String editPermitNo =  permitNo.getEditText().getText().toString();

                FuelStation fuelStation = new FuelStation(stationId, "", "", editName, editPermitNo, editAddress,"");
                updateFuelStation(stationId.replaceAll("^\"|\"$", ""), fuelStation);
            }
        });

        binding.petrolAmountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String editPetrolAmount =  petrolAmount.getEditText().getText().toString();
                updateFuelInventory(stationId.replaceAll("^\"|\"$", ""), Config.petrolId, Integer.valueOf(editPetrolAmount) );
            }
        });

        binding.dieselAmountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String editDieselAmount =  dieselAmount.getEditText().getText().toString();
                updateFuelInventory(stationId.replaceAll("^\"|\"$", ""), Config.dieselId, Integer.valueOf(editDieselAmount) );
            }
        });

    }

    //Method to get fuel station details
    private Integer getFuelStation(String id){
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        HttpsTrustManager.allowAllSSL();

        Call<JsonObject> call = retrofitClient.getMyApi().getFuelStation(id);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                JsonObject fuelStation = response.body();

               // String stationId = String.valueOf(fuelStation.get("id"));
                String stationName = fuelStation.get("name").getAsString();
                String stationAddress = fuelStation.get("address").getAsString();
                String permitNumber = fuelStation.get("permitNumber").getAsString();

                address.getEditText().setText(stationAddress);
                fsName.getEditText().setText(stationName);
                permitNo.getEditText().setText(permitNumber);

//                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPref.edit();
//                editor.putString("stationId", stationId );
//                editor.apply();

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                Log.i("response", String.valueOf(t));

            }
        });

        return 200;
    }

    //Method to get fuel station fuel inventory details
    private Integer getFuelInventory(String id){
        Log.i("Get fuel inventory", id);
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        HttpsTrustManager.allowAllSSL();

        Call<JsonArray> call = retrofitClient.getMyApi().getFuelInventory(id);

        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {

                JsonArray fuelInventory = response.body();

                Log.i("res", String.valueOf(fuelInventory));

                JsonObject petrol = (JsonObject) fuelInventory.get(0);
                JsonObject diesel = (JsonObject) fuelInventory.get(1);

                String pAmount = petrol.get("CurrentCapacirt").getAsString();
                String dAmount =  diesel.get("CurrentCapacirt").getAsString();

                Log.i("petrol", pAmount);

                petrolAmount.getEditText().setText(pAmount);
                dieselAmount.getEditText().setText(dAmount);

            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {

                Log.i("response", String.valueOf(t));

            }
        });

        return 200;
    }

    //Method to update fuel station details
    private Integer updateFuelStation(String id, FuelStation fuelStation){
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        HttpsTrustManager.allowAllSSL();

        Call<JsonObject> call = retrofitClient.getMyApi().updateFuelStation(id, fuelStation);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

//                JsonObject fuelStation = response.body();
//
//                String stationName = String.valueOf(fuelStation.get("Id"));
//                String stationAddress = fuelStation.get("address").getAsString();
//                String permitNumber = fuelStation.get("permitNumber").getAsString();
//
//                address.getEditText().setText(stationAddress);
//                fsName.getEditText().setText(stationName);
//                permitNo.getEditText().setText(permitNumber);

                Snackbar.make(getActivity().findViewById(android.R.id.content),
                        "Station Details Updated", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                Log.i("response", String.valueOf(t));

            }
        });

        return 200;
    }

    //Method to update fuel inventory details
    private Integer updateFuelInventory(String id, String fuelTypeId, Integer amount){
        Log.i("fuel type id", fuelTypeId );
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        HttpsTrustManager.allowAllSSL();

        Call<JsonObject> call = retrofitClient.getMyApi().updateFuelInventory(id, fuelTypeId, amount);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {


                JsonObject inventory = response.body();
                JsonObject Status = (JsonObject) inventory.get("value");
                String statusVal = Status.get("status").getAsString();


                if(statusVal.equals("Success")) {
                    Snackbar.make(getActivity().findViewById(android.R.id.content),
                            "Fuel Amount  Updated", Snackbar.LENGTH_LONG).show();
                }
                else{
                    Snackbar.make(getActivity().findViewById(android.R.id.content),
                            "Something went wrong", Snackbar.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                Log.i("response", String.valueOf(t));

            }
        });

        return 200;
    }
}