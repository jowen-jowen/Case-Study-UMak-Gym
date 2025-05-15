package com.example.umakgymreserve;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class BicepsActivity extends AppCompatActivity {

    ListView listViewBiceps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biceps); // Make sure you have activity_biceps.xml

        listViewBiceps = findViewById(R.id.listViewBiceps); // Make sure your ListView in activity_biceps.xml has this ID

        String[] bicepsExercises = {
                "Barbell Bicep Curls",
                "Dumbbell Bicep Curls",
                "Hammer Curls",
                "Concentration Curls",
                "Incline Dumbbell Press", // Assuming this was a typo and meant Incline Dumbbell Curls
                "Preacher Curls",
                "Cable Bicep Curls",
                "Reverse Curls",
                "Chin-Ups (Close Grip, Palms Facing You)",
                "Zottman Curls"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                bicepsExercises
        );

        listViewBiceps.setAdapter(adapter);
    }
}