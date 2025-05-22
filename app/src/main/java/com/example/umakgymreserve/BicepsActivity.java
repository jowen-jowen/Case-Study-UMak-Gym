package com.example.umakgymreserve;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.AdapterView;
import android.view.View;
import android.widget.Button;
import java.util.HashMap;
import androidx.appcompat.app.AppCompatActivity;

public class BicepsActivity extends AppCompatActivity {

    ListView listViewBiceps;
    TextView descriptionTextView;
    ImageView exerciseImageView;
    private HashMap<String, String> exerciseDescriptions;
    private HashMap<String, Integer> exerciseImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biceps);

        // Initialize views
        listViewBiceps = findViewById(R.id.listViewBiceps);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        exerciseImageView = findViewById(R.id.imageView14);
        Button backButton = findViewById(R.id.btn3);

        // Setup back button
        backButton.setOnClickListener(v -> finish());

        // Initialize exercise data
        initializeExerciseData();

        // Create and set adapter
        String[] bicepsExercises = exerciseDescriptions.keySet().toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                bicepsExercises
        );
        listViewBiceps.setAdapter(adapter);

        // Set item click listener
        listViewBiceps.setOnItemClickListener((parent, view, position, id) -> {
            String selectedExercise = (String) parent.getItemAtPosition(position);
            displayExerciseDetails(selectedExercise);
        });
    }

    private void initializeExerciseData() {
        exerciseDescriptions = new HashMap<>();
        exerciseImages = new HashMap<>();

        // Basic exercises from first version
        exerciseDescriptions.put("Bicep Curls", "Bicep Curls target the short head of the biceps.");
        exerciseDescriptions.put("Hammer Curls", "Hammer Curls focus on the brachialis muscle.");
        exerciseDescriptions.put("Concentration Curls", "Concentration Curls isolate the biceps for strength.");
        exerciseDescriptions.put("Preacher Curls", "Preacher Curls prevent cheating and increase isolation.");


        exerciseImages.put("Bicep Curls", R.drawable.bicep_curls);
        exerciseImages.put("Hammer Curls", R.drawable.hammer_curls);
        exerciseImages.put("Concentration Curls", R.drawable.concentration_curls);
        exerciseImages.put("Preacher Curls", R.drawable.preacher_curls);

    }

    private void displayExerciseDetails(String exerciseName) {
        // Set description
        String description = exerciseDescriptions.get(exerciseName);
        descriptionTextView.setText(description != null ? description : "Description not available for this exercise.");

        // Set image if available
        Integer imageRes = exerciseImages.get(exerciseName);
        if (imageRes != null) {
            exerciseImageView.setImageResource(imageRes);
        } else {
            exerciseImageView.setImageResource(R.drawable.default_exercise); // Set a default image
        }
    }
}