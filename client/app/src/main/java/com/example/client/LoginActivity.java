package com.example.client;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.client.databinding.LoginPageBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends Fragment {

private LoginPageBinding binding;
    String userId = null;
    String userType = null;
    TextInputLayout nic, password;
    Button login;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        SharedPreferences reader = getActivity().getPreferences(Context.MODE_PRIVATE);
        userId = reader.getString("userId", "undefined");
        userType = reader.getString("userType", "undefined");

        //Redirect if already logged in
        if(userId != null && userType != null){
            if ("Admin".equals(userType)){
                NavHostFragment.findNavController(LoginActivity.this)
                        .navigate(R.id.login_to_admin);
            }
            else if ("Driver".equals(userType)){
                NavHostFragment.findNavController(LoginActivity.this)
                        .navigate(R.id.login_to_admin);
            }
            else if ("Station Owner".equals(userType)){
                NavHostFragment.findNavController(LoginActivity.this)
                        .navigate(R.id.login_to_fs_page);
            }
        }

        binding = LoginPageBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        nic = view.findViewById(R.id.nic_field);
        password = view.findViewById(R.id.password_field);
        login = view.findViewById(R.id.login_button);

        return view;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nicNumber =  nic.getEditText().getText().toString();
                String pass =  password.getEditText().getText().toString();

                if ( nic.getEditText().getText().toString().equals("")) {
                    nic.setError("Please Insert your NIC Number");
                }
                if ( password.getEditText().getText().toString().equals("")) {
                    password.setError("Please Insert your Password");
                }

                if(!nicNumber.matches("^[0-9]{9}[vVxX]$")){
                    nic.setError("Invalid NIC Number!");
                }


                if( !nicNumber.equals("") && !pass.equals("") && nicNumber.matches("^[0-9]{9}[vVxX]$")){
                    login(nicNumber, pass);
                    SharedPreferences reader = getActivity().getPreferences(Context.MODE_PRIVATE);
                }
            }
        });

        binding.driverRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(LoginActivity.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        binding.fsoRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(LoginActivity.this)
                        .navigate(R.id.login_to_fso_sign_up);

            }

        });
    }

    //User login method
    private void login(String nicNumber, String password){

        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        User user = new User("", nicNumber, password, "", "");
        HttpsTrustManager.allowAllSSL();
        Call<JsonObject> call = retrofitClient.getMyApi().login(user);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();

                JsonObject object = response.body();
                JsonObject userObj = (JsonObject) object.get("user");

                String userId = String.valueOf(userObj.get("Id"));
                String userType = userObj.get("UserType").getAsString();

                //redirect by user type
                if ("Admin".equals(userType)){
                    NavHostFragment.findNavController(LoginActivity.this)
                            .navigate(R.id.login_to_admin);
                }
                else if ("Driver".equals(userType)){
                    NavHostFragment.findNavController(LoginActivity.this)
                            .navigate(R.id.login_to_station);
                }
                else if ("Station Owner".equals(userType)){
                    JsonObject stationObj = (JsonObject) object.get("fuelStation");
                    editor.putString("stationId",  String.valueOf(stationObj.get("Id")));
                    NavHostFragment.findNavController(LoginActivity.this)
                            .navigate(R.id.login_to_fs_page);
                }

                Snackbar.make(getActivity().findViewById(android.R.id.content),
                        "Logged In", Snackbar.LENGTH_LONG).show();

                editor.putString("userId", userId);
                editor.putString("userType", userType);
                editor.apply();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                Log.i("response", String.valueOf(t));

            }
        });

    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}