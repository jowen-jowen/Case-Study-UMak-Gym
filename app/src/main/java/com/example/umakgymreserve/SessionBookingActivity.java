package com.example.umakgymreserve;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class SessionBookingActivity extends AppCompatActivity {

    DatePicker datePicker;
    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_booking);

        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(false);

        Calendar now = Calendar.getInstance();
        datePicker.setMinDate(now.getTimeInMillis());

        Button btnBack = findViewById(R.id.btnBackToMain);
        Button btnProceed = findViewById(R.id.btnProceed);

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(SessionBookingActivity.this, ReservationPage.class);
            startActivity(intent);
            finish();
        });

        btnProceed.setOnClickListener(v -> {
            int day = datePicker.getDayOfMonth();
            int month = datePicker.getMonth();
            int year = datePicker.getYear();
            int hour = timePicker.getHour();
            int minute = timePicker.getMinute();

            Calendar selected = Calendar.getInstance();
            selected.set(year, month, day, hour, minute, 0);
            selected.set(Calendar.MILLISECOND, 0);

            if (selected.before(now)) {
                new AlertDialog.Builder(this)
                        .setTitle("Invalid Time")
                        .setMessage("Please select a future time.")
                        .setPositiveButton("OK", null)
                        .show();
                return;
            }

            String amPm = (hour >= 12) ? "PM" : "AM";
            int hour12 = (hour % 12 == 0) ? 12 : hour % 12;

            String selectedDateTime = String.format(
                    "You selected:\n%02d/%02d/%04d at %02d:%02d %s", month + 1, day, year, hour12, minute, amPm
            );

            new AlertDialog.Builder(this)
                    .setTitle("Session Booked")
                    .setMessage(selectedDateTime)
                    .setCancelable(false)
                    .setPositiveButton("Back to Main", (dialog, which) -> {
                        Intent intent = new Intent(SessionBookingActivity.this, ReservationPage.class);
                        startActivity(intent);
                        finish();
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });
    }
}
