package com.example.umakgymreserve;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.*;

public class SessionBookingActivity extends AppCompatActivity {
    private GridLayout calendarGrid;
    private TextView tvSelectedDate;
    private Button btnSelectDate, btnClearSelection;
    private final List<Calendar> selectedDates = new ArrayList<>();
    private final SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault());
    private final Map<TextView, Calendar> dateViewsMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_booking);

        calendarGrid = findViewById(R.id.calendarGrid);
        tvSelectedDate = findViewById(R.id.tvSelectedDate);
        btnSelectDate = findViewById(R.id.btnSelectDate);
        btnClearSelection = findViewById(R.id.btnClearSelection);

        generateCalendar();

        btnSelectDate.setOnClickListener(v -> {
            if (!selectedDates.isEmpty()) {
                StringBuilder sb = new StringBuilder("Selected:\n");
                for (Calendar date : selectedDates) {
                    sb.append(sdf.format(date.getTime())).append("\n");
                }
                tvSelectedDate.setText(sb.toString().trim());
            } else {
                tvSelectedDate.setText("No date selected");
                Toast.makeText(this, "Please select a valid date first.", Toast.LENGTH_SHORT).show();
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
                params.setMargins(8, 8, 8, 8); // ✅ margin for weekday headers
                params.columnSpec = GridLayout.spec(i, 1f);
                params.rowSpec = GridLayout.spec(0);
                header.setLayoutParams(params);

                calendarGrid.addView(header);
            }

            int row = 1;
            int col;

            // === DATE CELLS ===
            for (int day = 1; day <= daysInMonth; day++) {
                Calendar cellDate = Calendar.getInstance();
                cellDate.set(currentYear, currentMonth, day);
                cellDate.set(Calendar.HOUR_OF_DAY, 0);
                cellDate.set(Calendar.MINUTE, 0);
                cellDate.set(Calendar.SECOND, 0);
                cellDate.set(Calendar.MILLISECOND, 0);

                int dayOfWeek = cellDate.get(Calendar.DAY_OF_WEEK); // Sun=1, Mon=2, ..., Sat=7

                // Skip weekends
                if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) continue;

                // Map: Mon=2 → 0, Tue=3 → 1, ..., Fri=6 → 4
                col = dayOfWeek - Calendar.MONDAY;

                if (col == 0 && day > 1) {
                    row++;
                }

                TextView dayView = new TextView(this);
                dayView.setText(String.valueOf(day));
                dayView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                dayView.setGravity(Gravity.CENTER);
                dayView.setBackgroundResource(R.drawable.calendar_cell_border);
                dayView.setPadding(0, 25, 0, 25); // ✅ padding inside each cell

                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = 0;
                params.height = cellWidth;
                params.setMargins(8, 8, 8, 8); // ✅ margin between cells
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

                        boolean alreadySelected = false;
                        for (Calendar c : selectedDates) {
                            if (c.get(Calendar.YEAR) == selected.get(Calendar.YEAR)
                                    && c.get(Calendar.MONTH) == selected.get(Calendar.MONTH)
                                    && c.get(Calendar.DAY_OF_MONTH) == selected.get(Calendar.DAY_OF_MONTH)) {
                                alreadySelected = true;
                                break;
                            }
                        }

                        if (alreadySelected) {
                            selectedDates.removeIf(c -> c.get(Calendar.YEAR) == selected.get(Calendar.YEAR)
                                    && c.get(Calendar.MONTH) == selected.get(Calendar.MONTH)
                                    && c.get(Calendar.DAY_OF_MONTH) == selected.get(Calendar.DAY_OF_MONTH));
                            selectedView.setBackgroundResource(R.drawable.calendar_cell_border);
                            selectedView.setTextColor(Color.BLACK);
                        } else if (selectedDates.size() < 5) {
                            selectedDates.add(selected);
                            selectedView.setBackgroundColor(Color.parseColor("#6200EE"));
                            selectedView.setTextColor(Color.WHITE);
                        } else {
                            Toast.makeText(this, "You can only select up to 5 dates.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                calendarGrid.addView(dayView);
            }
        });
    }
}
