package com.example.umakgymreserve;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class WorkoutsActivity extends AppCompatActivity {

    // Declare buttons
    private Button btnChest, btnBiceps, btnLegs, btnBack, btnBackToMain;
    private String firstNameExport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);

        firstNameExport = getIntent().getStringExtra("firstName");

        btnChest = findViewById(R.id.btnchest);
        btnBiceps = findViewById(R.id.btnbiceps);
        btnLegs = findViewById(R.id.btnlegs);
        btnBack = findViewById(R.id.btnback);
        btnBackToMain = findViewById(R.id.btnBackHomeWorkout);

        // Set click listeners
        setupButtonClickListeners();
    }


    private void setupButtonClickListeners() {
        btnChest.setOnClickListener(v->{
            Intent intent = new Intent(WorkoutsActivity.this, ChestActivity.class);
            intent.putExtra("firstName", firstNameExport);
            startActivity(intent);
            finish();
        });

        btnBiceps.setOnClickListener(v->{
            Intent intent = new Intent(WorkoutsActivity.this, BicepsActivity.class);
            intent.putExtra("firstName", firstNameExport);
            startActivity(intent);
            finish();
        });

        btnLegs.setOnClickListener(v->{
            Intent intent = new Intent(WorkoutsActivity.this, LegsActivity.class);
            intent.putExtra("firstName", firstNameExport);
            startActivity(intent);
            finish();
        });

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(WorkoutsActivity.this, BackActivity.class);
            intent.putExtra("firstName", firstNameExport);
            startActivity(intent);
            finish();
        });

        btnBackToMain.setOnClickListener(v->{
            Intent intent = new Intent(WorkoutsActivity.this, ReservationPage.class);
            intent.putExtra("firstName", firstNameExport);
            startActivity(intent);
            finish();
        });
    }

}
