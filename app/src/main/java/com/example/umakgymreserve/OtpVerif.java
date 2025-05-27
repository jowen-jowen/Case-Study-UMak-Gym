package com.example.umakgymreserve;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
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
    EditText typeOtp, email;
    String otp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_otp_verif);

        sendOtp = findViewById(R.id.btnSentOtp);
        confirmOtp = findViewById(R.id.btnConfirmOtp);
        typeOtp = findViewById(R.id.etTypeOtp);
        email = findViewById(R.id.etUmakEmail);

        sendOtp.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
        confirmOtp.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
        sendOtp.setBackgroundResource(R.drawable.rounded_border_trans);
        confirmOtp.setBackgroundResource(R.drawable.rounded_border_trans);

        String url = URLs.OTP_SEND;

        sendOtp.setOnClickListener(v -> {
            String umakEmail = email.getText().toString().trim();
            String umakEmailValidator = "^[a-zA-Z0-9._%+-]+@umak\\.edu\\.ph$";
            String injectionPattern = ".*[\"';=<>%*(){}\\[\\]--].*";

            if(umakEmail.isEmpty()){
                email.setError("Please input your University Email Account");
            }

            if (umakEmail.matches(umakEmailValidator)) {
                sendOtp.setEnabled(false);
                sendOtp.setClickable(false);
                sendOtp.setText("Sending...");
                Log.d("OtpVerif", "Send OTP clicked");

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        response -> {
                            try {
                                JSONObject object = new JSONObject(response);
                                boolean success = object.getBoolean("success");
                                if (success) {
                                    otp = object.getString("otp").trim();
                                    Toast.makeText(OtpVerif.this, "OTP has been sent to your email!", Toast.LENGTH_SHORT).show();
                                    sendOtp.setText("OTP Sent");
                                    typeOtp.setFocusable(true);
                                } else {
                                    String message = object.getString("message");
                                    Toast.makeText(OtpVerif.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                                    sendOtp.setText("Send OTP");
                                    sendOtp.setEnabled(true);
                                    sendOtp.setClickable(true);
                                }
                            } catch (JSONException e) {
                                Toast.makeText(OtpVerif.this, "Response parsing error", Toast.LENGTH_SHORT).show();
                                sendOtp.setText("Send OTP");
                                sendOtp.setEnabled(true);
                                sendOtp.setClickable(true);
                            }

                            // Re-enable the button after 30 seconds
                            new Handler().postDelayed(() -> {
                                sendOtp.setEnabled(true);
                                sendOtp.setClickable(true);
                                sendOtp.setText("Send OTP");
                            }, 30000);
                        },
                        error -> {
                            Toast.makeText(OtpVerif.this, "Volley Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                            sendOtp.setText("Send OTP");
                            sendOtp.setEnabled(true);
                            sendOtp.setClickable(true);
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("email", umakEmail);
                        return params;
                    }
                };


                stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                        0,
                        0,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                ));

                RequestQueue queue = Volley.newRequestQueue(this);
                queue.add(stringRequest);

            } else if (umakEmail.matches(injectionPattern)){
                Toast.makeText(this, "Invalid UMak Email or Contains Unsafe Characters", Toast.LENGTH_SHORT).show();
            }
        });

        confirmOtp.setOnClickListener(v -> {
            String enteredOtp = typeOtp.getText().toString().trim();
            String umakEmail = email.getText().toString().trim();
            Intent fetchData = getIntent();
            String typeRegister = fetchData.getStringExtra("typeRegister");

            if (enteredOtp.equals(otp)) {
                Toast.makeText(getApplicationContext(), "OTP MATCHES", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, SignUp.class);
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