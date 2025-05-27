package com.example.umakgymreserve;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUp extends AppCompatActivity {
    RadioGroup rdGroupType;
    EditText first, last;
    RadioButton stud, emp, alum;
    TextView price;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                new AlertDialog.Builder(SignUp.this)
                        .setTitle("Warning")
                        .setMessage("Are you sure you want to cancel?\nYou will be throw back to Login Session")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            Intent intent = new Intent(SignUp.this,LogReg.class);
                            startActivity(intent);
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        first = findViewById(R.id.etFirstName);
        last = findViewById(R.id.etLastName);
        rdGroupType = findViewById(R.id.rdGrpType);
        stud = findViewById(R.id.rdStudent);
        emp = findViewById(R.id.rdEmployee);
        alum = findViewById(R.id.rdAlumni);
        price = findViewById(R.id.tvPrice);
        next = findViewById(R.id.btnNext);

        stud.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.radio_button_color)));
        emp.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.radio_button_color)));
        alum.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.radio_button_color)));
        next.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
        next.setBackgroundResource(R.drawable.rounded_border_trans);

        price.setVisibility(View.GONE);

        setPrice();
        confirmationData();
    }

    public void setPrice() {
        rdGroupType.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rdStudent) {
                price.setVisibility(View.VISIBLE);
                price.setText(R.string.stud_price_results);
            }
            if (checkedId == R.id.rdEmployee) {
                price.setVisibility(View.VISIBLE);
                price.setText(R.string.price_employee_results);
            }
            if (checkedId == R.id.rdAlumni) {
                price.setVisibility(View.VISIBLE);
                price.setText(R.string.price_alumni_results);
            }
        });
    }

    public void confirmationData() {
        next.setOnClickListener(v -> {
            String firstNameValidator = "^[A-Za-z]+([-'\\s]?[A-Za-z]+)*$";
            String lastNameValidator = "^[A-Za-z]+([-'\\s]?[A-Za-z]+)*$";
            String firstName = first.getText().toString().trim();
            String lastName = last.getText().toString().trim();
            String injectionPattern = ".*[\"';=<>%*(){}\\[\\]--].*";


            //for radiogroup
            int selectedId = rdGroupType.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(this, "Please select a registration type.", Toast.LENGTH_SHORT).show();
            }

            RadioButton selectedRdButton = findViewById(selectedId);
            String typeRegister = selectedRdButton.getText().toString();
            String umakEmail = getIntent().getStringExtra("myEmail");
            if (firstName.matches(firstNameValidator)) {
                if (lastName.matches(lastNameValidator)) {
                        Intent intent = new Intent(this, CreateAccount.class);
                        intent.putExtra("firstName", firstName);
                        intent.putExtra("lastName", lastName);
                        intent.putExtra("typeRegister", typeRegister);
                        intent.putExtra("myEmail", umakEmail);
                        startActivity(intent);
                } else if (lastName.isEmpty()) {
                    last.setError("Last Name is empty");
                } else if (lastName.matches(injectionPattern)){
                    last.setError("Invalid Last Name or Contains Unsafe Characters");
                }
            } else if (firstName.isEmpty()) {
                first.setError("First Name is empty");
            } else if (firstName.matches(injectionPattern)) {
                first.setError("Invalid First Name or Contains Unsafe Characters");
            }
        });
    }

}