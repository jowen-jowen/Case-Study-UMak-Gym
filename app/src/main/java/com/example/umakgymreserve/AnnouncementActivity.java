package com.example.umakgymreserve;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class AnnouncementActivity extends AppCompatActivity {
    TextView announcement;
    String firstNameExport = getIntent().getStringExtra("firstName");
    String registerExport = getIntent().getStringExtra("typeRegister");
    String userId = getIntent().getStringExtra("user_id");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(AnnouncementActivity.this, ReservationPage.class);
                intent.putExtra("firstName", firstNameExport);
                intent.putExtra("typeRegister", registerExport);
                intent.putExtra("user_id", userId);
                finish();
            }
        });

        Button btnBack = findViewById(R.id.btnBackHomeAnnouncement);
        announcement = findViewById(R.id.tvAnnouncement);

        announcement.setText("");
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(AnnouncementActivity.this, ReservationPage.class);
            intent.putExtra("firstName", firstNameExport);
            intent.putExtra("typeRegister", registerExport);
            intent.putExtra("user_id", userId);
            startActivity(intent);
            finish();
        });
    }
}