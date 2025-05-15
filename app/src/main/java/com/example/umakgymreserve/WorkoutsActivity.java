package com.example.umakgymreserve;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.umakgymreserve.BackActivity;
import com.example.umakgymreserve.BicepsActivity;
import com.example.umakgymreserve.ChestActivity;
import com.example.umakgymreserve.LegsActivity;
import com.example.umakgymreserve.MainActivity;

public class WorkoutActivity extends AppCompatActivity {

    Button btnChest, btnBiceps, btnLegs, btnBack, btnBackToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);

        // Connect buttons with their IDs
        btnChest = findViewById(R.id.btnchest);
        btnBiceps = findViewById(R.id.btnbiceps);
        btnLegs = findViewById(R.id.btnlegs);
        btnBack = findViewById(R.id.btnback);
        btnBackToMain = findViewById(R.id.btnBackToMain);

        // Example functionality for each button
        btnChest.setOnClickListener(view -> {
            Intent intent = new Intent(WorkoutActivity.this, ChestActivity.class);
            startActivity(intent);
        });

        btnBiceps.setOnClickListener(view -> {
            Intent intent = new Intent(WorkoutActivity.this, BicepsActivity.class);
            startActivity(intent);
        });

        btnLegs.setOnClickListener(view -> {
            Intent intent = new Intent(WorkoutActivity.this, LegsActivity.class);
            startActivity(intent);
        });

        btnBack.setOnClickListener(view -> {
            Intent intent = new Intent(WorkoutActivity.this, BackActivity.class);
            startActivity(intent);
        });

        btnBackToMain.setOnClickListener(view -> {
            Intent intent = new Intent(WorkoutActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
