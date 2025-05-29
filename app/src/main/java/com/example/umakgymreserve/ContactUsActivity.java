package com.example.umakgymreserve;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class ContactUsActivity extends AppCompatActivity {
    Button btnHome;
    private String firstNameExport;
    private String registerExport;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        Intent fetch = getIntent();
        firstNameExport = fetch.getStringExtra("firstName");
        registerExport = fetch.getStringExtra("typeRegister");
        userId = fetch.getStringExtra("user_id");

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