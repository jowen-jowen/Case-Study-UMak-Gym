package com.example.umakgymreserve;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class ChestActivity extends AppCompatActivity {

    ListView listViewChest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chest);

        listViewChest = findViewById(R.id.listViewChest);

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
    }
}
