package com.example.umakgymreserve;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
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
        CardView sessionBookingCard = findViewById(R.id.cView4);
        
        firstName = findViewById(R.id.fetchFirstName);

        String firstNameExport = getIntent().getStringExtra("firstName");
        firstName.setText(String.format("WELCOME %s!", firstNameExport).toUpperCase());
        
        sessionBookingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReservationPage.this, SessionBookingActivity.class);
                intent.putExtra("firstName", firstNameExport);
                startActivity(intent);
                finish();
            }
        });

        // Workouts
        CardView workoutsCard = findViewById(R.id.cView1);
        workoutsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReservationPage.this, WorkoutsActivity.class);
                intent.putExtra("firstName", firstNameExport);
                startActivity(intent);
                finish();
            }
        });

        // Contact Us
        CardView contactUsCard = findViewById(R.id.cView3);
        contactUsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReservationPage.this, ContactUsActivity.class);
                intent.putExtra("firstName", firstNameExport);
                startActivity(intent);
                finish();
            }
        });

        // Announcement
        CardView announcementCard = findViewById(R.id.cView2);
        announcementCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReservationPage.this, AnnouncementActivity.class);
                intent.putExtra("firstName", firstNameExport);
                startActivity(intent);
                finish();
            }
        });
    }
}