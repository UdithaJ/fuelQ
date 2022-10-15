package com.example.client;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.client.databinding.DriverSignUpPageBinding;

public class DriverSignUpActivity extends Fragment {

private DriverSignUpPageBinding binding;

String[] fuelTypes = {"Petrol", "Diesel"};
String[] vehicleTypes = {"Bike", "Car/Van", "ThreeWheels", "Bus"};
AutoCompleteTextView fuelType;
AutoCompleteTextView vehicleType;
ArrayAdapter<String> fuelTypeAdapterItems;
ArrayAdapter<String> vehicleTypeAdapterItems;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        View view =  inflater.inflate(R.layout.driver_sign_up_page, container, false);

        fuelType = view.findViewById(R.id.fuel_type);
        vehicleType = view.findViewById(R.id.vehicle_type);

        fuelTypeAdapterItems = new ArrayAdapter<String>(getActivity(),R.layout.fuel_type_list,fuelTypes);
        vehicleTypeAdapterItems = new ArrayAdapter<String>(getActivity(),R.layout.vehicle_type_list,vehicleTypes);

        fuelType.setAdapter(fuelTypeAdapterItems);
        vehicleType.setAdapter(vehicleTypeAdapterItems);

        binding = DriverSignUpPageBinding.inflate(inflater, container, false);
        return view;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.driverSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(DriverSignUpActivity.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}