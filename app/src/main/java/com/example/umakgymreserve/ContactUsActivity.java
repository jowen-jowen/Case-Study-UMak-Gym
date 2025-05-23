package com.example.umakgymreserve;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ContactUsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        Button btnBack = findViewById(R.id.btnBackToMain);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(ContactUsActivity.this, ReservationPage.class);
            startActivity(intent);
            finish(); // optional: finishes current activity so it doesn't stay in back stack
        });

    }
}