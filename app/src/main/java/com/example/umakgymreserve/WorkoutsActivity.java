package com.example.umakgymreserve;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class WorkoutsActivity extends AppCompatActivity {

    // Declare buttons
    private Button btnChest, btnBiceps, btnLegs, btnBack, btnBackToMain;

    private String firstNameExport, registerExport, userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);

        // Get data from intent safely here
        firstNameExport = getIntent().getStringExtra("firstName");
        registerExport = getIntent().getStringExtra("typeRegister");
        userId = getIntent().getStringExtra("user_id");

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(WorkoutsActivity.this, ReservationPage.class);
                intent.putExtra("firstName", firstNameExport);
                intent.putExtra("typeRegister", registerExport);
                intent.putExtra("user_id", userId);
                startActivity(intent);
                finish();
            }
        });

        // Initialize buttons
        initializeViews();
        // Set click listeners
        setupButtonClickListeners();
    }

    private void initializeViews() {
        btnChest = findViewById(R.id.btnchest);
        btnBiceps = findViewById(R.id.btnbiceps);
        btnLegs = findViewById(R.id.btnlegs);
        btnBack = findViewById(R.id.btnback);
        btnBackToMain = findViewById(R.id.btnBackHomeWorkout);
    }

    private void setupButtonClickListeners() {
        btnChest.setOnClickListener(view -> {
            Intent intent = new Intent(WorkoutsActivity.this, ChestActivity.class);
            intent.putExtra("firstName", firstNameExport);
            intent.putExtra("typeRegister", registerExport);
            intent.putExtra("user_id", userId);
            startActivity(intent);
        });

        btnBiceps.setOnClickListener(view -> {
            Intent intent = new Intent(WorkoutsActivity.this, BicepsActivity.class);
            intent.putExtra("firstName", firstNameExport);
            intent.putExtra("typeRegister", registerExport);
            intent.putExtra("user_id", userId);
            startActivity(intent);
        });

        btnLegs.setOnClickListener(view -> {
            Intent intent = new Intent(WorkoutsActivity.this, LegsActivity.class);
            intent.putExtra("firstName", firstNameExport);
            intent.putExtra("typeRegister", registerExport);
            intent.putExtra("user_id", userId);
            startActivity(intent);
        });

        btnBack.setOnClickListener(view -> {
            Intent intent = new Intent(WorkoutsActivity.this, BackActivity.class);
            intent.putExtra("firstName", firstNameExport);
            intent.putExtra("typeRegister", registerExport);
            intent.putExtra("user_id", userId);
            startActivity(intent);
        });

        btnBackToMain.setOnClickListener(view -> {
            Intent intent = new Intent(WorkoutsActivity.this, ReservationPage.class);
            intent.putExtra("firstName", firstNameExport);
            intent.putExtra("typeRegister", registerExport);
            intent.putExtra("user_id", userId);
            startActivity(intent);
        });
    }
}
