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

        Button btnBack = findViewById(R.id.btnBackHomeAnnouncement);

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(AnnouncementActivity.this, ReservationPage.class);
            String firstNameExport = getIntent().getStringExtra("firstName");
            intent.putExtra("firstName", firstNameExport);
            startActivity(intent);
            finish();
        });
    }
}