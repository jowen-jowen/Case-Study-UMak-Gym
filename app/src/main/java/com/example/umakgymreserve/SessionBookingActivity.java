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
                StringBuilder sb = new StringBuilder("Selected: ");
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

                if (!dayView.isEnabled()) continue; // skip disabled dates (weekends/past)

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

        final int firstDay = calendar.get(Calendar.DAY_OF_WEEK) - 1; // Sunday=1, so subtract 1 to make Sunday=0 index
        final int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // 'today' at midnight for comparison
        final Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        calendarGrid.post(() -> {
            int gridWidth = calendarGrid.getWidth();
            int cellWidth = gridWidth / 7; // 7 columns

            calendarGrid.removeAllViews();
            calendarGrid.setColumnCount(7);
            calendarGrid.setRowCount(6);

            // Add empty cells before the first day of the month
            for (int i = 0; i < firstDay; i++) {
                TextView emptyView = new TextView(this);
                GridLayout.LayoutParams emptyParams = new GridLayout.LayoutParams();
                emptyParams.width = cellWidth;
                emptyParams.height = cellWidth;
                emptyParams.setMargins(2, 2, 2, 2);
                emptyParams.columnSpec = GridLayout.spec(i);
                emptyParams.rowSpec = GridLayout.spec(0);
                emptyView.setLayoutParams(emptyParams);
                calendarGrid.addView(emptyView);
            }

            // Add day cells
            for (int day = 1; day <= daysInMonth; day++) {
                Calendar cellDate = Calendar.getInstance();
                cellDate.set(currentYear, currentMonth, day);
                cellDate.set(Calendar.HOUR_OF_DAY, 0);
                cellDate.set(Calendar.MINUTE, 0);
                cellDate.set(Calendar.SECOND, 0);
                cellDate.set(Calendar.MILLISECOND, 0);

                int dayOfWeek = cellDate.get(Calendar.DAY_OF_WEEK);

                TextView dayView = new TextView(this);
                dayView.setText(String.valueOf(day));
                dayView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                dayView.setGravity(Gravity.CENTER);
                dayView.setBackgroundResource(R.drawable.calendar_cell_border);
                dayView.setPadding(0, 20, 0, 20);

                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = cellWidth;
                params.height = cellWidth;  // square cell
                params.setMargins(2, 2, 2, 2);

                int position = firstDay + day - 1;
                int column = position % 7;
                int row = position / 7;

                params.columnSpec = GridLayout.spec(column);
                params.rowSpec = GridLayout.spec(row);
                dayView.setLayoutParams(params);

                boolean isPast = cellDate.before(today);
                boolean isWeekend = (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY);

                if (isPast || isWeekend) {
                    dayView.setTextColor(Color.GRAY);
                    dayView.setEnabled(false);
                } else {
                    dayView.setTextColor(Color.BLACK);
                    dayView.setEnabled(true);
                    dateViewsMap.put(dayView, cellDate);

                    dayView.setOnClickListener(view -> {
                        Calendar selected = dateViewsMap.get(view);
                        TextView selectedView = (TextView) view;

                        if (selectedDates.contains(selected)) {
                            selectedDates.remove(selected);
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
