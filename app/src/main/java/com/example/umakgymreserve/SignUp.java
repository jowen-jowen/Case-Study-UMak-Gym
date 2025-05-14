package com.example.umakgymreserve;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUp extends AppCompatActivity {
    RadioGroup rdGroupType;
    EditText first, last, email;
    RadioButton stud, emp, alum;
    TextView price;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        first = findViewById(R.id.etFirstName);
        last = findViewById(R.id.etLastName);
        rdGroupType = findViewById(R.id.rdGrpType);
        stud = findViewById(R.id.rdStudent);
        emp = findViewById(R.id.rdEmployee);
        alum = findViewById(R.id.rdAlumni);
        price = findViewById(R.id.tvPrice);
        email = findViewById(R.id.etUmakEmail);
        confirm = findViewById(R.id.btnBackHomePayment);

        stud.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.radio_button_color)));
        emp.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.radio_button_color)));
        alum.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.radio_button_color)));
        confirm.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#cfcf8c")));
        confirm.setBackgroundResource(R.drawable.rounded_border_trans);

        setPrice();
        confirmationData();
    }

    public void setPrice() {
        rdGroupType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rdStudent) {
                    price.setText(R.string.stud_price_results);
                }
                if (checkedId == R.id.rdEmployee) {
                    price.setText(R.string.price_employee_results);
                }
                if (checkedId == R.id.rdAlumni) {
                    price.setText(R.string.price_alumni_results);
                }
            }
        });
    }

    public void confirmationData() {
        confirm.setOnClickListener(v -> {
            String firstNameValidator = "^[A-Za-z]+([-'\\s]?[A-Za-z]+)*$";
            String lastNameValidator = "^[A-Za-z]+([-'\\s]?[A-Za-z]+)*$";
            String umakEmailValidator = "^[a-zA-Z]+\\.[a|k][0-9]{8,10}@(umak\\.edu\\.ph)$";

            String firstName = first.getText().toString().trim();
            String lastName = last.getText().toString().trim();
            String umakEmail = email.getText().toString().trim();

            //for radiogroup
            int selectedId = rdGroupType.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(this, "Please select a registration type.", Toast.LENGTH_SHORT).show();
                return;
            }
            RadioButton selectedRdButton = findViewById(selectedId);
            String typeRegister = selectedRdButton.getText().toString();


            if (firstName.matches(firstNameValidator)) {
                if (lastName.matches(lastNameValidator)) {
                    if (umakEmail.matches(umakEmailValidator)) {
                        Intent intent = new Intent(this, OtpVerif.class);
                        intent.putExtra("firstName", firstName);
                        intent.putExtra("lastName", lastName);
                        intent.putExtra("myEmail", umakEmail);
                        intent.putExtra("typeRegister", typeRegister);
                        startActivity(intent);
                        finish();
                    } else {
                        email.setError("Invalid UMAK ACCOUNT");
                    }
                } else if (lastName.isEmpty()) {
                    last.setError("Last Name is empty");
                } else {
                    last.setError("Invalid Last Name");
                }
            } else if (firstName.isEmpty()) {
                first.setError("First Name is empty");
            } else {
                first.setError("Invalid First Name");
            }
        });
    }

}