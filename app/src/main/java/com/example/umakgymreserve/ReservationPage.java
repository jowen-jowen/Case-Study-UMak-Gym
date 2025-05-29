package com.example.umakgymreserve;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ReservationPage extends AppCompatActivity {
    TextView firstName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reservation_page);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                new AlertDialog.Builder(ReservationPage.this)
                        .setTitle("Warning")
                        .setMessage("Are you sure you want to go back?\nBacking out will meant for Log Out")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            Intent intent = new Intent(ReservationPage.this, LogReg.class);
                            startActivity(intent);
                            finish();
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(ReservationPage.this, LogReg.class);
                startActivity(intent);
                finish();
            }
        });

        CardView workoutsCard = findViewById(R.id.cView1);
        CardView announcementCard = findViewById(R.id.cView2);
        CardView contactUsCard = findViewById(R.id.cView3);
        CardView sessionBookingCard = findViewById(R.id.cView4);

        firstName = findViewById(R.id.fetchFirstName);

        String firstNameExport = getIntent().getStringExtra("firstName");
        String registerExport = getIntent().getStringExtra("typeRegister");
        String userId = getIntent().getStringExtra("user_id");
        firstName.setText(String.format("WELCOME %s!", firstNameExport).toUpperCase());


        sessionBookingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReservationPage.this, SessionBookingActivity.class);
                intent.putExtra("firstName", firstNameExport);
                intent.putExtra("typeRegister", registerExport);
                intent.putExtra("user_id", userId);
                startActivity(intent);
                finish();
            }
        });

        // Workouts
        workoutsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReservationPage.this, WorkoutsActivity.class);
                intent.putExtra("firstName", firstNameExport);
                intent.putExtra("typeRegister", registerExport);
                intent.putExtra("user_id", userId);
                startActivity(intent);
                finish();
            }
        });

        // Contact Us
        contactUsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReservationPage.this, ContactUsActivity.class);
                intent.putExtra("firstName", firstNameExport);
                intent.putExtra("typeRegister", registerExport);
                intent.putExtra("user_id", userId);
                startActivity(intent);
                finish();
            }
        });

        // Announcement
        announcementCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReservationPage.this, AnnouncementActivity.class);
                intent.putExtra("firstName", firstNameExport);
                intent.putExtra("typeRegister", registerExport);
                intent.putExtra("user_id", userId);
                startActivity(intent);
                finish();
            }
        });
    }
}