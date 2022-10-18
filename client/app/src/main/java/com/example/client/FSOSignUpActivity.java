package com.example.client;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.client.databinding.DriverSignUpPageBinding;
import com.example.client.databinding.FsoSignUpPageBinding;
import com.example.client.databinding.FuelStationPageBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FSOSignUpActivity#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FSOSignUpActivity extends Fragment {


    private @NonNull FsoSignUpPageBinding binding;


    public FSOSignUpActivity() {
        // Required empty public constructor
    }


    public static FSOSignUpActivity newInstance(String param1, String param2) {
        FSOSignUpActivity fragment = new FSOSignUpActivity();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FsoSignUpPageBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.fsoSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FSOSignUpActivity.this)
                        .navigate(R.id.fso_sign_up_to_fs);

            }

        });
    }

}