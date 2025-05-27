package com.example.umakgymreserve;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AnnouncementActivity extends AppCompatActivity {
    TextView announcement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);

        Button btnBack = findViewById(R.id.btnBackHomeAnnouncement);
        announcement = findViewById(R.id.tvAnnouncement);

        announcement.setText("");
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(AnnouncementActivity.this, ReservationPage.class);
            String firstNameExport = getIntent().getStringExtra("firstName");
            String registerExport = getIntent().getStringExtra("typeRegister");
            intent.putExtra("firstName", firstNameExport);
            intent.putExtra("typeRegister", registerExport);
            startActivity(intent);
            finish();
        });
    }
}