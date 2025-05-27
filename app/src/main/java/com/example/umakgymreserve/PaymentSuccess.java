package com.example.umakgymreserve;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PaymentSuccess extends AppCompatActivity {
    TextView summaryEmail, summaryContact, summaryReference, summaryName;
    Button btnBackToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);


        String linkId = getIntent().getStringExtra("link_id");

        TextView textView = findViewById(R.id.textViewSuccess);
        textView.setText("Payment successful!\n Here is your Receipt");

        summaryEmail = findViewById(R.id.summaryEmail);
        summaryContact = findViewById(R.id.summaryContact);
        summaryReference = findViewById(R.id.summaryReference);
        summaryName = findViewById(R.id.summaryName);
        btnBackToHome = findViewById(R.id.btnBackToHome);

        String email = getIntent().getStringExtra("email");
        String contact = getIntent().getStringExtra("contact");
        String reference = getIntent().getStringExtra("reference");
        String name = getIntent().getStringExtra("name");


        summaryEmail.setText("Email: " + (email != null ? email : "N/A"));
            summaryContact.setText("Contact Number: " + (contact != null ? contact : "N/A"));
            summaryReference.setText("Reference Number: " + (reference != null ? reference : "N/A"));
            summaryName.setText("Name: " + (name != null ? name : "N/A"));


        btnBackToHome.setOnClickListener(v -> {
            Intent intent = new Intent(PaymentSuccess.this, ReservationPage.class);
            String firstNameExport = getIntent().getStringExtra("firstName");
            String registerExport = getIntent().getStringExtra("typeRegister");
            intent.putExtra("firstName", firstNameExport);
            intent.putExtra("typeRegister", registerExport);
            startActivity(intent);
            finish();
        });
    }
}
