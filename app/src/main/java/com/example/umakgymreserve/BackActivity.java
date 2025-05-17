package com.example.umakgymreserve;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class BackActivity extends AppCompatActivity {

    ListView listViewBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back); // Make sure you have activity_back.xml

        listViewBack = findViewById(R.id.listViewBack); // Make sure your ListView in activity_back.xml has this ID

        String[] backExercises = {
                "Pull-Ups (Various Grips)",
                "Barbell Rows (Bent-Over Rows)",
                "Dumbbell Rows (Single-Arm Rows)",
                "Seated Cable Rows",
                "Lat Pulldowns (Various Grips)",
                "Face Pulls",
                "Deadlifts",
                "Back Extensions (Hyperextensions)",
                "T-Bar Rows",
                "Shrugs (Barbell or Dumbbell)"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                backExercises
        );

        listViewBack.setAdapter(adapter);
    }
}