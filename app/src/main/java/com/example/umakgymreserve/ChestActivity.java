package com.example.umakgymreserve;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;
import java.util.HashMap;
import androidx.appcompat.app.AppCompatActivity;

public class ChestActivity extends AppCompatActivity {

    ListView listViewChest;
    TextView descriptionTextView;
    ImageView exerciseImageView;
    private HashMap<String, String> exerciseDescriptions;
    private HashMap<String, Integer> exerciseImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chest);


        listViewChest = findViewById(R.id.listViewChest);
        descriptionTextView = findViewById(R.id.descriptionChestTextView);
        exerciseImageView = findViewById(R.id.imageView13);
        Button backButton = findViewById(R.id.btn2);


        backButton.setOnClickListener(v -> finish());


        initializeExerciseData();


        String[] chestExercises = exerciseDescriptions.keySet().toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                chestExercises
        );
        listViewChest.setAdapter(adapter);

        // Set item click listener
        listViewChest.setOnItemClickListener((parent, view, position, id) -> {
            String selectedExercise = (String) parent.getItemAtPosition(position);
            displayExerciseDetails(selectedExercise);
        });
    }

    private void initializeExerciseData() {
        exerciseDescriptions = new HashMap<>();
        exerciseImages = new HashMap<>();

        // Chest exercises with descriptions
        exerciseDescriptions.put("Push-Ups", "Fundamental bodyweight exercise. Keep core tight and lower with control. Variations: wide/narrow grip.");
        exerciseDescriptions.put("Bench Press", "Classic chest builder. Keep shoulder blades retracted, arch slightly, and lower bar to mid-chest.");
        exerciseDescriptions.put("Incline Dumbbell Press", "Targets upper pecs. Set bench at 30-45 degrees, maintain neutral wrists throughout.");
        exerciseDescriptions.put("Dumbell Chest Flyes", "Isolation movement for chest. Maintain slight elbow bend, imagine hugging a tree.");
        exerciseDescriptions.put("Cable Fly", "Great for chest definition. Keep slight forward lean and squeeze at the midpoint.");
        exerciseDescriptions.put("Incline Barbell Press", "Lie back on an incline bench set at about 30–45 degrees, grip the bar slightly wider than shoulder-width, lower it to your upper chest, and then press it back up until your arms are fully extended.");
        exerciseDescriptions.put("Decline Bench Press", "Lie on a decline bench with your feet secured, grip the bar slightly wider than shoulder-width, lower it to your lower chest, and press it back up with control.");
        exerciseDescriptions.put("Chest Dips", "Grab parallel bars with arms straight and lean slightly forward, lower your body by bending your elbows until your upper arms are parallel to the ground, then push yourself back up.");
        exerciseDescriptions.put("Machine Chest Press", "Sit on the machine with your back flat against the pad, grip the handles at chest level, press them forward until your arms are extended, and slowly return to the starting position.");
        exerciseDescriptions.put("Svend Press", "Hold a weight plate between your palms at chest height, press it straight forward while keeping your elbows slightly bent and squeezing your chest, then return to the start position while maintaining the squeeze.");



        exerciseImages.put("Push-Ups", R.drawable.push_ups);
        exerciseImages.put("Bench Press", R.drawable.bench_press);
        exerciseImages.put("Incline Dumbbell Press", R.drawable.inclined_dumbell_press);
        exerciseImages.put("Chest Flyes", R.drawable.dumbell_chest_flyes);
        exerciseImages.put("Cable Crossover", R.drawable.cable_fly);
        exerciseImages.put("Incline Barbell Press", R.drawable.incline_barbell_press);
        exerciseImages.put("Decline Bench Press", R.drawable.declined_bench_press);
        exerciseImages.put("Chest Dips", R.drawable.chest_dips);
        exerciseImages.put("Machine Chest Press", R.drawable.machine_chest_press);
        exerciseImages.put("Svend Press", R.drawable.svend_press);

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
            exerciseImageView.setImageResource(R.drawable.default_exercise1); // Set a default image
        }
    }
}