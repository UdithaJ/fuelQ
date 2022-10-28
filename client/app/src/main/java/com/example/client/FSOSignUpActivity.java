package com.example.client;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.client.databinding.FsoSignUpPageBinding;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FSOSignUpActivity extends Fragment {


    private @NonNull FsoSignUpPageBinding binding;
    TextInputLayout nic, username, permitNo, password, fsName, fsAddress;
    Button signUp;


    public FSOSignUpActivity() {

    }


//    public static FSOSignUpActivity newInstance(String param1, String param2) {
//        FSOSignUpActivity fragment = new FSOSignUpActivity();
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
        binding = FsoSignUpPageBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        nic = view.findViewById(R.id.nic_field);
        username =  view.findViewById(R.id.username_field);
        permitNo = view.findViewById(R.id.permit_field);
        fsName = view.findViewById(R.id.fs_name_field);
        fsAddress = view.findViewById(R.id.address_field);
        password = view.findViewById(R.id.password_field);
        signUp = view.findViewById(R.id.fso_sign_up_button);


        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.fsoSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nicNumber =  nic.getEditText().getText().toString();
                String uname = username.getEditText().getText().toString();
                String permitNumber =  permitNo.getEditText().getText().toString();
                String name =  fsName.getEditText().getText().toString();
                String address = fsAddress.getEditText().getText().toString();
                String pass =  password.getEditText().getText().toString();

                if ( nicNumber.equals("")) {
                    nic.setError("Please Insert your NIC Number");
                }
                if ( name.equals("")) {
                    username.setError("Please Insert a Fuel Station name");
                }
                if ( pass.equals("")) {
                    password.setError("Please Insert your Password");
                }
                if ( permitNumber.equals("")) {
                    permitNo.setError("Please Insert your permit Number");
                }
                if ( uname.equals("")) {
                    username.setError("Please Insert a username");
                }
                if ( address.equals("")) {
                    fsAddress.setError("Please Insert station address");
                }


                if(!nicNumber.equals("") && name.equals("") && pass.equals("") && permitNumber.equals("") && uname.equals("") && address.equals("") ) {
                    registerFuelStation(nicNumber, uname, permitNumber, name, address, pass);
                }

            }

        });
    }

    //Method to register fuel station
    private void registerFuelStation(String nic, String username, String permitNumber,String name, String address, String password){
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        FuelStation fuelStation = new FuelStation("", username, nic, name, permitNumber, address, password);
        HttpsTrustManager.allowAllSSL();

        Call<FuelStation> call = retrofitClient.getMyApi().registerFuelStation(fuelStation);
        Log.i("payload", fuelStation.getNic());

        call.enqueue(new Callback<FuelStation>() {
            @Override
            public void onResponse(Call<FuelStation> call, Response<FuelStation> response) {

                FuelStation responseFromAPI = response.body();

                    NavHostFragment.findNavController(FSOSignUpActivity.this)
                            .navigate(R.id.fso_sign_up_to_login);

            }

            @Override
            public void onFailure(Call<FuelStation> call, Throwable t) {

                Log.i("response", String.valueOf(t));

            }
        });
    }

}