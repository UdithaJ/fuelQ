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

import com.example.client.databinding.FsoSignUpPageBinding;
import com.example.client.databinding.LoginPageBinding;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends Fragment {

private LoginPageBinding binding;
    TextInputLayout nic, password;
    Button login;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = LoginPageBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        nic = view.findViewById(R.id.nic_field);
        password = view.findViewById(R.id.password_field);
        login = view.findViewById(R.id.login_button);

        //nic.getEditText().setText("9878676786V");


        return view;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nicNumber =  nic.getEditText().getText().toString();
                String pass =  password.getEditText().getText().toString();

                Integer responseCode  = login(nicNumber, pass);

                if (responseCode == 200){
                    NavHostFragment.findNavController(LoginActivity.this)
                            .navigate(R.id.login_to_fs_page);

                    SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("userId", "user123");
                    editor.apply();

                    SharedPreferences reader = getActivity().getPreferences(Context.MODE_PRIVATE);
                    String userId = reader.getString("userId", "undefined");

                    Log.i("Reading from storage", userId);

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

    private Integer login(String nic, String password){
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        User user = new User(nic, password);
        HttpsTrustManager.allowAllSSL();

        JSONObject newLogging = new JSONObject();  //if needed
        try {
            newLogging.put("nic", nic);
            newLogging.put("password", password);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Call<User> call = retrofitClient.getMyApi().login(user);
        Log.i("payload", user.getNic());

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                User responseFromAPI = response.body();
                String responseString = "Response Code : " + response.code();
                Log.i("response", responseString);

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Log.i("response", String.valueOf(t));

            }
        });

        return 200;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}