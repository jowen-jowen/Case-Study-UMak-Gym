package com.example.umakgymreserve;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ContactUsActivity extends AppCompatActivity {
    Button btnHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        btnHome = findViewById(R.id.btnBackHomeContact);
        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(ContactUsActivity.this, ReservationPage.class);
            String firstNameExport = getIntent().getStringExtra("firstName");
            String registerExport = getIntent().getStringExtra("typeRegister");
            intent.putExtra("firstName", firstNameExport);
            intent.putExtra("typeRegister", registerExport);
            startActivity(intent);
            finish();
        });

    }
}