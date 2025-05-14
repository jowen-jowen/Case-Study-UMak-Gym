package com.example.umakgymreserve;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

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


        Button btnProceed = findViewById(R.id.btnProceed);
        btnProceed.setOnClickListener(v -> {

            int day = datePicker.getDayOfMonth();
            int month = datePicker.getMonth(); // 0-indexed
            int year = datePicker.getYear();
            int hour = timePicker.getHour();
            int minute = timePicker.getMinute();

            // Convert to 12-hour format
            String amPm = (hour >= 12) ? "PM" : "AM";
            int hour12 = (hour % 12 == 0) ? 12 : hour % 12;


            String selectedDateTime = String.format("You selected:\n%02d/%02d/%04d at %02d:%02d %s", month + 1, day, year, hour12, minute, amPm);

            Toast.makeText(this, selectedDateTime, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SessionBookingActivity.this, Payment.class);
                startActivity(intent);
                finish();
        });


    }
}