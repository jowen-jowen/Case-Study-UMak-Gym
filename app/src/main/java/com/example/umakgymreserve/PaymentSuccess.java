package com.example.umakgymreserve;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class PaymentSuccess extends AppCompatActivity {

    private TextView summaryReference, summaryName, reservationDate, amountTextView, type;
    Button reserveAgain, logout;
    private double amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);

        summaryReference = findViewById(R.id.summaryReference);
        summaryName = findViewById(R.id.summaryName);
        reservationDate = findViewById(R.id.reservationDate);
        amountTextView = findViewById(R.id.tvAmount);
        type = findViewById(R.id.tvType);
        logout = findViewById(R.id.btnBook);
        reserveAgain = findViewById(R.id.btnBackToHome);

        String fName = getIntent().getStringExtra("firstName");
        String paymentCode = getIntent().getStringExtra("payment_code");
        String selectedDate = getIntent().getStringExtra("selectedDate");
        String userId = getIntent().getStringExtra("user_id");
        String registerExport = getIntent().getStringExtra("typeRegister");

        if (registerExport.equals("STUDENT")){
            amount = 30.00;
            amountTextView.setText(String.format("Amount: %s", amount));
        }

        if (registerExport.equals("EMPLOYEE")) {
            amount = 20.00;
            amountTextView.setText(String.format("Amount: %s", amount));
        }

        if(registerExport.equals("ALUMNI")){
            amount = 50.00;
            amountTextView.setText(String.format("Amount: %s", amount));
        }

        summaryName.setText(String.format("Name: %s", fName));
        summaryReference.setText(String.format("Reference Number: %s", paymentCode));
        reservationDate.setText(String.format("Reservation Date: %s", selectedDate));
        type.setText(String.format("Type: %s", registerExport));

        sendPaymentToServer(userId, paymentCode, selectedDate);

        logout.setOnClickListener(v -> {
            Intent intent = new Intent(PaymentSuccess.this, LogReg.class);
            startActivity(intent);
            finish();
        });

        reserveAgain.setOnClickListener(v -> {
            Intent intent = new Intent(PaymentSuccess.this, ReservationPage.class);
            intent.putExtra("firstName", fName);
            intent.putExtra("typeRegister", registerExport);
            intent.putExtra("user_id", userId);
            startActivity(intent);
            finish();
        });
    }

    private void sendPaymentToServer(String userId, String reference, String reservationDate) {
        String url = URLs.RECEIPT;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    Toast.makeText(PaymentSuccess.this, "Payment stored successfully!", Toast.LENGTH_SHORT).show();
                },
                error -> {
                    Toast.makeText(PaymentSuccess.this, "Error storing payment: " + error.getMessage(), Toast.LENGTH_LONG).show();
                }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", userId);
                params.put("reference", reference);
                params.put("amount", String.valueOf(amount));
                params.put("resDate", reservationDate); // Send as string, PHP will convert
                return params;
            }
        };

        // Add to request queue
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
}
