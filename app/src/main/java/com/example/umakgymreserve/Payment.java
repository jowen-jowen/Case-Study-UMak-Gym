package com.example.umakgymreserve;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.BuildConfig;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Payment extends AppCompatActivity {

    private WebView browser;
    private Button btnStartPayment, btnBackHome;
    String paymentCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String firstNameExport = getIntent().getStringExtra("firstName");
        String registerExport = getIntent().getStringExtra("typeRegister");
        String userId = getIntent().getStringExtra("user_id");
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                new AlertDialog.Builder(Payment.this)
                        .setTitle("Warning")
                        .setMessage("Are you sure you want to go back?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            Intent intent = new Intent(Payment.this, SessionBookingActivity.class);
                            intent.putExtra("firstName", firstNameExport);
                            intent.putExtra("typeRegister", registerExport);
                            intent.putExtra("user_id", userId);
                            startActivity(intent);
                            finish();
                        })
                        .setNegativeButton("No", null)
                        .show();
            }

        });
        if (BuildConfig.DEBUG) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        setContentView(R.layout.activity_payment);

        browser = findViewById(R.id.webView);
        btnStartPayment = findViewById(R.id.btnStartPayment);
        btnBackHome = findViewById(R.id.btnBackHome);

        initializeWebView();

        btnStartPayment.setOnClickListener(v -> {
            Toast.makeText(Payment.this, "Directing to payment...", Toast.LENGTH_SHORT).show();
            createPaymentLink();
        });

        btnBackHome.setOnClickListener(v -> {
            Intent intent = new Intent(Payment.this, ReservationPage.class);

            intent.putExtra("firstName", firstNameExport);
            intent.putExtra("typeRegister", registerExport);
            startActivity(intent);
            finish();
        });
    }

    private void initializeWebView() {
        browser.getSettings().setJavaScriptEnabled(true);

        browser.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();
                return handleUrl(url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                handleUrl(url);
            }

            private boolean handleUrl(String url) {
                Toast.makeText(Payment.this, "Loaded URL: " + url, Toast.LENGTH_LONG).show();

                if (url.contains("pm.link/gcash/success")) {
                    // Minimal handling on success
                    Toast.makeText(Payment.this,
                            "Payment successful!",
                            Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(Payment.this, PaymentSuccess.class);
                    intent.putExtra("payment_code", paymentCode);

                    String selectedDate = getIntent().getStringExtra("selectedDate");
                    String firstNameExport = getIntent().getStringExtra("firstName");
                    String registerExport = getIntent().getStringExtra("typeRegister");
                    intent.putExtra("selectedDate", selectedDate);
                    intent.putExtra("firstName", firstNameExport);
                    intent.putExtra("typeRegister", registerExport);
                    startActivity(intent);
                    finish();

                    return true;
                } else if (url.contains("pm.link/gcash/failure")) {
                    Intent failIntent = new Intent(Payment.this, PaymentFailed.class);
                    startActivity(failIntent);
                    finish();
                    return true;
                }

                return false;
            }
        });
    }

    private void createPaymentLink() {
        String phpUrl = "https://bfe3-136-158-33-155.ngrok-free.app/LogReg/payment.php";

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, phpUrl,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject data = jsonObject.getJSONObject("data");
                        JSONObject attributes = data.getJSONObject("attributes");
                        String checkoutUrl = attributes.getString("checkout_url");

                        // 🔑 Extract the code (e.g., "8iChSHV")
                        String[] parts = checkoutUrl.split("/");
                        paymentCode = parts[parts.length - 1];

                        Log.d("PaymentCode", "Extracted Code: " + paymentCode);
                        Toast.makeText(Payment.this, "Code: " + paymentCode, Toast.LENGTH_SHORT).show();

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
