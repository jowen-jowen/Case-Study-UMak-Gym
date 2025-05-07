package com.example.umakgymreserve;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ReservationPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reservation_page);
        CardView sessionBookingCard = findViewById(R.id.cView4);
        sessionBookingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReservationPage.this, SessionBookingActivity.class));
            }
        });

        // Workouts
        CardView workoutsCard = findViewById(R.id.cView1);
        workoutsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReservationPage.this, WorkoutsActivity.class));
            }
        });

        // Contact Us
        CardView contactUsCard = findViewById(R.id.cView3);
        contactUsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReservationPage.this, ContactUsActivity.class));
            }
        });

        // Announcement
        CardView announcementCard = findViewById(R.id.cView2);
        announcementCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReservationPage.this, AnnouncementActivity.class));
            }
        });
    }
}