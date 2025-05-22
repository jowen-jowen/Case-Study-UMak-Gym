package com.example.umakgymreserve;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;
import java.util.HashMap;
import androidx.appcompat.app.AppCompatActivity;

public class LegsActivity extends AppCompatActivity {

    ListView listViewLegs;
    TextView descriptionTextView;
    ImageView exerciseImageView;
    private HashMap<String, String> exerciseDescriptions;
    private HashMap<String, Integer> exerciseImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legs);

        listViewLegs = findViewById(R.id.listViewLegs);
        descriptionTextView = findViewById(R.id.descriptionLegsTextView);
        exerciseImageView = findViewById(R.id.imageViewLegs); // Add this ImageView in XML layout
        Button backButton = findViewById(R.id.btn4);

        backButton.setOnClickListener(v -> finish());

        initializeExerciseData();

        String[] legsExercises = exerciseDescriptions.keySet().toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                legsExercises
        );
        listViewLegs.setAdapter(adapter);

        listViewLegs.setOnItemClickListener((parent, view, position, id) -> {
            String selectedExercise = (String) parent.getItemAtPosition(position);
            displayExerciseDetails(selectedExercise);
        });
    }

    private void initializeExerciseData() {
        exerciseDescriptions = new HashMap<>();
        exerciseImages = new HashMap<>();

        exerciseDescriptions.put("Barbell Squats", "High bar emphasizes quads, low bar works more posterior chain.");
        exerciseDescriptions.put("Leg Press", "Great for quad development with less spinal loading than squats.");
        exerciseDescriptions.put("Romanian Deadlifts", "Targets hamstrings and glutes.");
        exerciseDescriptions.put("Walking Lunges", "Develops single-leg strength and balance.");
        exerciseDescriptions.put("Leg Extensions", "Isolates the quadriceps.");
        exerciseDescriptions.put("Hamstring Curls", "Isolates the hamstrings.");
        exerciseDescriptions.put("Calf Raises", "Standing hits gastrocnemius, seated targets soleus.");
        exerciseDescriptions.put("Step-Ups", "Works quads, glutes, and improves balance.");
        exerciseDescriptions.put("Glute Bridges", "Excellent for glute activation.");
        exerciseDescriptions.put("Goblet Squats", "Beginner-friendly squat variation.");


        exerciseImages.put("Barbell Squats", R.drawable.barbell_squats);
        exerciseImages.put("Leg Press", R.drawable.leg_press);
        exerciseImages.put("Romanian Deadlifts", R.drawable.romanian_deadlift);
        exerciseImages.put("Walking Lunges", R.drawable.walking_lunges);
        exerciseImages.put("Leg Extensions", R.drawable.leg_extensions);
        exerciseImages.put("Hamstring Curls", R.drawable.hamstring_curls);
        exerciseImages.put("Calf Raises", R.drawable.calf_raises);
        exerciseImages.put("Step-Ups", R.drawable.step_ups);
        exerciseImages.put("Glute Bridges", R.drawable.glute_bridges);
        exerciseImages.put("Goblet Squats", R.drawable.goblet_squats);
    }

    private void displayExerciseDetails(String exerciseName) {
        String description = exerciseDescriptions.get(exerciseName);
        descriptionTextView.setText(description != null ? description : "Description not available.");

        Integer imageRes = exerciseImages.get(exerciseName);
        if (imageRes != null) {
            exerciseImageView.setImageResource(imageRes);
        } else {
            exerciseImageView.setImageResource(R.drawable.default_exercise); // Add default image
        }
    }
}
