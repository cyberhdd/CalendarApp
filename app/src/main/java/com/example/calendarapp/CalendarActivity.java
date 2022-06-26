package com.example.calendarapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {

    private static final String TAG = "CalendarActivity";

    private CalendarView mCalendarView;
    private Calendar mCalendar;
    private SimpleDateFormat mDateFormat; //test to change milli to date
    private Time currTime; //test to get time using time

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);

        //declaration
        mCalendarView = findViewById(R.id.calendarView);



        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                mCalendar = Calendar.getInstance(); //change instance each time calendar, hence date & time changes - otherwise if outside doesnt change unless closed activity
                String date = i2 + "/" + (i1+1) + "/" + i;
                Long time = mCalendar.getTimeInMillis(); //test to get milli instead of date
                mDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); //test using converter for milli to date format

                //try using each individual selection
                int hour = mCalendar.get(mCalendar.HOUR_OF_DAY);
                int mins = mCalendar.get(mCalendar.MINUTE);
                int secs = mCalendar.get(mCalendar.SECOND);

                //sqllite format
                String sqltimeformat = i + "-" + (i1+1) + "-" + i2 + " " + hour + ":" + mins + ":" + secs;

                Log.d(TAG, "onSelectedDayChange: " + date + "\nTime in normal: " + mCalendar.getTime() + "\nTime in milli: " + time + "\nTime after reformat: "
                + mDateFormat.format(time) + "\n\nHour separated: " + hour + ":" + mins + ":" + secs +
                        "\n\nSQL datetime format: " + sqltimeformat);


                Intent intent = new Intent(CalendarActivity.this, MainActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);

                //note: best to use simpledateformat to store
                //or store in milli secs also works

            }
        });
    }
}
