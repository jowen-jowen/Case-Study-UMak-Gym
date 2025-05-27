package com.example.umakgymreserve;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;
import android.view.View;
import android.widget.Button;
import java.util.HashMap;
import androidx.appcompat.app.AppCompatActivity;

public class LegsActivity extends AppCompatActivity {

    ListView listViewLegs;
    TextView descriptionTextView;
    private HashMap<String, String> exerciseDescriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legs);

        listViewLegs = findViewById(R.id.listViewLegs);
        descriptionTextView = findViewById(R.id.descriptionLegsTextView);
        Button backButton = findViewById(R.id.btnLegsWorkoutB); // BACK button

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(LegsActivity.this, WorkoutsActivity.class);
            String firstNameExport = getIntent().getStringExtra("firstName");
            String registerExport = getIntent().getStringExtra("typeRegister");
            intent.putExtra("firstName", firstNameExport);
            intent.putExtra("typeRegister", registerExport);
        });

        exerciseDescriptions = new HashMap<>();
        exerciseDescriptions.put("Barbell Squats (High Bar & Low Bar)",
                "The king of leg exercises. High bar emphasizes quads, low bar works more posterior chain.");
        exerciseDescriptions.put("Leg Press",
                "Great for quad development with less spinal loading than squats. Keep feet placement varied.");
        exerciseDescriptions.put("Romanian Deadlifts (RDLs)",
                "Targets hamstrings and glutes. Maintain slight knee bend and flat back throughout.");
        exerciseDescriptions.put("Walking Lunges",
                "Develops single-leg strength and balance. Great for athletic performance.");
        exerciseDescriptions.put("Leg Extensions",
                "Isolates the quadriceps. Use controlled movements to protect knees.");
        exerciseDescriptions.put("Hamstring Curls (Lying or Seated)",
                "Isolates the hamstrings. Lying version hits more of the biceps femoris.");
        exerciseDescriptions.put("Calf Raises (Standing or Seated)",
                "Standing hits gastrocnemius, seated targets soleus. Do full range of motion.");
        exerciseDescriptions.put("Step-Ups",
                "Functional exercise that works quads, glutes, and improves balance.");
        exerciseDescriptions.put("Glute Bridges/Hip Thrusts (Barbell or Bodyweight)",
                "Excellent for glute activation. Can progressively overload with weight.");
        exerciseDescriptions.put("Goblet Squats",
                "Beginner-friendly squat variation that helps maintain upright torso position.");

        String[] legsExercises = {
                "Barbell Squats (High Bar & Low Bar)", "Leg Press", "Romanian Deadlifts (RDLs)",
                "Walking Lunges", "Leg Extensions", "Hamstring Curls (Lying or Seated)",
                "Calf Raises (Standing or Seated)", "Step-Ups",
                "Glute Bridges/Hip Thrusts (Barbell or Bodyweight)", "Goblet Squats"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, legsExercises
        );
        listViewLegs.setAdapter(adapter);

        listViewLegs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedExercise = (String) parent.getItemAtPosition(position);
                displayExerciseDescription(selectedExercise);
            }
        });
    }

    private void displayExerciseDescription(String exerciseName) {
        String description = exerciseDescriptions.get(exerciseName);
        descriptionTextView.setText(description != null ? description : "Description not available for this exercise.");
    }
}



