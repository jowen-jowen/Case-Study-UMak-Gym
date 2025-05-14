package com.example.umakgymreserve;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AnnouncementActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);

        Button btnBack = findViewById(R.id.btnBackHomePayment);

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(AnnouncementActivity.this, ReservationPage.class);
            startActivity(intent);
            finish(); // optional: finishes current activity so it doesn't stay in back stack
        });

    }
}