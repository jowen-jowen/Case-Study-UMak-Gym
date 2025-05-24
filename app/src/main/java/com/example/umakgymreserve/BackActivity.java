package com.example.umakgymreserve;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;
import java.util.HashMap;
import androidx.appcompat.app.AppCompatActivity;

public class BackActivity extends AppCompatActivity {

    ListView listViewBack;
    TextView descriptionTextView;
    ImageView exerciseImageView;
    private HashMap<String, String> exerciseDescriptions;
    private HashMap<String, Integer> exerciseImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);

        listViewBack = findViewById(R.id.listViewBack);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        exerciseImageView = findViewById(R.id.imageViewBack); // Add this ImageView in activity_back.xml
        Button backButton = findViewById(R.id.btn1);

        backButton.setOnClickListener(v -> finish());

        initializeExerciseData();

        String[] backExercises = exerciseDescriptions.keySet().toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                backExercises
        );
        listViewBack.setAdapter(adapter);

        listViewBack.setOnItemClickListener((parent, view, position, id) -> {
            String selectedExercise = (String) parent.getItemAtPosition(position);
            displayExerciseDetails(selectedExercise);
        });
    }

    private void initializeExerciseData() {
        exerciseDescriptions = new HashMap<>();
        exerciseImages = new HashMap<>();

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

        exerciseImages.put("Pull-Ups (Various Grips)", R.drawable.pull_ups);
        exerciseImages.put("Barbell Rows (Bent-Over Rows)", R.drawable.barbell_rows);
        exerciseImages.put("Dumbbell Rows (Single-Arm Rows)", R.drawable.dumbell_rows);
        exerciseImages.put("Seated Cable Rows", R.drawable.seated_cable_rows);
        exerciseImages.put("Lat Pulldowns (Various Grips)", R.drawable.lat_pulldowns);
        exerciseImages.put("Face Pulls", R.drawable.face_pulls);
        exerciseImages.put("Deadlifts", R.drawable.deadlifts);
        exerciseImages.put("Back Extensions (Hyperextensions)", R.drawable.back_extensions);
        exerciseImages.put("T-Bar Rows", R.drawable.t_bar_rows);
        exerciseImages.put("Shrugs (Barbell or Dumbbell)", R.drawable.shrugs);
    }

    private void displayExerciseDetails(String exerciseName) {
        String description = exerciseDescriptions.get(exerciseName);
        descriptionTextView.setText(description != null ? description : "Description not available.");

        Integer imageRes = exerciseImages.get(exerciseName);
        if (imageRes != null) {
            exerciseImageView.setImageResource(imageRes);
        } else {
            exerciseImageView.setImageResource(R.drawable.default_exercise); // default fallback
        }
    }
}
