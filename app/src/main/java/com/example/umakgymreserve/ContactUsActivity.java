package com.example.umakgymreserve;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class ContactUsActivity extends AppCompatActivity {
    Button btnHome;
    String firstNameExport = getIntent().getStringExtra("firstName");
    String registerExport = getIntent().getStringExtra("typeRegister");
    String userId = getIntent().getStringExtra("user_id");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(ContactUsActivity.this, ReservationPage.class);
                intent.putExtra("firstName", firstNameExport);
                intent.putExtra("typeRegister", registerExport);
                intent.putExtra("user_id", userId);
                finish();
            }
        });

        btnHome = findViewById(R.id.btnBackHomeContact);
        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(ContactUsActivity.this, ReservationPage.class);
            intent.putExtra("firstName", firstNameExport);
            intent.putExtra("typeRegister", registerExport);
            intent.putExtra("user_id", userId);
            startActivity(intent);
            finish();
        });

    }
}