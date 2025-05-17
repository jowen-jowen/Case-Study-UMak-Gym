package com.example.umakgymreserve;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class WorkoutsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);

        Button btnBack = findViewById(R.id.btnBackToMain);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(WorkoutsActivity.this, ReservationPage.class);
            String firstNameExport = getIntent().getStringExtra("firstName");
            intent.putExtra("firstName", firstNameExport);
            startActivity(intent);
            finish();
        });

    }
}