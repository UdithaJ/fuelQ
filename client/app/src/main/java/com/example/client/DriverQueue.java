package com.example.client;

import android.os.Bundle;

import com.example.client.databinding.DriverSignUpPageBinding;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import com.example.client.databinding.ActivityDriverQueueBinding;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class DriverQueue extends Fragment {

    private Config config;

    private ActivityDriverQueueBinding binding;

    TextInputLayout station_name;
    Button find_station_btn;

    private AppBarConfiguration appBarConfiguration;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        //View view =  inflater.inflate(R.layout.driver_sign_up_page, container, false);
        binding = ActivityDriverQueueBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        station_name = view.findViewById(R.id.name_field);

        return view;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.findStationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name =  station_name.getEditText().getText().toString();

                Integer responseCode   = searchStation(name);

                if (responseCode == 200){
                    NavHostFragment.findNavController(DriverQueue.this)
                            .navigate(R.id.driver_sign_up_to_FirstFragment);

                    SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("userId", "user123");
                    editor.apply();

                    SharedPreferences reader = getActivity().getPreferences(Context.MODE_PRIVATE);
                    String userId = reader.getString("userId", "undefined");


                }

            }

        });
    }

    private Integer searchStation(String name){
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        HttpsTrustManager.allowAllSSL();

        Call<JsonObject> call = retrofitClient.getMyApi().searchStationByName(name);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                JsonObject station = response.body();

//                JsonObject car = (JsonObject) vehicleTypes.get(0);
//                JsonObject bus = (JsonObject) vehicleTypes.get(1);
//                JsonObject bike = (JsonObject) vehicleTypes.get(2);
//                JsonObject threeWheel = (JsonObject) vehicleTypes.get(3);
//
//                bikeAmount.getEditText().setText(bike.get("maxAmmount").getAsString());
//                carAmount.getEditText().setText(car.get("maxAmmount").getAsString());
//                busAmount.getEditText().setText(bus.get("maxAmmount").getAsString());
//                threeWheelAmount.getEditText().setText(threeWheel.get("maxAmmount").getAsString());

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                Log.i("response", String.valueOf(t));

            }
        });

        return 200;

    }


}