package com.example.umakgymreserve;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Payment extends AppCompatActivity {

    private WebView browser;
    private Button btnStartPayment, btnBackHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        browser = findViewById(R.id.webView);
        btnStartPayment = findViewById(R.id.btnStartPayment);
        btnBackHome = findViewById(R.id.btnBackHome);

        initializeWebView();

        btnStartPayment.setOnClickListener(v -> createPaymentLink());

        btnBackHome.setOnClickListener(v -> {
            Intent intent = new Intent(Payment.this, ReservationPage.class);
            startActivity(intent);
            finish(); // optional: finishes current activity so it doesn't stay in back stack
        });




    }

    private void initializeWebView() {
        browser.getSettings().setJavaScriptEnabled(true);
        browser.setWebViewClient(new WebViewClient());
    }

    private void createPaymentLink() {
        // ⚠️ Replace with your actual local IP address and PHP filename
        String phpUrl = "http://10.0.2.2/LogReg/payment.php";

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, phpUrl,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject data = jsonObject.getJSONObject("data");
                        JSONObject attributes = data.getJSONObject("attributes");
                        String checkoutUrl = attributes.getString("checkout_url");

                        // Show WebView and load the checkout link
                        browser.setVisibility(View.VISIBLE);
                        btnStartPayment.setVisibility(View.GONE);
                        browser.loadUrl(checkoutUrl);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(Payment.this, "Invalid JSON from server", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    error.printStackTrace();
                    Toast.makeText(Payment.this, "Failed to connect to PHP server", Toast.LENGTH_SHORT).show();
                });

        queue.add(stringRequest);
    }
}
