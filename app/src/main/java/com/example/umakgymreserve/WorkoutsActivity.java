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

public class WorkoutsActivity extends AppCompatActivity {

    Button btnChest, btnBiceps, btnLegs, btnBack, btnBackToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);


        btnChest = findViewById(R.id.btnchest);
        btnBiceps = findViewById(R.id.btnbiceps);
        btnLegs = findViewById(R.id.btnlegs);
        btnBack = findViewById(R.id.btnback);
        btnBackToMain = findViewById(R.id.btnBackToMain);


        btnChest.setOnClickListener(view -> {
            Intent intent = new Intent(WorkoutsActivity.this, ChestActivity.class);
            startActivity(intent);
        });

        btnBiceps.setOnClickListener(view -> {
            Intent intent = new Intent(WorkoutsActivity.this, BicepsActivity.class);
            startActivity(intent);
        });

        btnLegs.setOnClickListener(view -> {
            Intent intent = new Intent(WorkoutsActivity.this, LegsActivity.class);
            startActivity(intent);
        });

        btnBack.setOnClickListener(view -> {
            Intent intent = new Intent(WorkoutsActivity.this, BackActivity.class);
            startActivity(intent);
        });

        btnBackToMain.setOnClickListener(view -> {
            Intent intent = new Intent(WorkoutsActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
