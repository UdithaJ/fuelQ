package com.example.client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.client.databinding.FuelStationPageBinding;
//import com.github.mikephil.charting.charts.BarChart;
//import com.github.mikephil.charting.data.BarData;
//import com.github.mikephil.charting.data.BarDataSet;
//import com.github.mikephil.charting.data.BarEntry;
//import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class FuelStationActivity extends Fragment {

    private @NonNull
    FuelStationPageBinding binding;


    public FuelStationActivity() {
        // Required empty public constructor
    }


    public static FuelStationActivity newInstance(String param1, String param2) {
        FuelStationActivity fragment = new FuelStationActivity();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        BarChart barChart = (BarChart) findViewById(R.id.barchart);
//
//        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
//        entries.add(new BarEntry(8f, 0));
//        entries.add(new BarEntry(2f, 1));
//
//        BarDataSet bardataset = new BarDataSet(entries, "Cells");
//
//        ArrayList<String> labels = new ArrayList<String>();
//        labels.add("2016");
//        labels.add("2015");
//
//        BarData data = new BarData(labels, bardataset);
//        barChart.setData(data); // set the data and list of labels into chart
//        barChart.setDescription("Set Bar Chart Description Here");  // set the description
//        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
//        barChart.animateY(5000);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FuelStationPageBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        binding.fsoSignUpButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(FuelStationActivity.this)
//                        .navigate(R.id.fso_sign_up_to_fs);
//
//            }
//
//        });
    }
}