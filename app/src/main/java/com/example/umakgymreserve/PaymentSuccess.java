package com.example.umakgymreserve;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentSuccess extends AppCompatActivity {

    private TextView summaryReference, summaryName, reservationDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);

        summaryReference = findViewById(R.id.summaryReference);
        summaryName = findViewById(R.id.summaryName);
        reservationDate = findViewById(R.id.reservationDate);

        String fName = getIntent().getStringExtra("firstName");
        String paymentCode = getIntent().getStringExtra("payment_code");
        String selectedDate = getIntent().getStringExtra("selectedDate");

        summaryName.setText("Name: %s" + fName);
        summaryReference.setText(String.format("Reference Number: %s", paymentCode));
        reservationDate.setText(String.format("ReservationDate: %s", selectedDate));
    }
}
