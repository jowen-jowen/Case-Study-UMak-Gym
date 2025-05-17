package com.example.umakgymreserve;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;
import android.view.View;
import java.util.HashMap;
import androidx.appcompat.app.AppCompatActivity;

public class BicepsActivity extends AppCompatActivity {

    ListView listViewBiceps;
    TextView descriptionTextView;
    private HashMap<String, String> exerciseDescriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biceps);

        listViewBiceps = findViewById(R.id.listViewBiceps);
        descriptionTextView = findViewById(R.id.descriptionTextView);

        // Initialize and populate the HashMap with biceps exercise descriptions
        exerciseDescriptions = new HashMap<>();
        exerciseDescriptions.put("Barbell Bicep Curls",
                "Classic bicep builder. Keep elbows pinned to sides, curl weight up while squeezing biceps. Avoid swinging.");
        exerciseDescriptions.put("Dumbbell Bicep Curls",
                "Allows individual arm focus. Can do alternating or simultaneous curls. Rotate palms up during curl.");
        exerciseDescriptions.put("Hammer Curls",
                "Targets brachialis and brachioradialis. Keep palms facing inward throughout movement.");
        exerciseDescriptions.put("Concentration Curls",
                "Isolates the biceps effectively. Sit with elbow braced against inner thigh, focus on slow contraction.");
        exerciseDescriptions.put("Incline Dumbbell Curls",
                "Provides full stretch at bottom. Lie back on incline bench (45°), maintain control throughout.");
        exerciseDescriptions.put("Preacher Curls",
                "Eliminates cheating. Pad isolates biceps by preventing shoulder involvement. Control negative phase.");
        exerciseDescriptions.put("Cable Bicep Curls",
                "Provides constant tension. Use straight bar or rope attachment. Keep elbows stationary.");
        exerciseDescriptions.put("Reverse Curls",
                "Works brachioradialis. Use overhand grip, focus on controlled movement with lighter weight.");
        exerciseDescriptions.put("Chin-Ups (Close Grip, Palms Facing You)",
                "Compound bicep builder. Pull chest to bar while keeping core tight. Excellent for functional strength.");
        exerciseDescriptions.put("Zottman Curls",
                "Combines regular and reverse curl. Rotate palms down during lowering phase for complete forearm work.");

        String[] bicepsExercises = {
                "Barbell Bicep Curls",
                "Dumbbell Bicep Curls",
                "Hammer Curls",
                "Concentration Curls",
                "Incline Dumbbell Curls",
                "Preacher Curls",
                "Cable Bicep Curls",
                "Reverse Curls",
                "Chin-Ups (Close Grip, Palms Facing You)",
                "Zottman Curls"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                bicepsExercises
        );

        listViewBiceps.setAdapter(adapter);

        // Set click listener for ListView items
        listViewBiceps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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