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

    // Declare variables for intent extras
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
                startActivity(intent); // you missed this
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
        btnChest.setOnClickListener(view -> navigateToActivity(ChestActivity.class));
        btnBiceps.setOnClickListener(view -> navigateToActivity(BicepsActivity.class));
        btnLegs.setOnClickListener(view -> navigateToActivity(LegsActivity.class));
        btnBack.setOnClickListener(view -> navigateToActivity(BackActivity.class));
        btnBackToMain.setOnClickListener(view -> navigateToActivity(ReservationPage.class));
    }

    private void navigateToActivity(Class<?> activityClass) {
        Intent intent = new Intent(WorkoutsActivity.this, activityClass);
        intent.putExtra("firstName", firstNameExport);
        intent.putExtra("typeRegister", registerExport);
        intent.putExtra("user_id", userId);
        startActivity(intent);
    }
}
