package com.example.umakgymreserve;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class LegsActivity extends AppCompatActivity {

    ListView listViewLegs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legs); // Make sure you have activity_legs.xml

        listViewLegs = findViewById(R.id.listViewLegs); // Make sure your ListView in activity_legs.xml has this ID

        String[] legsExercises = {
                "Barbell Squats (High Bar & Low Bar)",
                "Leg Press",
                "Romanian Deadlifts (RDLs)",
                "Walking Lunges",
                "Leg Extensions",
                "Hamstring Curls (Lying or Seated)",
                "Calf Raises (Standing or Seated)",
                "Step-Ups",
                "Glute Bridges/Hip Thrusts (Barbell or Bodyweight)",
                "Goblet Squats"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                legsExercises
        );

        listViewLegs.setAdapter(adapter);
    }
}