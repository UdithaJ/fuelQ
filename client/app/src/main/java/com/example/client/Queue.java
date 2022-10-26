package com.example.client;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.client.databinding.ActivityQueueBinding;
import com.google.android.material.textfield.TextInputLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Queue extends Fragment {

    private Config config;
    private ActivityQueueBinding binding;

    String[] fuelTypes = {"Petrol", "Diesel"};
    String[] vehicleTypes = {"Bike", "Car", "ThreeWheels", "Bus"};
    AutoCompleteTextView fuelType;
    AutoCompleteTextView vehicleType;
    ArrayAdapter<String> fuelTypeAdapterItems;
    ArrayAdapter<String> vehicleTypeAdapterItems;
    TextInputLayout amount;
    Button join_queue, exit_before, exit_after;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

//        View view =  inflater.inflate(R.layout.driver_sign_up_page, container, false);
        binding = ActivityQueueBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        fuelType = view.findViewById(R.id.fuel_type);
        vehicleType = view.findViewById(R.id.vehicle_type);
        amount = view.findViewById(R.id.amount);
        join_queue = view.findViewById(R.id.join_queue);
        exit_before = view.findViewById(R.id.exit_before);
        exit_after = view.findViewById(R.id.exit_after);


        fuelTypeAdapterItems = new ArrayAdapter<String>(getActivity(),R.layout.fuel_type_list,fuelTypes);
        vehicleTypeAdapterItems = new ArrayAdapter<String>(getActivity(),R.layout.vehicle_type_list,vehicleTypes);

        fuelType.setAdapter(fuelTypeAdapterItems);
        vehicleType.setAdapter(vehicleTypeAdapterItems);

        return view;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.joinQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fType =  fuelType.getText().toString();
                String vType =  vehicleType.getText().toString();
                String amt1 =  amount.getEditText().getText().toString();
                int amt = Integer.parseInt(amt1);

                String fuelId = "";
                String vTypeId = "";

                if (fType.equals("Petrol")){
                    fuelId = Config.petrolId;
                }
                else if (fType.equals("Diesel")){
                    fuelId = Config.dieselId;
                }

                if (vType.equals("Bike")){
                    vTypeId = Config.bike;
                }
                else if (vType.equals("Car")){
                    vTypeId = Config.car;
                }
                else if (vType.equals("ThreeWheels")){
                    vTypeId = Config.threeWheel;
                }
                else if (vType.equals("Bus")){
                    vTypeId = Config.bus;
                }

                Integer responseCode   = joinQueue(vTypeId, fuelId, amt);

                if (responseCode == 200){
                    NavHostFragment.findNavController(Queue.this)
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

    private Integer joinQueue(String vType, String fType, Integer amount){
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        QueueClass queue = new QueueClass("", fType, vType, amount);
        HttpsTrustManager.allowAllSSL();

        Log.i("Reading from storage", queue.getFuelType());

        Call<QueueClass> call = retrofitClient.getMyApi().joinQueue(queue);
//        Log.i("payload", queue.getNic());

        call.enqueue(new Callback<QueueClass>() {
            @Override
            public void onResponse(Call<QueueClass> call, Response<QueueClass> response) {

                QueueClass responseFromAPI = response.body();
                String responseString = "Response Code : " + response.code();
                Log.i("response", responseString);

            }

            @Override
            public void onFailure(Call<QueueClass> call, Throwable t) {
                Log.i("response", String.valueOf(t));
            }
        });

        return 200;
    }


}