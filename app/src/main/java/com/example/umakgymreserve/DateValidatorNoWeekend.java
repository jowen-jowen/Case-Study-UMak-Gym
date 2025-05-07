package com.example.umakgymreserve;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.datepicker.CalendarConstraints;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class DateValidatorNoWeekend implements CalendarConstraints.DateValidator {

    private final Set<Long> holidays = new HashSet<>();

    public DateValidatorNoWeekend() {
        // Add holidays in milliseconds
        Calendar cal = Calendar.getInstance();

        // New Year's Day - Jan 1, 2025
        cal.set(2025, Calendar.JANUARY, 1);
        holidays.add(stripTime(cal).getTimeInMillis());

        // Christmas - Dec 25, 2025
        cal.set(2025, Calendar.DECEMBER, 25);
        holidays.add(stripTime(cal).getTimeInMillis());

        // Add more holidays here...
    }

    private Calendar stripTime(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

    @Override
    public boolean isValid(long date) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date);
        stripTime(cal);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        // Block Saturdays and Sundays
        if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
            return false;
        }

        return !holidays.contains(cal.getTimeInMillis());
    }

    public static final Parcelable.Creator<DateValidatorNoWeekend> CREATOR =
            new Parcelable.Creator<DateValidatorNoWeekend>() {
                public DateValidatorNoWeekend createFromParcel(Parcel in) {
                    return new DateValidatorNoWeekend();
                }

                public DateValidatorNoWeekend[] newArray(int size) {
                    return new DateValidatorNoWeekend[size];
                }
            };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {}
}
