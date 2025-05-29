package com.example.umakgymreserve;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class PaymentFailed extends AppCompatActivity {
    Button reserveAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment_failed);

        reserveAgain = findViewById(R.id.button);

        String fName = getIntent().getStringExtra("firstName");
        String userId = getIntent().getStringExtra("user_id");
        String registerExport = getIntent().getStringExtra("typeRegister");

        reserveAgain.setOnClickListener(v -> {
            Intent intent = new Intent(PaymentFailed.this, ReservationPage.class);
            intent.putExtra("firstName", fName);
            intent.putExtra("typeRegister", registerExport);
            intent.putExtra("user_id", userId);
            startActivity(intent);
            finish();
        });
    }
}
