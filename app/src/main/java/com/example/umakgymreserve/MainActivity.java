package com.example.umakgymreserve;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {
    Button btnStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnStart = findViewById(R.id.btnStart);

        btnStart.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#cfcf8c")));
        getStartedButton();
        btnStart.setBackgroundResource(R.drawable.rounded_border_trans);
    }

    public void getStartedButton(){
        btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(this, LogReg.class);
            startActivity(intent);
            finish();
        });
    }
}