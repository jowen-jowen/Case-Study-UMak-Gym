package com.example.umakgymreserve;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.AdapterView;
import android.view.View;
import java.util.HashMap;
import androidx.appcompat.app.AppCompatActivity;

public class BackActivity extends AppCompatActivity {

    ListView listViewBack;
    TextView descriptionTextView;
    private HashMap<String, String> exerciseDescriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);

        listViewBack = findViewById(R.id.listViewBack);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        Button backButton = findViewById(R.id.btn2);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String firstNameExport = getIntent().getStringExtra("firstName");
                String registerExport = getIntent().getStringExtra("typeRegister");
                intent.putExtra("firstName", firstNameExport);
                intent.putExtra("typeRegister", registerExport);
                finish();
            }
        });

        exerciseDescriptions = new HashMap<>();
        exerciseDescriptions.put("Pull-Ups (Various Grips)", "Pull-Ups target your lats, traps, and biceps. Use different grips to emphasize different muscles.");
        exerciseDescriptions.put("Barbell Rows (Bent-Over Rows)", "Strengthens the middle back and posterior chain. Keep your back straight at about 45 degrees.");
        exerciseDescriptions.put("Dumbbell Rows (Single-Arm Rows)", "Great for isolating one side of your back. Helps correct muscle imbalances.");
        exerciseDescriptions.put("Seated Cable Rows", "Targets the middle back with constant tension. Focus on squeezing your shoulder blades together.");
        exerciseDescriptions.put("Lat Pulldowns (Various Grips)", "Works the latissimus dorsi. Wider grips target the outer lats, closer grips work the inner lats.");
        exerciseDescriptions.put("Face Pulls", "Excellent for rear delts and upper back. Helps improve posture and shoulder health.");
        exerciseDescriptions.put("Deadlifts", "Full-body exercise that heavily works the lower back. Maintain proper form to avoid injury.");
        exerciseDescriptions.put("Back Extensions (Hyperextensions)", "Targets the erector spinae. Can be done with or without additional weight.");
        exerciseDescriptions.put("T-Bar Rows", "Compound movement for thickness in the middle back. Keep your chest up throughout the movement.");
        exerciseDescriptions.put("Shrugs (Barbell or Dumbbell)", "Focuses on the trapezius muscles. Lift with your shoulders, not your arms.");

        String[] backExercises = {
                "Pull-Ups (Various Grips)",
                "Barbell Rows (Bent-Over Rows)",
                "Dumbbell Rows (Single-Arm Rows)",
                "Seated Cable Rows",
                "Lat Pulldowns (Various Grips)",
                "Face Pulls",
                "Deadlifts",
                "Back Extensions (Hyperextensions)",
                "T-Bar Rows",
                "Shrugs (Barbell or Dumbbell)"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                backExercises
        );

        listViewBack.setAdapter(adapter);

        // Set click listener for ListView items
        listViewBack.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedExercise = (String) parent.getItemAtPosition(position);
                displayExerciseDescription(selectedExercise);
            }
        });
    }

    private void displayExerciseDescription(String exerciseName) {
        String description = exerciseDescriptions.get(exerciseName);
        if (description != null) {
            descriptionTextView.setText(description);
        } else {
            descriptionTextView.setText("Description not available for this exercise.");
        }
    }
}
