package com.example.umakgymreserve;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;
import java.util.HashMap;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class LegsActivity extends AppCompatActivity {

    ListView listViewLegs;
    TextView descriptionTextView;
    ImageView exerciseImageView;
    private HashMap<String, String> exerciseDescriptions;
    private HashMap<String, Integer> exerciseImages;
    private String firstNameExport;
    private String registerExport;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legs);

        Intent fetch = getIntent();
        firstNameExport = fetch.getStringExtra("firstName");
        registerExport = fetch.getStringExtra("typeRegister");
        userId = fetch.getStringExtra("user_id");

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(LegsActivity.this, WorkoutsActivity.class);
                intent.putExtra("firstName", firstNameExport);
                intent.putExtra("typeRegister", registerExport);
                intent.putExtra("user_id", userId);
                startActivity(intent);
            }
        });
        listViewLegs = findViewById(R.id.listViewLegs);
        descriptionTextView = findViewById(R.id.descriptionLegsTextView);
        exerciseImageView = findViewById(R.id.imageViewLegs); // Add this ImageView in XML layout
        Button backButton = findViewById(R.id.btnLegsWorkoutB);

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(LegsActivity.this, WorkoutsActivity.class);
            intent.putExtra("firstName", firstNameExport);
            intent.putExtra("typeRegister", registerExport);
            intent.putExtra("user_id", userId);
            startActivity(intent);
            finish();
        });

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

        exerciseDescriptions.put("Pull-Ups (Various Grips)", "Hang from a pull-up bar with your chosen grip, pull your chest toward the bar by engaging your back and biceps, then lower yourself with control until your arms are fully extended.");
        exerciseDescriptions.put("Barbell Rows (Bent-Over Rows)", "Grip the barbell with both hands, bend your torso forward at about a 45-degree angle with your back flat, then pull the bar toward your lower chest while squeezing your shoulder blades together.");
        exerciseDescriptions.put("Dumbbell Rows (Single-Arm Rows)", "Place one knee and hand on a bench for support, hold a dumbbell in the other hand, pull it toward your waist while keeping your elbow close to your body, then lower it slowly.");
        exerciseDescriptions.put("Seated Cable Rows", "Sit on the bench with your feet on the platform, grab the cable handle, pull it toward your torso while keeping your back straight and squeezing your shoulder blades together, then return to the start.");
        exerciseDescriptions.put("Lat Pulldowns (Various Grips)", "Sit at the lat pulldown machine, grip the bar with your desired hand placement, pull it down to your upper chest while leaning slightly back, then return slowly with control.");
        exerciseDescriptions.put("Face Pulls", "Attach a rope to a high pulley, grip both ends with palms facing inward, pull the rope toward your face while keeping your elbows high, then return slowly to the starting position.");
        exerciseDescriptions.put("Deadlifts", "Stand with your feet hip-width apart and grip the bar just outside your knees, keep your back straight, lift the bar by extending your hips and knees simultaneously, then lower it in a controlled motion.");
        exerciseDescriptions.put("Back Extensions (Hyperextensions)", "Position yourself on a back extension bench with your hips at the edge, lower your upper body toward the ground, then contract your lower back muscles to raise your torso in line with your legs.");
        exerciseDescriptions.put("T-Bar Rows", "Stand over the T-bar row machine, grip the handles, bend at the hips with a flat back, pull the bar toward your chest by squeezing your shoulder blades, and slowly lower it.");
        exerciseDescriptions.put("Shrugs (Barbell or Dumbbell)", "Hold a barbell or dumbbells at your sides, lift your shoulders straight up toward your ears, pause at the top, then lower them back down under control.");


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
            exerciseImageView.setImageResource(R.drawable.default_exercise2); // Add default image
        }
    }
}
