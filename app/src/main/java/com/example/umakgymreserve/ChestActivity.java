package com.example.umakgymreserve;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;
import android.view.View;
import java.util.HashMap;
import androidx.appcompat.app.AppCompatActivity;

public class ChestActivity extends AppCompatActivity {

    ListView listViewChest;
    TextView descriptionTextView;
    private HashMap<String, String> exerciseDescriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chest);

        listViewChest = findViewById(R.id.listViewChest);
        descriptionTextView = findViewById(R.id.descriptionTextView);


        exerciseDescriptions = new HashMap<>();
        exerciseDescriptions.put("Push-Ups",
                "Fundamental bodyweight exercise. Keep core tight and lower with control. Variations: wide/narrow grip.");
        exerciseDescriptions.put("Bench Press",
                "Classic chest builder. Keep shoulder blades retracted, arch slightly, and lower bar to mid-chest.");
        exerciseDescriptions.put("Incline Dumbbell Press",
                "Targets upper pecs. Set bench at 30-45 degrees, maintain neutral wrists throughout.");
        exerciseDescriptions.put("Chest Flyes",
                "Isolation movement for chest. Maintain slight elbow bend, imagine hugging a tree.");
        exerciseDescriptions.put("Cable Crossover",
                "Great for chest definition. Keep slight forward lean and squeeze at the midpoint.");
        exerciseDescriptions.put("Chest Dips",
                "Advanced movement. Lean forward to emphasize chest, keep elbows flared about 45 degrees.");
        exerciseDescriptions.put("Incline Bench Press",
                "Develops upper chest. Use barbell or dumbbells, lower weight to upper sternum.");
        exerciseDescriptions.put("Decline Push-Ups",
                "Focuses on lower chest. Elevate feet on bench or step, maintain straight body line.");

        String[] chestExercises = {
                "Push-Ups",
                "Bench Press",
                "Incline Dumbbell Press",
                "Chest Flyes",
                "Cable Crossover",
                "Chest Dips",
                "Incline Bench Press",
                "Decline Push-Ups"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                chestExercises
        );

        listViewChest.setAdapter(adapter);

        // Set click listener for ListView items
        listViewChest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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