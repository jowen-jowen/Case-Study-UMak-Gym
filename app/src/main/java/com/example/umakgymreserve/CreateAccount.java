package com.example.umakgymreserve;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class CreateAccount extends AppCompatActivity {
    Button confirmAcc;
    EditText createUser, createPass;
    String url = URLs.REGISTER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_account);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                new AlertDialog.Builder(CreateAccount.this)
                        .setTitle("Warning")
                        .setMessage("Are you sure you want to cancel?\nYou will be throw back to Login Session")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            Intent intent = new Intent(CreateAccount.this, LogReg.class);
                            startActivity(intent);
                            finish();
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        confirmAcc = findViewById(R.id.btnConfirmAcc);
        createUser = findViewById(R.id.etCreateUsername);
        createPass = findViewById(R.id.etCreatePassword);

        confirmAcc.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
        confirmAcc.setBackgroundResource(R.drawable.rounded_border_trans);
        accountCreateConfirmation();
    }

    public void accountCreateConfirmation(){
        confirmAcc.setOnClickListener(v -> {
            String username = createUser.getText().toString().trim();
            String password = createPass.getText().toString().trim();
            String receivedEmail = getIntent().getStringExtra("myEmail");
            String receivedType = getIntent().getStringExtra("typeRegister");
            String receivedFirstName = getIntent().getStringExtra("firstName");
            String receivedLastName = getIntent().getStringExtra("lastName");
            String injectionPattern = ".*[\"';=<>%*(){}\\[\\]-].*";


            if (username.isEmpty()) {
                createUser.setError("USERNAME IS EMPTY!");
            } else if (username.matches(injectionPattern)) {
                createUser.setError("Invalid username or Contains Unsafe Characters");
            }

            if (password.isEmpty()) {
                createPass.setError("PASSWORD IS EMPTY!");
            } else if (password.matches(injectionPattern)) {
                createPass.setError("Invalid password or Contains Unsafe Characters");
            }

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        try {
                            JSONObject obj = new JSONObject(response);
                            String status = obj.getString("status");
                            if (status.equals("success")) {
                                Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(this, LogReg.class);
                                startActivity(intent);
                                finish();
                            } else {
                                String message = obj.getString("message");
                                Toast.makeText(this, "Error: " + message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(this, "Error: " + e, Toast.LENGTH_SHORT).show();
                        }
                    },
                    error -> Toast.makeText(this, "Volley Error: " + error.toString(), Toast.LENGTH_SHORT).show()
            ) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("username", username);
                    params.put("password", password);
                    params.put("firstName", receivedFirstName);
                    params.put("lastName", receivedLastName);
                    params.put("type", receivedType);
                    params.put("email", receivedEmail);
                    return params;
                }
            };

            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(stringRequest);
        });
    }
}