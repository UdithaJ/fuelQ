package com.example.client;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;


public class StationList extends Activity {

    ListView simpleList;
    String stationList[] = {"India", "China", "australia", "Portugle", "America", "NewZealand"};
    int images[] = {R.drawable.logo, R.drawable.logo, R.drawable.logo, R.drawable.logo, R.drawable.logo, R.drawable.logo};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simpleList = (ListView) findViewById(R.id.simpleListView);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), stationList, images);
        simpleList.setAdapter(customAdapter);
    }

}
