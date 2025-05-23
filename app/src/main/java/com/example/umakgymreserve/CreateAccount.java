package com.example.umakgymreserve;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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


public class CreateAccount extends AppCompatActivity {
    Button confirmAcc;
    EditText createUser, createPass;
    String URL = "http://10.0.2.2/LogReg/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        confirmAcc = findViewById(R.id.btnConfirmAcc);
        createUser = findViewById(R.id.etCreateUsername);
        createPass = findViewById(R.id.etCreatePassword);

        confirmAcc.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#cfcf8c")));
        confirmAcc.setBackgroundResource(R.drawable.rounded_border_trans);
        accountCreateConfirmation();
    }

    public void accountCreateConfirmation(){
        confirmAcc.setOnClickListener(v -> {
            String username = createUser.getText().toString().trim();
            String password = createPass.getText().toString().trim();
            Intent fetchSignUp = getIntent();
            String receivedEmail = fetchSignUp.getStringExtra("myEmail");
            String receivedType = fetchSignUp.getStringExtra("typeRegister");
            String receivedFirstName = fetchSignUp.getStringExtra("firstName");
            String receivedLastName = fetchSignUp.getStringExtra("lastName");

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
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