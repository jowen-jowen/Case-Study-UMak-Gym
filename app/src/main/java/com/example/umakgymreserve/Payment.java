package com.example.umakgymreserve;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

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

    private String userName = "Default Name";
    private String userEmail = "default@example.com";
    private String userContact = "09999999999";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            String firstNameExport = getIntent().getStringExtra("firstName");
            String registerExport = getIntent().getStringExtra("typeRegister");
            intent.putExtra("firstName", firstNameExport);
            intent.putExtra("typeRegister", registerExport);
            startActivity(intent);
            finish();
        });

        // Uncomment if you want to get real user data passed from previous Activity
        // userName = getIntent().getStringExtra("name");
        // userEmail = getIntent().getStringExtra("email");
        // userContact = getIntent().getStringExtra("contact");
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

                if (url.contains("success.php") && url.contains("pm.link/gcash/success")) {
                    // For pm.link/gcash/success, you might not get query params, so
                    // you may need to call your backend or save something before.

                    Uri uri = Uri.parse(url);

                    String name = uri.getQueryParameter("name");
                    String email = uri.getQueryParameter("email");
                    String contact = uri.getQueryParameter("contact");
                    String reference = uri.getQueryParameter("reference");
                    String linkId = uri.getQueryParameter("link_id");

                    // If params are missing, consider fetching from backend by linkId or reference

                    Toast.makeText(Payment.this,
                            "Payment successful! Reference: " + reference,
                            Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(Payment.this, PaymentSuccess.class);
                    intent.putExtra("name", name);
                    intent.putExtra("email", email);
                    intent.putExtra("contact", contact);
                    intent.putExtra("reference", reference);
                    intent.putExtra("link_id", linkId);

                    String firstNameExport = getIntent().getStringExtra("firstName");
                    String registerExport = getIntent().getStringExtra("typeRegister");
                    intent.putExtra("firstName", firstNameExport);
                    intent.putExtra("typeRegister", registerExport);

                    startActivity(intent);
                    finish();

                    return true;
                }else if (url.contains("fail.php") || url.contains("pm.link/gcash/failure")) {
                        // Add your custom logic or parameters here if needed
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
        String phpUrl = "http://10.0.2.2/LogReg/payment.php"
                + "?name=" + Uri.encode(userName)
                + "&email=" + Uri.encode(userEmail)
                + "&contact=" + Uri.encode(userContact);


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
