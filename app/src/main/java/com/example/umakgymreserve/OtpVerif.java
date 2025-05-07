package com.example.umakgymreserve;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class OtpVerif extends AppCompatActivity {
    Button sendOtp, confirmOtp;
    EditText typeOtp;
    TextView umakEmailDisplay;
    AlertDialog dialog;
    String otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_otp_verif);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sendOtp = findViewById(R.id.btnSentOtp);
        confirmOtp = findViewById(R.id.btnConfirmOtp);
        typeOtp = findViewById(R.id.etTypeOtp);
        umakEmailDisplay = findViewById(R.id.tvUmakEmail);

        sendOtp.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#cfcf8c")));
        confirmOtp.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#cfcf8c")));
        sendOtp.setBackgroundResource(R.drawable.rounded_border_trans);
        confirmOtp.setBackgroundResource(R.drawable.rounded_border_trans);

        // Get the email from the intent
        Intent intent = getIntent();
        String emailFromIntent = intent.getStringExtra("myEmail");
        umakEmailDisplay.setText(emailFromIntent);

        otpSending();
    }

    public void otpSending() {
        sendOtp.setOnClickListener(v -> {
            new Handler().postDelayed(() -> {
                showProgress();
                //pang generate ng OTP(offline nga lng, galing sa dating code eh)
                int otpCode = (int) (Math.random() * 900000) + 100000;
                otp = String.valueOf(otpCode);

                typeOtp.setFocusable(true);
                Toast.makeText(getApplicationContext(), "Your OTP: " + otpCode, Toast.LENGTH_SHORT).show();
                typeOtp.setText(otp);
                dismissProgress();

                //Optional lng to kase yung gamit kong emulator is walang clipboard
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                if (clipboardManager != null) {
                    ClipData clip = ClipData.newPlainText("OTP", otp);
                    clipboardManager.setPrimaryClip(clip);
                } else {
                    Toast.makeText(getApplicationContext(), "Clipboard not available", Toast.LENGTH_SHORT).show();
                }
            }, 2000); // two-second delay
        });

        confirmOtp.setOnClickListener(b -> {
            //compare the entered OTP with the generated OTP
            String enteredOtp = typeOtp.getText().toString().trim();

            //pass the data again
            Intent fetchData = getIntent();
            String firstName = fetchData.getStringExtra("firstName");
            String lastName = fetchData.getStringExtra("lastName");
            String umakEmail = fetchData.getStringExtra("myEmail");
            String typeRegister = fetchData.getStringExtra("typeRegister");

            if (enteredOtp.equals(otp)) {
                Toast.makeText(getApplicationContext(), "OTP MATCHES", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, CreateAccount.class);
                intent.putExtra("firstName", firstName);
                intent.putExtra("lastName", lastName);
                intent.putExtra("myEmail", umakEmail);
                intent.putExtra("typeRegister", typeRegister);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Invalid OTP", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showProgress() {
        ProgressBar progressBar = new ProgressBar(this);
        progressBar.setIndeterminate(true);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Generating OTP").setView(progressBar).setCancelable(false);

        dialog = builder.create();
        dialog.show();
    }

    private void dismissProgress() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
