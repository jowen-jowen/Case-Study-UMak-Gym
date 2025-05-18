package com.example.umakgymreserve;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OtpVerif extends AppCompatActivity {
    Button sendOtp, confirmOtp;
    EditText typeOtp;
    TextView umakEmailDisplay;
    String otp;
    boolean otpAlreadySent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_otp_verif);

        sendOtp = findViewById(R.id.btnSentOtp);
        confirmOtp = findViewById(R.id.btnConfirmOtp);
        typeOtp = findViewById(R.id.etTypeOtp);
        umakEmailDisplay = findViewById(R.id.tvUmakEmail);

        sendOtp.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
        confirmOtp.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
        sendOtp.setBackgroundResource(R.drawable.rounded_border_trans);
        confirmOtp.setBackgroundResource(R.drawable.rounded_border_trans);

        Intent intent = getIntent();
        String emailFromIntent = intent.getStringExtra("myEmail");
        umakEmailDisplay.setText(emailFromIntent);

        String urlOtp = "http://10.0.2.2/LogReg/otpSending.php";
        sendOtp.setOnClickListener(v -> {

            if (otpAlreadySent) {
                Toast.makeText(OtpVerif.this, "OTP already sent.", Toast.LENGTH_SHORT).show();
                return;
            }

            otpAlreadySent = true;
            sendOtp.setEnabled(false);
            sendOtp.setClickable(false);
            Toast.makeText(OtpVerif.this, "OTP has been sent to your email!", Toast.LENGTH_SHORT).show();
            Log.d("OtpVerif", "Send OTP clicked");

            StringRequest stringRequest = new StringRequest(Request.Method.POST, urlOtp,
                    response -> {
                        try {
                            JSONObject object = new JSONObject(response);
                            boolean success = object.getBoolean("success");
                            if (success) {
                                otp = object.getString("otp").trim();
                            } else {
                                String message = object.getString("message");
                                Toast.makeText(OtpVerif.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(OtpVerif.this, "Response parsing error", Toast.LENGTH_SHORT).show();
                        }
                    }, volleyError -> {
                Toast.makeText(OtpVerif.this, "Volley Error " + volleyError, Toast.LENGTH_SHORT).show();
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("email", emailFromIntent);
                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(stringRequest);
        });

        otpConfirmation();
    }

    private void otpConfirmation() {
        confirmOtp.setOnClickListener(v -> {
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
}
