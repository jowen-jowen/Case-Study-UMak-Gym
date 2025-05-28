package com.example.umakgymreserve;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.*;

public class SessionBookingActivity extends AppCompatActivity {
    private GridLayout calendarGrid;
    private TextView tvSelectedDate;
    Button btnSelectDate, btnClearSelection, btnBack, btnProceed;
    private final List<Calendar> selectedDates = new ArrayList<>();
    private final SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault());
    private final Map<TextView, Calendar> dateViewsMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_booking);

        String firstNameExport = getIntent().getStringExtra("firstName");
        String registerExport = getIntent().getStringExtra("typeRegister");
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                new AlertDialog.Builder(SessionBookingActivity.this)
                        .setTitle("Warning")
                        .setMessage("Are you sure you want to go back?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            Intent intent = new Intent(SessionBookingActivity.this, ReservationPage.class);
                            intent.putExtra("firstName", firstNameExport);
                            intent.putExtra("typeRegister", registerExport);
                            finish();
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        calendarGrid = findViewById(R.id.calendarGrid);
        tvSelectedDate = findViewById(R.id.tvSelectedDate);
        btnSelectDate = findViewById(R.id.btnSelectDate);
        btnClearSelection = findViewById(R.id.btnClearSelection);
        btnBack = findViewById(R.id.btnBackToMain);
        btnProceed = findViewById(R.id.btnProceed);

        generateCalendar();

        btnSelectDate.setOnClickListener(v -> {
            if (selectedDates.isEmpty()) {
                tvSelectedDate.setText("Please select a date.");
                Toast.makeText(this, "You must select one booking date.", Toast.LENGTH_SHORT).show();
            } else if (selectedDates.size() > 1) {
                tvSelectedDate.setText("You can only select one date.");
                Toast.makeText(this, "Please select only ONE booking date.", Toast.LENGTH_SHORT).show();
            } else {
                Calendar date = selectedDates.get(0);
                tvSelectedDate.setText("Selected: " + sdf.format(date.getTime()));
            }
        });

        btnClearSelection.setOnClickListener(v -> {
            for (Map.Entry<TextView, Calendar> entry : dateViewsMap.entrySet()) {
                TextView dayView = entry.getKey();
                if (!dayView.isEnabled()) continue;
                dayView.setBackgroundResource(R.drawable.calendar_cell_border);
                dayView.setTextColor(Color.BLACK);
            }
            selectedDates.clear();
            tvSelectedDate.setText("No date selected");
        });

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(SessionBookingActivity.this, ReservationPage.class);

            intent.putExtra("firstName", firstNameExport);
            intent.putExtra("typeRegister", registerExport);
            startActivity(intent);
            finish();
        });

        btnProceed.setOnClickListener(v -> {
            if (selectedDates.size() != 1) {
                Toast.makeText(this, "Please select exactly 1 date to proceed.", Toast.LENGTH_SHORT).show();

            } else {
                String selectedDate = tvSelectedDate.getText().toString().trim();
                Intent intent = new Intent(SessionBookingActivity.this, Payment.class);
                intent.putExtra("selectedDate", selectedDate);
                intent.putExtra("firstName", firstNameExport);
                intent.putExtra("typeRegister", registerExport);
                startActivity(intent);
                finish();
            }
        });
    }

    private void generateCalendar() {
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentYear = calendar.get(Calendar.YEAR);

        calendar.set(currentYear, currentMonth, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        final int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        final Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        String[] weekdayLabels = {"Mon", "Tue", "Wed", "Thu", "Fri"};

        calendarGrid.post(() -> {
            int gridWidth = calendarGrid.getWidth();
            int cellWidth = gridWidth / 5;

            calendarGrid.removeAllViews();
            calendarGrid.setColumnCount(5);
            calendarGrid.setRowCount(7); // 1 for headers + up to 6 weeks

            // === WEEKDAY HEADERS ===
            for (int i = 0; i < 5; i++) {
                TextView header = new TextView(this);
                header.setText(weekdayLabels[i]);
                header.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                header.setGravity(Gravity.CENTER);
                header.setBackgroundColor(Color.LTGRAY);
                header.setTextColor(Color.BLACK);
                header.setTextSize(14);
                header.setPadding(0, 10, 0, 10);

                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = 0;
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                params.setMargins(8, 8, 8, 8);
                params.columnSpec = GridLayout.spec(i, 1f);
                params.rowSpec = GridLayout.spec(0);
                header.setLayoutParams(params);

                calendarGrid.addView(header);
            }

            int row = 1;
            int col;


            for (int day = 1; day <= daysInMonth; day++) {
                Calendar cellDate = Calendar.getInstance();
                cellDate.set(currentYear, currentMonth, day);
                cellDate.set(Calendar.HOUR_OF_DAY, 0);
                cellDate.set(Calendar.MINUTE, 0);
                cellDate.set(Calendar.SECOND, 0);
                cellDate.set(Calendar.MILLISECOND, 0);

                int dayOfWeek = cellDate.get(Calendar.DAY_OF_WEEK);

                if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) continue;


                col = dayOfWeek - Calendar.MONDAY;

                if (col == 0 && day > 1) {
                    row++;
                }

                TextView dayView = new TextView(this);
                dayView.setText(String.valueOf(day));
                dayView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                dayView.setGravity(Gravity.CENTER);
                dayView.setBackgroundResource(R.drawable.calendar_cell_border);
                dayView.setPadding(0, 25, 0, 25);

                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = 0;
                params.height = cellWidth;
                params.setMargins(8, 8, 8, 8);
                params.columnSpec = GridLayout.spec(col, 1f);
                params.rowSpec = GridLayout.spec(row);
                dayView.setLayoutParams(params);

                boolean isPast = cellDate.before(today);
                if (isPast) {
                    dayView.setTextColor(Color.GRAY);
                    dayView.setEnabled(false);
                } else {
                    dayView.setTextColor(Color.BLACK);
                    dayView.setEnabled(true);
                    dateViewsMap.put(dayView, cellDate);

                    dayView.setOnClickListener(view -> {
                        Calendar selected = dateViewsMap.get(view);
                        TextView selectedView = (TextView) view;

                        for (Map.Entry<TextView, Calendar> entry : dateViewsMap.entrySet()) {
                            TextView otherDayView = entry.getKey();
                            if (!otherDayView.isEnabled()) continue;
                            otherDayView.setBackgroundResource(R.drawable.calendar_cell_border);
                            otherDayView.setTextColor(Color.BLACK);
                        }

                        selectedDates.clear();
                        selectedDates.add(selected);

                        selectedView.setBackgroundColor(Color.parseColor("#6200EE"));
                        selectedView.setTextColor(Color.WHITE);
                    });
                }

                calendarGrid.addView(dayView);
            }
        });
    }
}
