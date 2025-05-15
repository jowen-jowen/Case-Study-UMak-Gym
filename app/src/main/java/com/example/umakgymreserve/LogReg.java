package com.example.umakgymreserve;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
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

public class LogReg extends AppCompatActivity {
    Button login, signUp;

    EditText user,pass;

    String URL = "http://10.0.2.2/LogReg/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_reg);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        login = findViewById(R.id.btnLogin);
        signUp = findViewById(R.id.btnsignUp);

        user = findViewById(R.id.etUsername);
        pass = findViewById(R.id.etPassword);

        login.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
        signUp.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
        login.setBackgroundResource(R.drawable.rounded_border_trans);
        signUp.setBackgroundResource(R.drawable.rounded_border_trans);

        login.setOnClickListener(v ->{
            String username = user.getText().toString().trim();
            String password = pass.getText().toString().trim();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                    response -> {
                        try {
                            JSONObject obj = new JSONObject(response);
                            String status = obj.getString("status");

                            if (status.equals("success")) {
                                Intent intent = new Intent(LogReg.this, ReservationPage.class);
                                intent.putExtra("username", username); // Optional
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
                    return params;
                }
            };

            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(stringRequest);
        });

        signUp.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
            finish();
        });
    }
}